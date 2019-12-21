package by.epam.training.reflection.annotation;

import by.epam.training.reflection.annotation.repeatable.Befores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(Befores.class)
public @interface Before {
    String message();
}
