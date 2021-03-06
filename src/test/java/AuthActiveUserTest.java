import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthActiveUserTest {

    User user = DataGenerator.Generator.generatePersonWithRegistration("active","en");

    @Test
    void WrongPasswordAuthTest (){
        open("http://localhost:9999");
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword() + "1");
        $("[data-test-id='action-login']").submit();

        $(withText("Ошибка")).shouldBe(visible);
        String text = $("[data-test-id='error-notification'] .notification__content").getText().replaceAll("\\s\\s*", " ");
        assertEquals("Ошибка! Неверно указан логин или пароль", text);
    }

    @Test
    void WrongUsernameAuthTest (){
        open("http://localhost:9999");
        $("[data-test-id='login'] .input__control").setValue(user.getLogin() + "1");
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").submit();

        $(withText("Ошибка1111")).shouldBe(visible);
        String text = $("[data-test-id='error-notification'] .notification__content").getText().replaceAll("\\s\\s*", " ");
        assertEquals("Ошибка! Неверно указан логин или пароль", text);
    }

    @Test
    void ValidDataAuthTest (){
        open("http://localhost:9999");
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").submit();

        $(withText("Личный кабинет")).shouldBe(visible);
    }
}
