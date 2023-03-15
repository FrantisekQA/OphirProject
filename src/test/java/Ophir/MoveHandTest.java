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

import static org.junit.jupiter.api.Assertions.*;

class MoveHandTest {
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
    @DisplayName("Draw a rectangle")
    void DrawRectangle() throws InterruptedException
    {
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

        // select the "Rectangle" tool
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


        // select the "Hand" tool
        WebElement handTool = driver.findElement(By.id("toolID-Hand"));
        handTool.click();

        WebElement canvas = driver.findElement(By.id("canvas"));
        actions.clickAndHold(canvas).perform();
        actions.moveByOffset(-500,0).pause(500).perform();
        actions.release().perform();

        actions.clickAndHold(canvas).perform();
        actions.moveByOffset(500,0).pause(500).perform();
        actions.release().perform();

        //TODO: Fix this
        //Select the selector tool
        handTool.click();
        actions.moveByOffset(400,-100).pause(500).perform();
        actions.clickAndHold(canvas).perform();
        actions.moveByOffset(-100,200).pause(500).perform();
        actions.release().perform();
    }

    @AfterEach
    void tearDown() {
        // Close the browser
        driver.quit();
    }

}