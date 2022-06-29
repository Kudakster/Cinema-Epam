import com.epam.cinema.commands.Validation;
import com.epam.cinema.enity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {
    User user;

    @BeforeEach
    public void init() {
        user = new User();
    }

    @Test
    public void userPasswordPatternTest() {
        user.setPassword("Werton12@");
        String result = Validation.validate(user);
        assertEquals("", result);
    }

    @Test
    public void userEmailPatternTest() {
        user.setEmail("email123@mail.com");
        String result= Validation.validate(user);
        assertEquals("", result);
    }

    @Test
    public void userPhoneNumberPatternTest() {
        user.setPhoneNumber("+380983214332");
        String result= Validation.validate(user);
        assertEquals("", result);
    }
}
