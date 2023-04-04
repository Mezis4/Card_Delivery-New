package ru.netology.web;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryNegativeTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    //Изменения в старых тестах
    //Тесты валидации поля Город (1-е задание)
    @Test
    @Description("shouldShowErrorIfCityNotAdministrativeCenter")
    void shouldShowErrorIfCityNotAdministrativeCenter() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue("Сургут");
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='city']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='city']//child::   span[@class='input__sub']").
                should(visible, text("недоступна"));
    }

    @Test
    @Description("shouldShowErrorIfCityWithLatinLetters")
    void shouldShowErrorIfCityWithLatinLetters() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity() + "fefs");
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='city']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='city']//child::span[@class='input__sub']").
                should(visible, text("недоступна"));
    }

    @Test
    @Description("shouldShowErrorIfCityWithNumbers")
    void shouldShowErrorIfCityWithNumbers() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity() + "2324");
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='city']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='city']//child::span[@class='input__sub']").
                should(visible, text("недоступна"));
    }

    @Test
    @Description("shouldShowErrorIfCityWithSymbols")
    void shouldShowErrorIfCityWithSymbols() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity() + "#$#$");
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='city']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='city']//child::span[@class='input__sub']").
                should(visible, text("недоступна"));
    }

    @Test
    @Description("shouldShowErrorIfCityFieldEmpty")
    void shouldShowErrorIfCityFieldEmpty() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='city']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='city']//child::span[@class='input__sub']").
                should(visible, text("обязательно для заполнения"));
    }

    //Тесты валидации поля Дата
    @Test
    @Description("shouldShowErrorIfMeetingDayAfterTomorrow")
    void shouldShowErrorIfMeetingDayAfterTomorrow() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 1;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='date']/span/span").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='date']//child::span[@class='input__sub']").
                should(visible, text("Заказ на выбранную дату"));
    }

    @Test
    @Description("shouldShowErrorIfMeetingDateToday")
    void shouldShowErrorIfMeetingDateToday() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 0;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='date']/span/span").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='date']//child::span[@class='input__sub']").
                should(visible, text("Заказ на выбранную дату"));
    }

    @Test
    @Description("shouldShowErrorIfMeetingDateYesterday")
    void shouldShowErrorIfMeetingDateYesterday() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = -1;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='date']/span/span").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='date']//child::span[@class='input__sub']").
                should(visible, text("Заказ на выбранную дату"));
    }

    @Test
    @Description("shouldShowErrorIfDateEmpty")
    void shouldShowErrorIfDateEmpty() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='date']/span/span").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='date']//child::span[@class='input__sub']").
                should(visible, text("Неверно"));
    }

    //Тесты на валидацию поле Фамилия и Имя
    @Test
    @Description("shouldShowErrorIfNameWithLatinLetters")
    void shouldShowErrorIfNameWithLatinLetters() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName() + " FJSDFJKSD");
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='name']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='name']//child::span[@class='input__sub']").
                should(visible, text("неверно"));
    }

    @Test
    @Description("shouldShowErrorIfWithNumber")
    void shouldShowErrorIfWithNumber() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName() + "342535");
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='name']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='name']//child::span[@class='input__sub']").
                should(visible, text("неверно"));
    }

    @Test
    @Description("shouldShowErrorIfNameWithSymbols")
    void shouldShowErrorIfNameWithSymbols() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName() + "%*(#");
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='name']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='name']//child::span[@class='input__sub']").
                should(visible, text("неверно"));
    }

    @Test
    @Description("shouldShowErrorIfNameEmpty")
    void shouldShowErrorIfNameEmpty() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='name']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='name']//child::span[@class='input__sub']").
                should(visible, text("обязательно для заполнения"));
    }

    //Тест для поля Номер телефона
    @Test
    @Description("shouldShowErrorIfPhoneEmpty")
    void shouldShowErrorIfPhoneEmpty() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='phone']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='phone']//child::span[@class='input__sub']").
                should(visible, text("обязательно для заполнения"));
    }

    @Test
    @Description("shouldShowErrorIfPhone10Numbers")
    void shouldShowErrorIfPhone10Numbers() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//span[@data-test-id='phone']//child::input").sendKeys(Keys.BACK_SPACE);
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//span[@data-test-id='phone']").shouldHave(cssClass("input_invalid"));
        $x("//span[@data-test-id='phone']//child::span[@class='input__sub']").
                should(visible, text("указан неверно"));
    }

    @Test
    @Description("shouldShowErrorIfCheckboxEmpty")
    void shouldShowErrorIfCheckboxEmpty() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//button//child::span[@class='button__text']").click();
        $x("//label[@data-test-id='agreement']").shouldHave(cssClass("input_invalid"));
    }
}
