package Ophir;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

class DrawRectangleTest
{
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
    @DisplayName("Test drawing with a rectangle")
    void DrawRectangle() throws InterruptedException
    {
        Assertions.assertTrue(true);
        System.out.println("The test method works as expected");
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

        //Select the rectangle tool
        WebElement rectangleTool = driver.findElement(By.id("toolID-Rectangle"));
        rectangleTool.click();

        // Move the mouse cursor to the center of the screen
        actions.moveByOffset(500, 500).perform();

        // Hold the mouse button down
        actions.clickAndHold().perform();

        // Move the mouse to the right for 0.5 seconds
        actions.moveByOffset(500, 0).pause(500).perform();

        // Move the mouse to the bottom for 0.5 seconds
        actions.moveByOffset(0, 200).pause(500).perform();
        actions.release().perform();

        // Wait for the mouse dragging to be finished
        Thread.sleep(1000);
    }
    @Test
    @DisplayName("Test drawing a square")
    void DrawSquare() throws InterruptedException
    {
        Assertions.assertTrue(false);
        System.out.println("The test method is not working as expected");
        driver.get("https://wbo.ophir.dev/");

        //Wait for the page to load
        Thread.sleep(1000);

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();

        //Wait for the board to load
        Thread.sleep(1000);

        //Select the rectangle tool
        WebElement rectangleTool = driver.findElement(By.id("toolID-Rectangle"));
        rectangleTool.click();
        rectangleTool.click();

        // Move the mouse cursor to the center of the screen
        actions.moveByOffset(500, 500).perform();

        // Hold the mouse button down
        actions.clickAndHold().perform();

        // Move the mouse to the right for 0.5 seconds
        actions.moveByOffset(500, 0).pause(500).perform();

        // Move the mouse to the bottom for 0.5 seconds
        actions.moveByOffset(0, 200).pause(500).perform();
        actions.release().perform();

        // Wait for the mouse dragging to be finished
        Thread.sleep(1000);
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}