package com.github.base.injector;

import com.github.logging.BaseLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private Properties properties = new Properties();
    private BaseLogger baseLogger = new BaseLogger();

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
                throw new RuntimeException();
            }
        } else
            try {
                FileInputStream fileInStream = new FileInputStream(file.getAbsolutePath());
                objProperties.load(fileInStream);
                fileInStream.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        baseLogger.info("Properties are initiated");
        return objProperties;
    }

    public Properties getProperties() {
        return properties;
    }
}
