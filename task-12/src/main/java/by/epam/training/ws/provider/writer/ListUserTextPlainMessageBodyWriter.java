package by.epam.training.ws.provider.writer;

import by.epam.training.ws.model.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

@Provider
public class ListUserTextPlainMessageBodyWriter implements MessageBodyWriter<List<User>> {

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.equals(MediaType.TEXT_PLAIN_TYPE);
    }

    @Override
    public void writeTo(List<User> users, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        users.forEach(user -> {
                 try {
                     writer.append(user.toString()).append("\n");
                 } catch (IOException e) {
                     throw new RuntimeException("Processing user data exception");
                 }
             });

        writer.close();
    }
}
