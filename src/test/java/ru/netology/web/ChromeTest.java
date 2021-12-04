package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.http.io.SessionOutputBuffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChromeTest {

    private WebDriver driver;// основная сущность c полем драйвер

    @BeforeAll
    public static void setUpClass() { // метод вызывается 1 раз перед проходом всех тестов
        WebDriverManager.chromedriver().setup(); // указываем что используем и где оно у нас лежит (путь)
    }

    @BeforeEach // выполняется перед каждым тестом
    public void setupTest() { // перед каждым тестом создаём хром драйвер
        driver = new ChromeDriver(); // инициализируем поле драйвер новым экземпляром класса
    }

    @AfterEach // выполняется после каждого теста
    public void tearDown() { // после каждого теста закрываем браузер и присваиваем null полю driver
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldSendForm() {
        driver.get("http://localhost:9999"); // открываем страницу которую собираемся тестировать
//        System.out.println(""); // проверка что браузер Хром запускается и открывает localhost
//        driver.findElement().sendKeys("Анатолий"); // находим поле (элемент) для имени и в этот элемент передаём имя
//        driver.findElement().sendKeys("+79375566778"); // находим поле (элемент) для номера телефона и в этот элемент передаём номер

        List<WebElement> textFields = driver.findElements(By.className("input__control")); // находим все поля по названию класса. Сохраним результат в список WebElement и назовём его textFields
        textFields.get(0).sendKeys("Анатолий"); // обращаемся к полученному результату по индексному порядку (первый по списку)
        textFields.get(1).sendKeys("+79375566778"); // обращаемся к полученному результату по индексному порядку (второй по списку)

        driver.findElement(By.className("checkbox__box")).click(); // находим чек-бокс и делаем клик по нему
        driver.findElement(By.tagName("button")).click(); // находим кнопку "Продолжить" и делаем клик по ней
        // проверяем что получили надпись "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."
        String actualText = driver.findElement(By.className("order-success")).getText().trim(); // необходимо найти элемент и получить у него текст. Сохраняем полученный текст в переменную actualText, где trim - обрезает лишние пробелы в тексте
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."; // ожидаемый результат
        assertEquals(expected, actualText, "Текст сообщения не совпадает"); // сравниваем ОР и ФР
    }

}
