package Ophir;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

class SizeTest {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    void setUp() {
        // Set up the WebDriver and initialize the Actions object
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Change the size of the pencil")
    void ChangeSize() throws InterruptedException
    {
        driver.get("https://wbo.ophir.dev/");

        Thread.sleep(1000);

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board 2");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();

        //Wait for the board to load
        Thread.sleep(1000);

        //Locate the Size tool
        WebElement sizeTool = driver.findElement(By.xpath("//*[@id=\"settings\"]/li[2]/img"));
        actions.moveToElement(sizeTool).perform();
        System.out.println("The mouse has hovered over the size tool");

        //Locate the size slider
        WebElement sizeSlider = driver.findElement(By.id("chooseSize"));
        actions.dragAndDropBy(sizeSlider,50,0).perform();
        System.out.println("The size has been changed");
    }

}