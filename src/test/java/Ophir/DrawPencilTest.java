package Ophir;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
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

class BrowserTestTest {
    WebDriver driver;

    Actions actions;

    @BeforeEach
    void setUp() {
        // Set up the WebDriver and initialize the Actions object
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Test drawing with the pencil")
    void DrawPencil() throws InterruptedException
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

        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Test Board | WBO | Collaborative whiteboard";
        Assertions.assertEquals(ActualTitle, ExpectedTitle);
        System.out.println("Assertion passed");

        // Click on the "Pencil" icon
        WebElement pencilIcon = driver.findElement(By.id("toolID-Pencil"));
        pencilIcon.click();

        // Move the mouse cursors to the center of the screen
        Actions action = new Actions(driver);
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).build().perform();

        // Hold the mouse button down
        actions.clickAndHold().build().perform();

        // Move the mouse to the right for 0.5 seconds
        actions.moveByOffset(500, 0).pause(500).build().perform();

        // Release the mouse button
        actions.release().build().perform();
    }

    @AfterEach
    void tearDown() {
        // Close the browser
        driver.quit();
    }
}