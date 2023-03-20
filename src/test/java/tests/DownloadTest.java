package tests;

import org.apache.log4j.*;
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

class DownloadTest {
    WebDriver driver;
    Actions actions;

    Logger Log = LogManager.getLogger("Log");

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
    @DisplayName("Download the shape")
    void DownloadShape() throws InterruptedException
    {
        driver.get("https://wbo.ophir.dev/");
        Log.info("Web application has been launched");

        Thread.sleep(1000);

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board 2");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();
        //Wait for the board to load
        Thread.sleep(1000);
        Log.info("A new board has been created");

        //Draw a shape first
        //Select the rectangle tool
        WebElement rectangleTool = driver.findElement(By.id("toolID-Rectangle"));
        rectangleTool.click();
        Log.info("The rectangle tool has been selected");

        try {
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
            Log.info("A rectangle has been drawn");
        }
        catch (Exception exp){
            Log.warn("The following exception was raised: ", exp);
        }

        WebElement downloadTool = driver.findElement(By.id("toolID-Download"));
        downloadTool.click();
        Log.info("The image has been downloaded");
    }

    @AfterEach
    void tearDown() {
        // Close the browser
        driver.quit();
    }
}