package Ophir;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DrawStraightLineTestTest {

    WebDriver driver;

    Actions actions;
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Test drawing a straight line")
    void DrawLine() throws InterruptedException
    {
        driver.get("https://wbo.ophir.dev/");

        //Wait for the page to load
        Thread.sleep(5000);

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();

        //Wait for the board to load
        Thread.sleep(1000);

        // Click on the "Straight line" icon
        WebElement lineIcon = driver.findElement(By.id("toolID-Pencil"));
        lineIcon.click();

        /// Move the mouse cursor to the center of the screen
        Actions builder = new Actions(driver);
        builder.moveByOffset(500, 500).perform();

        // Hold the mouse button down
        builder.clickAndHold().perform();

        // Move the mouse to the right for 0.5 seconds
        builder.moveByOffset(500, 0).pause(500).perform();

        // Release the mouse button
        builder.release().perform();

    }

    @Test
    @DisplayName("Test drawing a straighter line")
    void DrawStraigtLine() throws InterruptedException
    {
        driver.get("https://wbo.ophir.dev/");

        //Wait for the page to load
        Thread.sleep(5000);

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();

        //Wait for the board to load
        Thread.sleep(1000);

        // Click on the "Straight line" icon
        WebElement lineIcon = driver.findElement(By.id("toolID-Straight line"));
        lineIcon.click();
        lineIcon.click();

        /// Move the mouse cursor to the center of the screen
        actions.moveByOffset(500, 400).perform();

        // Hold the mouse button down
        actions.clickAndHold().perform();

        // Move the mouse to the right for 0.5 seconds
        actions.moveByOffset(500, 0).pause(500).perform();

        // Release the mouse button
        actions.release().perform();

    }

    @AfterEach
    void tearDown() {
    }
}