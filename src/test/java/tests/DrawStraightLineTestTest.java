package tests;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

class DrawStraightLineTestTest {

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
    @DisplayName("Test drawing a straight line")
    void DrawLine() throws InterruptedException
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
        // Close the browser
        driver.quit();
    }
}