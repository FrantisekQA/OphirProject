package Ophir;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

class OpacityTest {
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
    @DisplayName("Change the opacity")
    void ChangeOpacity() throws InterruptedException
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

        //Locate the Opacity tool
        WebElement opacity = driver.findElement(By.id("opacityIndicator"));
        actions.moveToElement(opacity).perform();
        System.out.println("The mouse has been hovered over the opacity tool");

        //Locate the opacity slider
        WebElement opacitySlider = driver.findElement(By.id("chooseOpacity"));
        actions.dragAndDropBy(opacitySlider,-50,0).perform();
        System.out.println("The opacity has been lowered");

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

}