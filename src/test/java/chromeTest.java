import com.codeborne.selenide.Condition;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class chromeTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @Test
    void bankCardPositiveTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дарья Молодцова");
        $("[data-test-id=phone] input").setValue("+79160000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void bankCardNegativeNameTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Daria Molodtsova");
        $("[data-test-id=phone] input").setValue("+79160000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void bankCardNegativePhoneTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дарья Молодцова");
        $("[data-test-id=phone] input").setValue("89160000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void bankCardNoClickTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дарья Молодцова");
        $("[data-test-id=phone] input").setValue("+79160000000");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void bankCardEmptyNameTest() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+79160000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void bankCardEmptyPhoneTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дарья Молодцова");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

}
