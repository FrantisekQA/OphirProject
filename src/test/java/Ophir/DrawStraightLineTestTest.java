package Ophir;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DrawStraightLineTestTest {

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
    @DisplayName("Test drawing a straight line")
    void DrawLine()
    {
        // Wait for the board to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"actions\"]/div[2]/a")));

        // Click on the "Create a private board" button
        WebElement createBoardButton = driver.findElement(By.xpath("//*[@id=\"actions\"]/div[2]/a"));
        createBoardButton.click();

        //Wait for the straight line tool to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toolID-Straight line")));

        // Click on the "Straight line" icon
        WebElement pencilIcon = driver.findElement(By.id("toolID-Pencil"));
        pencilIcon.click();

        /// Move the mouse cursor to the center of the screen
        Actions builder = new Actions(driver);
        builder.moveByOffset(500, 500).perform();

        // Hold the mouse button down
        builder.clickAndHold().perform();

        // Move the mouse to the right for 0.5 seconds
        builder.moveByOffset(500, 0).pause(500).perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Release the mouse button
        builder.release().perform();

    }

    @AfterEach
    void tearDown() {
    }
}