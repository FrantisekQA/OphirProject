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

class BrowserTestTest {
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
    @DisplayName("Test drawing with the pencil")
    void DrawPencil() {

        // Wait for the board to load
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"actions\"]/div[2]/a")));

        /*
        // Verify that the board was created successfully
        WebElement boardTitle = driver.findElement(By.xpath("//h1[contains(text(), 'My Board')]"));
        assertEquals("My Board", boardTitle.getText());

         */

        // Click on the "Create a private board" button
        WebElement createBoardButton = driver.findElement(By.xpath("//*[@id=\"actions\"]/div[2]/a"));
        createBoardButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toolID-Pencil")));

        // Click on the "Pencil" icon
        WebElement pencilIcon = driver.findElement(By.id("toolID-Pencil"));
        pencilIcon.click();

        // Move the mouse cursors to the center of the screen
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.tagName("body")), 0, 0).build().perform();

        // Hold the mouse button down
        action.clickAndHold().build().perform();

        // Move the mouse to the right for 0.5 seconds
        action.moveByOffset(500, 0).pause(500).build().perform();

        // Release the mouse button
        action.release().build().perform();
    }

    @AfterEach
    void tearDown() {
        // Close the browser
        //driver.quit();
    }
}