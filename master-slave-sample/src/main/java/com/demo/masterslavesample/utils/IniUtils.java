package com.demo.masterslavesample.utils;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

public class IniUtils {

    private static IniUtils instance;

    private final Ini ini;

    private IniUtils() throws IOException {
        ini = new Ini(new File(System.getProperty("CONFIG_FILE")));
    }

    public static synchronized IniUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new IniUtils();
        }
        return instance;
    }

    public String getValue(String section, String key) {
        return ini.get(section, key);
    }

}
