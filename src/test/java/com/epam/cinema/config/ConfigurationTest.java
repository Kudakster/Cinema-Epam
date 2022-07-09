package com.epam.cinema.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {
    private Configuration instance;

    @BeforeEach
    public void setup() {
        instance = Configuration.getInstance();
    }

    @Test
    public void givenConfigurationGetInstance_whenInstanceIsNull_thenReturnConfigurationInstance() {
        assertThat(instance.getClass()).isEqualTo(Configuration.class);
    }

    @Test
    public void givenConfigurationLocale_whenLocaleIsEn_thenLocaleEn() {
        instance.setLocale(Locale.US);
        assertThat(instance.getLocale()).isEqualTo(Locale.US);
    }

    @Test
    public void givenConfigurationPage_whenKeyIsExist_thenPageUri() {
        String page = instance.getPage("main");
        assertThat(page).isEqualTo("/pages/main.jsp");
    }

    @Test
    public void givenConfigurationError_whenErrorIsExist_thenErrorMessage() {
        String error = instance.getError("user.size.firstName");
        assertThat(error).isEqualTo("FirstName must be min 2 and max 30");
    }
}


