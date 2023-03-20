package Ophir;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DrawEllipseTest {

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

        //Add the appender to our Logger Object.
        //from now onwards all the logs will be directed
        //to file mentioned by FileAppender
        Log.addAppender(ConsoleAppender);
        ConsoleAppender.activateOptions();

        //Driver and actions object initialization
        driver = new ChromeDriver(options);
        actions = new Actions(driver);
        Log.info("A driver has been instantiated");
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Draw an ellipse")
    void DrawEllipse() throws InterruptedException
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

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshot, new File("C:\\Users\\PC\\IdeaProjects\\Ophir\\screenshot\\ellipse.png"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        //Select the ellipse tool
        WebElement ellipseTool = driver.findElement(By.id("toolID-Ellipse"));
        ellipseTool.click();

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
    @DisplayName("Draw a circle")
    void DrawCircle() throws InterruptedException
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

        WebElement circleTool = driver.findElement(By.id("toolID-Ellipse"));
        circleTool.click();
        circleTool.click();

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