package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderingCardTest {

    private WebDriver driver;// основная сущность c полем драйвер

    @BeforeAll
    public static void setUpAll() { // метод вызывается 1 раз перед проходом всех тестов
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Acer\\Downloads\\chromedriver_win32"); // указываем что используем и где оно у нас лежит (путь)
    }

    @BeforeEach // выполняется перед каждым тестом
    public void SetDriver() { // перед каждым тестом создаём хром драйвер
        driver = new ChromeDriver(); // инициализируем поле драйвер новым экземпляром класса
    }

    @AfterEach // выполняется после каждого теста
    public void tearDown() { // после каждого теста закрываем браузер и присваиваем null полю driver
        driver.quit(); // у driver-а вызываем метод выхода для закрытия браузера
        driver = null;
    }


}
