package Ophir;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DrawRectangleTest
{
    WebDriver driver;
    @BeforeEach
    void setUp() {
        // Create a new instance of the Chrome driver
        driver = new ChromeDriver();
        // Open the web page
        driver.get("https://wbo.ophir.dev/");
        // Maximize the browser window
        driver.manage().window().maximize();
        // Wait implicitly for 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Test drawing a rectangle")
    void DrawRectangle()
    {
        // Wait for the board to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"actions\"]/div[2]/a")));

        // Click on the "Create a private board" button
        WebElement createBoardButton = driver.findElement(By.xpath("//*[@id=\"actions\"]/div[2]/a"));
        createBoardButton.click();

        //Wait for the rectangle tool to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toolID-Rectangle")));

        // Click on the "Rectangle" icon
        // The tool can also be selected by pressing the "r" key on the keyboard
        WebElement pencilIcon = driver.findElement(By.id("toolID-Pencil"));
        pencilIcon.click();
    }
}