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

class ColorTest {
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
    @DisplayName("Change the color of the pencil")
    void ChangeColor() throws InterruptedException
    {
        driver.get("https://wbo.ophir.dev/");

        Thread.sleep(1000);

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board 2");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();

        //Locate the color picker
        WebElement colorPicker = driver.findElement(By.id("chooseColor"));
        actions.moveToElement(colorPicker).perform();
        System.out.println("The color picker has been hovered over");

        //TODO: Create enumerators for the colors and just call them in a ChangeColor function
        //TODO: Assert that the correct color has been selected
        //TODO: Log the color change

        //Locate the color black
        WebElement blackColor = driver.findElement(By.id("color_001f3f"));
        actions.click(blackColor).perform();
        System.out.println("The black color has been selected");
        Thread.sleep(2500);
        //Draw a line with the selected color
        DrawLine();

        //Locate the color red
        actions.moveToElement(colorPicker).perform();
        WebElement redColor = driver.findElement(By.id("color_FF4136"));
        actions.click(redColor).perform();
        System.out.println("The red color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the color blue
        actions.moveToElement(colorPicker).perform();
        WebElement blueColor = driver.findElement(By.id("color_0074D9"));
        actions.click(blueColor).perform();
        System.out.println("The blue color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the orange color
        actions.moveToElement(colorPicker).perform();
        WebElement orangeColor = driver.findElement(By.id("color_FF851B"));
        actions.click(orangeColor).perform();
        System.out.println("The orange color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the yellow color
        actions.moveToElement(colorPicker).perform();
        WebElement yellowColor = driver.findElement(By.id("color_FFDC00"));
        actions.click(yellowColor).perform();
        System.out.println("The yellow color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the darkGreen color
        actions.moveToElement(colorPicker).perform();
        WebElement darkGreenColor = driver.findElement(By.id("color_3D9970"));
        actions.click(darkGreenColor).perform();
        System.out.println("The dark green color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the lightGreen color
        actions.moveToElement(colorPicker).perform();
        WebElement lightGreenColor = driver.findElement(By.id("color_91E99B"));
        actions.click(lightGreenColor).perform();
        System.out.println("The light green color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the purple color
        actions.moveToElement(colorPicker).perform();
        WebElement purpleColor = driver.findElement(By.id("color_90468b"));
        actions.click(purpleColor).perform();
        System.out.println("The purple color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the light blue color
        actions.moveToElement(colorPicker).perform();
        WebElement lightBlueColor = driver.findElement(By.id("color_7FDBFF"));
        actions.click(lightBlueColor).perform();
        System.out.println("The light blue color has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the gray color
        actions.moveToElement(colorPicker).perform();
        WebElement grayColor = driver.findElement(By.id("color_AAAAAA"));
        actions.click(grayColor).perform();
        System.out.println("The light gray has been selected");
        Thread.sleep(1500);
        DrawLine();

        //Locate the pink color
        actions.moveToElement(colorPicker).perform();
        WebElement pinkColor = driver.findElement(By.id("color_E65194"));
        actions.click(pinkColor).perform();
        System.out.println("The light pink has been selected");
        Thread.sleep(1500);
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