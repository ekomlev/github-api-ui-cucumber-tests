package com.github.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private Properties properties;

    public PropertyProvider(final String resourceName) {
        properties = appendFromResource(properties, resourceName);
    }

    private Properties appendFromResource(final Properties objProperties, final String resourceName) {
        File file = new File(resourceName);

        if (file.getParent() == null) {
            InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
            System.out.println(resourceName);
            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                System.out.println("==========================================================================");
                e.printStackTrace();
                System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
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

    public Properties getProperties() {
        return properties;
    }

    /* public static String getProperty(final String key) {
        return properties.getProperty(key);
    }*/
}
