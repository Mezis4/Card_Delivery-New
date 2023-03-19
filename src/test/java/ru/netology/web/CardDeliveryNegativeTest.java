package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryNegativeTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @AfterEach
    void clear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    //Изменения в старых тестах
    //Тесты валидации поля Город (1-е задание)
    @Test
    @DisplayName("shouldShowErrorIfCityNotAdministrativeCenter")
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
    @DisplayName("shouldShowErrorIfCityWithLatinLetters")
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
    @DisplayName("shouldShowErrorIfCityWithNumbers")
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
    @DisplayName("shouldShowErrorIfCityWithSymbols")
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
    @DisplayName("shouldShowErrorIfCityFieldEmpty")
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
    @DisplayName("shouldShowErrorIfMeetingDayAfterTomorrow")
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
    @DisplayName("shouldShowErrorIfMeetingDateToday")
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
    @DisplayName("shouldShowErrorIfMeetingDateYesterday")
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
    @DisplayName("shouldShowErrorIfDateEmpty")
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
    @DisplayName("shouldShowErrorIfNameWithLatinLetters")
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
    @DisplayName("shouldShowErrorIfWithNumber")
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
    @DisplayName("shouldShowErrorIfNameWithSymbols")
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
    @DisplayName("shouldShowErrorIfNameEmpty")
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
    @DisplayName("shouldShowErrorIfPhoneEmpty")
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
    @DisplayName("shouldShowErrorIfPhone10Numbers")
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
    @DisplayName("shouldShowErrorIfCheckboxEmpty")
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
