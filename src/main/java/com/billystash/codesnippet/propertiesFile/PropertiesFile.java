package com.billystash.codesnippet.propertiesFile;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class PropertiesFile {

    private static PropertiesFile single_instance = null;

    static final String FILE_CONF_PROPERTIES_PATH = "./conf.properties";
    Properties appProps = new Properties();

    public static PropertiesFile getInstance() {
        if (single_instance == null) {
            single_instance = new PropertiesFile();
        }
        return single_instance;
    }

    public PropertiesFile() {
        copyFileConfProperties();
        try {
            appProps.load(new FileInputStream(FILE_CONF_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFileConfProperties() {
        if (!Files.exists(Paths.get(FILE_CONF_PROPERTIES_PATH))) {
            copy(getClass().getResourceAsStream("/com/billystash/codesnippet/propertiesFile/conf.properties"), FILE_CONF_PROPERTIES_PATH);
        }
    }

    public String getValueByKey(String key) {
        return appProps.getProperty(key);
    }

    public void setAndSaveValueByKey(String key, String value) {
            appProps.setProperty(key, value);
        try {
            appProps.store(new FileWriter(FILE_CONF_PROPERTIES_PATH), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean copy(InputStream source, String destination) {
        boolean success = true;
        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            success = false;
        }
        return success;
    }
}
