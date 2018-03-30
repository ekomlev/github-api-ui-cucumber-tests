package com.github.utils;

import com.github.entities.User;
import com.github.logging.LoggerInstanceProvider;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class UserCreator {
    private static final String USER_FILENAME = "src/test/resources/user_data.json";
    private static User instance;
    private User user;
    private static Logger systemInfo = LoggerInstanceProvider.getLogger(UserCreator.class);

    private UserCreator() {
        this.user = createUserFromJson();
    }

    public static User getInstance() {
        if (instance == null) {
            instance = createUserFromJson();
        }
        return instance;
    }

    private static User createUserFromJson() {
        Gson gson = new Gson();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(USER_FILENAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        User user = gson.fromJson(reader, User.class);
        systemInfo.info("User from json resource was created: " + user);
        return user;
    }
}
