package me.kupchenko.model;

import me.kupchenko.mapper.FieldName;
import me.kupchenko.mapper.Transient;

public class User {
    private Long id;
    private String username;
    @FieldName(name = "first_name")
    private String name;
    @FieldName(name = "last_name")
    private String lastName;
    @Transient
    private String temp;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
