package tests;

import org.apache.log4j.ConsoleAppender;
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

class SizeTest {
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
    @DisplayName("Change the size of the pencil")
    void ChangeSize() throws InterruptedException
    {
        driver.get("https://wbo.ophir.dev/");
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

        //Locate the Size tool
        WebElement sizeTool = driver.findElement(By.xpath("//*[@id=\"settings\"]/li[2]/img"));
        actions.moveToElement(sizeTool).perform();
        System.out.println("The mouse has hovered over the size tool");

        //Locate the size slider
        WebElement sizeSlider = driver.findElement(By.id("chooseSize"));
        actions.dragAndDropBy(sizeSlider,50,0).perform();
        System.out.println("The size has been changed");
        Thread.sleep(1000);


        DrawLine();
    }
    public void DrawLine() throws InterruptedException
    {
        WebElement lineTool = driver.findElement(By.id("toolID-Straight line"));
        lineTool.click();
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
    }


}