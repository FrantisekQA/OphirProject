package Ophir;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
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
    Logger Log = Logger.getLogger("Log");

    @BeforeEach
    void setUp() {
        //WebDriver setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Logger setup
        /*
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("logfile.txt");
        fileAppender.setLayout(new SimpleLayout());

        Log.addAppender(fileAppender);
        fileAppender.activateOptions();
         */
        ConsoleAppender ConsoleAppender = new ConsoleAppender();
        ConsoleAppender.setLayout(new SimpleLayout());

        Log.addAppender(ConsoleAppender);
        ConsoleAppender.activateOptions();

        //Driver and actions object initialization
        driver = new ChromeDriver(options);
        actions = new Actions(driver);
        Log.info("A driver has been instantiated");
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Draw a rectangle")
    void DrawRectangle() throws InterruptedException
    {
        driver.get("https://wbo.ophir.dev/");
        //Wait for the page to load
        Thread.sleep(1000);
        Log.info("Web application has been launched");

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board 2");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();
        //Wait for the board to load
        Thread.sleep(1000);
        Log.info("A new board has been created");

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