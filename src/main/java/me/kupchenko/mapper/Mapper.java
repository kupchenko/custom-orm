package me.kupchenko.mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Stream;

public class Mapper {
    public static <T> T map(Map<String, Object> data, Class<T> clazz) {
        T object;
        try {
            object = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new MapperException(e);
        }
        Stream.of(clazz.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Transient.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    String name;
                    if (field.isAnnotationPresent(FieldName.class)) {
                        name = field.getAnnotation(FieldName.class).name();
                    } else {
                        name = field.getName();
                    }
                    Object value = data.get(name);
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        throw new MapperException(e);
                    }
                });
        return object;
    }

    public static <T> T map(ResultSet data, Class<T> clazz) {
        T object;
        try {
            object = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new MapperException(e);
        }
        Stream.of(clazz.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Transient.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    String name;
                    if (field.isAnnotationPresent(FieldName.class)) {
                        name = field.getAnnotation(FieldName.class).name();
                    } else {
                        name = field.getName();
                    }
                    Object value = null;
                    try {
                        value = data.getObject(name);
                    } catch (SQLException e) {
                        throw new MapperException(e);
                    }
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        throw new MapperException(e);
                    }
                });
        return object;
    }
}
