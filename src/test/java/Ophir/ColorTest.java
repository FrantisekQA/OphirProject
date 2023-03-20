package Ophir;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.DOMConfiguration;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    WebDriver driver;
    Actions actions;

    //private static Logger Log = Logger.getLogger(ColorTest.class.getName());

    Logger Log = Logger.getLogger("Log");

    @Before
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
    @DisplayName("Change the color of the pencil")
    void ChangeColor() throws InterruptedException
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

        //Locate the color picker
        WebElement colorPicker = driver.findElement(By.id("chooseColor"));
        actions.moveToElement(colorPicker).perform();
        Log.info("The color picker has been hovered over");

        //TODO: Create enumerators for the colors and just call them in a ChangeColor function
        //TODO: Assert that the correct color has been selected
        //TODO: Log the color change

        //Locate the color black
        WebElement blackColor = driver.findElement(By.id("color_001f3f"));
        actions.click(blackColor).perform();
        Log.info("The black color has been selected");
        Thread.sleep(2500);
        //Draw a line with the selected color
        DrawLine();

        //Locate the color red
        actions.moveToElement(colorPicker).perform();
        WebElement redColor = driver.findElement(By.id("color_FF4136"));
        actions.click(redColor).perform();
        Log.info("The red color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the color blue
        actions.moveToElement(colorPicker).perform();
        WebElement blueColor = driver.findElement(By.id("color_0074D9"));
        actions.click(blueColor).perform();
        Log.info("The blue color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the orange color
        actions.moveToElement(colorPicker).perform();
        WebElement orangeColor = driver.findElement(By.id("color_FF851B"));
        actions.click(orangeColor).perform();
        Log.info("The orange color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the yellow color
        actions.moveToElement(colorPicker).perform();
        WebElement yellowColor = driver.findElement(By.id("color_FFDC00"));
        actions.click(yellowColor).perform();
        Log.info("The yellow color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the darkGreen color
        actions.moveToElement(colorPicker).perform();
        WebElement darkGreenColor = driver.findElement(By.id("color_3D9970"));
        actions.click(darkGreenColor).perform();
        Log.info("The dark green color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the lightGreen color
        actions.moveToElement(colorPicker).perform();
        WebElement lightGreenColor = driver.findElement(By.id("color_91E99B"));
        actions.click(lightGreenColor).perform();
        Log.info("The light green color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the purple color
        actions.moveToElement(colorPicker).perform();
        WebElement purpleColor = driver.findElement(By.id("color_90468b"));
        actions.click(purpleColor).perform();
        Log.info("The purple color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the light blue color
        actions.moveToElement(colorPicker).perform();
        WebElement lightBlueColor = driver.findElement(By.id("color_7FDBFF"));
        actions.click(lightBlueColor).perform();
        Log.info("The light blue color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the gray color
        actions.moveToElement(colorPicker).perform();
        WebElement grayColor = driver.findElement(By.id("color_AAAAAA"));
        actions.click(grayColor).perform();
        Log.info("The light gray has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the pink color
        actions.moveToElement(colorPicker).perform();
        WebElement pinkColor = driver.findElement(By.id("color_E65194"));
        actions.click(pinkColor).perform();
        Log.info("The light pink has been selected");
        Thread.sleep(1500);
        DrawLine();

    }
    public void DrawLine() throws InterruptedException
    {
        WebElement lineTool = driver.findElement(By.id("toolID-Straight line"));
        lineTool.click();
        Log.info("The straight line tool has been selected");
        actions.moveByOffset(500, 0).perform();

        // Hold the mouse button down
        actions.clickAndHold().perform();

        // Move the mouse to the right for 0.5 seconds
        int offset = 100;
        actions.moveByOffset(-500, offset++).pause(500).perform();
        actions.release().perform();
        Thread.sleep(2000);
    }

    @AfterEach
    void tearDown() {
        // Close the browser
        driver.quit();
        Log.info("The browser has been closed");
    }

}