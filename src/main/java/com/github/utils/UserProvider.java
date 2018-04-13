package com.github.utils;

import com.github.entities.User;
import com.github.logging.BaseLogger;
import com.google.gson.Gson;
import com.google.inject.Provider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UserProvider implements Provider<User> {
    private final String USER_FILENAME = "src/test/resources/user_data.json";
    private BaseLogger logger = new BaseLogger();

    @Override
    public User get() {
        Gson gson = new Gson();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(USER_FILENAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        User user = gson.fromJson(reader, User.class);
        logger.info("User from json resource was created: " + user);
        return user;
    }
}
