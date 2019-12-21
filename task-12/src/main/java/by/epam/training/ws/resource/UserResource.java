package by.epam.training.ws.resource;

import by.epam.training.ws.exception.UserNotAvailableException;
import by.epam.training.ws.model.User;
import by.epam.training.ws.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/")
public class UserResource {

    private UserRepository repository;

    @PostConstruct
    private void init() {
        repository = UserRepository.getInstance();
    }

    @PreDestroy
    private void destroy() {
        repository = null;
    }

    @GET
    @Path("/users")
    @Produces(TEXT_PLAIN)
    public List<User> findAll() {
        return getUsers();
    }

    @POST
    @Path("/user")
    @Consumes(APPLICATION_JSON)
    @Produces(TEXT_PLAIN)
    public Response add(@Context HttpHeaders headers, User user) {
        repository.create(user);

        return Response.ok(getUsers()).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/user/{id}")
    @Produces(TEXT_PLAIN)
    public Response findById(@PathParam("id") int id) {
        User currentUser = getUsers().stream()
                                     .filter(user -> user.getId() == id)
                                     .findFirst()
                                     .orElse(null);

        IfUserNotExist(id, currentUser);

        return Response.ok(currentUser).status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/user/{id}")
    @Produces(TEXT_PLAIN)
    public Response remove(@PathParam("id") int id) {
        User user = repository.getDatabase().remove(id);

        IfUserNotExist(id, user);

        return Response.ok(getUsers()).status(Response.Status.ACCEPTED).build();
    }

    @PUT
    @Path("/user/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(TEXT_PLAIN)
    public Response update(@Context HttpHeaders headers, User user, @PathParam("id") int id) {
        Map<Integer, User> database = repository.getDatabase();

        User currentUser = database.get(id);

        IfUserNotExist(id, currentUser);

        database.put(id, user);

        return Response.ok(getUsers()).status(Response.Status.ACCEPTED).build();
    }

    private void IfUserNotExist(int id, User user) {
        if (user == null) {
            throw new UserNotAvailableException(String.valueOf(id));
        }
    }

    private List<User> getUsers() {
        return repository.getDatabase()
                         .entrySet()
                         .stream()
                         .map(set -> new User(set.getKey(),
                                              set.getValue().getName(),
                                              set.getValue().getAge()))
                         .collect(Collectors.toList());
    }
}
