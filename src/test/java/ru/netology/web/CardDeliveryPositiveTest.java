package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class CardDeliveryPositiveTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @AfterEach
    void clear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    //Новый тест для изменения даты встречи
    @Test
    @DisplayName("shouldSuccessfulPlanAndReplanMeeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
        $x("//span[@data-test-id='date']//child::input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(secondMeetingDate);
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='replan-notification']//child::div[@class='notification__title']").
                should(Condition.exactText("Необходимо подтверждение"));
        $x("//div[@data-test-id='replan-notification']//child::span[@class ='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class ='notification__content']").
                should(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }

    @Test
    @DisplayName("shouldNotSuccessfulPlanAndReplanMeetingIfCityChanged")
    void shouldNotSuccessfulPlanAndReplanMeetingIfCityChanged() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate), ofSeconds(5))
                .shouldBe(visible);
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='replan-notification']//child::div[@class='notification__title']").
                should(Condition.exactText("Необходимо подтверждение"));
        $x("//span[@data-test-id='city']//child::input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }

    @Test
    @DisplayName("shouldNotSuccessfulPlanAndReplanMeetingIfNameChanged")
    void shouldNotSuccessfulPlanAndReplanMeetingIfNameChanged() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate), ofSeconds(5))
                .shouldBe(visible);
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='replan-notification']//child::div[@class='notification__title']").
                should(Condition.exactText("Необходимо подтверждение"));
        $x("//span[@data-test-id='name']//child::input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }

    @Test
    @DisplayName("shouldNotSuccessfulPlanAndReplanMeetingIfPhoneChanged")
    void shouldNotSuccessfulPlanAndReplanMeetingIfPhoneChanged() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate), ofSeconds(5))
                .shouldBe(visible);
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='replan-notification']//child::div[@class='notification__title']").
                should(Condition.exactText("Необходимо подтверждение"));
        $x("//span[@data-test-id='phone']//child::input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }

    //Изменения в старых тестах:
    //Тест появления попапа и соответствия выбранной даты
    @Test
    @DisplayName("shouldShowSuccessfulBookingPopUpWithSelectedDate")
    void shouldShowSuccessfulBookingPopUpWithSelectedDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }

    //Тест валидации поля Город
    @Test
    @DisplayName("shouldShowSuccessfulBookingPopUpIfCityNameWithDash")
    void shouldShowSuccessfulBookingPopUpIfCityNameWithDash() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue("Санкт-Петербург");
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }

    //Тесты валидации поля Фамилия и Имя
    @Test
    @DisplayName("shouldShowSuccessfulBookingPopUpIfNameWithDash")
    void shouldShowSuccessfulBookingPopUpIfNameWithDash() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName() + "-Пупкин");
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }

    @Test
    @DisplayName("shouldShowSuccessfulBookingPopUpIfNameWithLetterЁ")
    void shouldShowSuccessfulBookingPopUpIfNameWithLetterЁ() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName() + "Ёлкин");
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }

    @Test
    @DisplayName("shouldShowSuccessfulBookingPopUpIfNameWithSeveralParts")
    void shouldShowSuccessfulBookingPopUpIfNameWithSeveralParts() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $x("//span[@data-test-id='city']//child::input").setValue(validUser.getCity());
        $x("//span[@data-test-id='date']//child::input").doubleClick().
                sendKeys(Keys.BACK_SPACE);
        $x("//span[@data-test-id='date']//child::input").setValue(firstMeetingDate);
        $x("//span[@data-test-id='name']//child::input").setValue(validUser.getName() + " деБлюм");
        $x("//span[@data-test-id='phone']//child::input").setValue(validUser.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $x("//button//child::span[@class='button__text']").click();
        $x("//div[@data-test-id='success-notification']//child::div[@class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }
}