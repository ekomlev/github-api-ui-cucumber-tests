package com.github.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static Properties properties = new Properties();

    public PropertyProvider(final String resourceName) {
        properties = appendFromResource(properties, resourceName);
    }

    private Properties appendFromResource(final Properties objProperties, final String resourceName) {
        File file = new File(resourceName);

        if (file.getParent() == null) {
            InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);

            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            try {
                FileInputStream fileInStream = new FileInputStream(file.getAbsolutePath());
                objProperties.load(fileInStream);
                fileInStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return objProperties;

    }

    public static String getProperty(final String key) {
        return properties.getProperty(key);
    }
}
