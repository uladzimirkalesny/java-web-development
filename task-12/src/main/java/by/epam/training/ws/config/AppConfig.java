package by.epam.training.ws.config;

import by.epam.training.ws.provider.mapper.UserIdNotAvailableExceptionMapper;
import by.epam.training.ws.provider.writer.ListUserTextPlainMessageBodyWriter;
import by.epam.training.ws.provider.writer.UserTextPlainMessageBodyWriter;
import by.epam.training.ws.repository.UserRepository;
import by.epam.training.ws.resource.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class AppConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(UserResource.class);
        resources.add(ListUserTextPlainMessageBodyWriter.class);
        resources.add(UserTextPlainMessageBodyWriter.class);
        resources.add(UserIdNotAvailableExceptionMapper.class);
        return Collections.unmodifiableSet(resources);
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Class<?>> singletons = new HashSet<>();
        singletons.add(UserRepository.class);
        return Collections.unmodifiableSet(singletons);
    }
}
