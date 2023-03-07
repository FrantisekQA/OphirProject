package Ophir;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

class DownloadTest {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    void setUp() {
        // Set up the WebDriver and initialize the Actions object
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

}