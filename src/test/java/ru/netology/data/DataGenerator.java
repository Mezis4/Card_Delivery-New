package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        LocalDate deliveryDate = LocalDate.now().plusDays(shift);
        String date = deliveryDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        String[] administrativeCenter = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик",
                "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ",
                "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита", "Владивосток",
                "Краснодар", "Красноярск", "Пермь",  "Ставрополь", "Хабаровск", "Петропавловск-Камчатский", "Вологда",
                "Иваново", "Иркутск", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Орёл",
                "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир", "Волгоград",  "Воронеж",
                "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган", "Курск", "Липецк", "Магадан", "Тула",
                "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Пенза", "Псков", "Ульяновск",
                "Самара", "Саратов", "Рязань",  "Тюмень",  "Челябинск", "Мурманск", "Ростов-на-Дону",
                "Ярославль", "Москва", "Санкт-Петербург", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск",
                "Анадырь", "Салехард", "Элиста",};
        List<String> cityList = new ArrayList<>();
        for (int i = 0; i < administrativeCenter.length; i++) {
            cityList.add(administrativeCenter[i]);
        }
        Random randomCity = new Random();
        String city = cityList.get(randomCity.nextInt(administrativeCenter.length));
        return city;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }

}
