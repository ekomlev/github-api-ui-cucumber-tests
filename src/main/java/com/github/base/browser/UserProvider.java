package com.github.base.browser;

import com.github.entities.User;
import com.github.logging.LoggerInstanceProvider;
import com.google.gson.Gson;
import com.google.inject.Provider;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UserProvider implements Provider<User> {
    private static final String USER_FILENAME = "src/test/resources/user_data.json";
    private static Logger systemInfo = LoggerInstanceProvider.getLogger(UserProvider.class);

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
        systemInfo.info("User from json resource was created: " + user);
        return user;
    }
}
