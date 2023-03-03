package Ophir;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DrawRectangleTest
{
    WebDriver driver;
    @BeforeEach
    void setUp() {
        // Create a new instance of the Chrome driver
        driver = new ChromeDriver();
        // Open the web page
        driver.get("https://wbo.ophir.dev/");
        // Maximize the browser window
        driver.manage().window().maximize();
        // Wait implicitly for 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Test drawing a rectangle")
    void DrawRectangle()
    {

    }
}