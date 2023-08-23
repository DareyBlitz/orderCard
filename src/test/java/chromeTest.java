import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

public class chromeTest {
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-dev-shm-usage");
        options.addArguments(
                "--no-sandbox");
        options.addArguments(
                "--headless");
    }


}
