package me.kupchenko.mapper;

public class MapperException extends RuntimeException {
    public MapperException(Exception e) {
        super(e);
    }
}
