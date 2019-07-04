package me.kupchenko;

import me.kupchenko.mapper.Mapper;
import me.kupchenko.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 55L);
        data.put("username", "dkupc");
        data.put("first_name", "Dmitrii");
        data.put("last_name", "Kupchenko");

        User map = Mapper.map(data, User.class);

        System.out.println(map);
    }


    public static void resultSetExample() throws SQLException {
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(Mapper.map(resultSet, User.class));
        }

        System.out.println(users);
    }
}
