package com.epam.cinema.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class Configuration {
    private static Configuration instance;
    private Locale locale;
    private ResourceBundle pages;
    private ResourceBundle errors;

    private Configuration() {
        locale = new Locale("en");
        pages = ResourceBundle.getBundle("pages");
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getPage(String key) {
        return pages.getString(key);
    }

    public String getError(String key) {
        errors = ResourceBundle.getBundle("errors", locale);
        return errors.getString(key);
    }
}
