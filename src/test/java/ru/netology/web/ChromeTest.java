package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


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
        ChromeOptions options = new ChromeOptions(); // в режиме headless мы отключаем графический интерфейс браузера (при этот все процессы браузера продолжают работать так же)
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
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

        driver.findElement(By.cssSelector("[type = 'text']")).sendKeys("Анатолий"); // находим поле (элемент) через cssSelector по атрибуту text и в этот элемент передаём имя
        driver.findElement(By.cssSelector("[type = 'tel']")).sendKeys("+79375566778"); // находим поле (элемент) через cssSelector по атрибуту tel и в этот элемент передаём номер

//        List<WebElement> textFields = driver.findElements(By.className("input__control")); // находим все поля по названию класса. Сохраним результат в список WebElement и назовём его textFields
//        textFields.get(0).sendKeys("Анатолий"); // обращаемся к полученному результату по индексному порядку (первый по списку)
//        textFields.get(1).sendKeys("+79375566778"); // обращаемся к полученному результату по индексному порядку (второй по списку)

        driver.findElement(By.cssSelector(".checkbox__box")).click(); // находим поле чек-бокс через cssSelector по классу и делаем клик по нему
        driver.findElement(By.cssSelector("button")).click(); // находим поле кнопки "Продолжить" через cssSelector по имени тега и делаем клик по ней
        // проверяем что получили уведомление "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."
        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'order-success']")).getText().trim(); // необходимо найти элемент и получить у него текст. Сохраняем полученный текст в переменную actualText. trim - обрезает лишние пробелы в тексте
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."; // ожидаемый результат
        assertEquals(expected, actualText, "Текст сообщения не совпадает"); // сравниваем ОР и ФР
    }

}
