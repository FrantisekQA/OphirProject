package Ophir;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

class EraserTest {
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
    @DisplayName("Test entering text")
    void Erase() throws InterruptedException
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

        //Select the Text tool
        WebElement textTool = driver.findElement(By.id("toolID-Text"));
        textTool.click();

        // Move the mouse cursor to the center of the screen
        actions.moveByOffset(200, 200).perform();

        // Click on the screen so that the text field appears
        actions.click().perform();

        actions.sendKeys("Damjan je budala").perform();
        Thread.sleep(500);

        actions.sendKeys(Keys.ENTER).perform();

        Thread.sleep(1000);

        //Click on the eraser tool
        WebElement eraser = driver.findElement(By.id("toolID-Eraser"));
        eraser.click();
        Thread.sleep(1000);

        WebElement rectangle = driver.findElement(By.id("rley5i7qg8"));
        actions.moveToElement(rectangle).click().perform();

    }

    @AfterEach
    void tearDown() {
        // Close the browser
        driver.quit();
    }
}