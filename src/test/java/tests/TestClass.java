package tests;

import core.TestBase;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestClass extends TestBase {
    private static final String URL = "https://wbo.ophir.dev/";

    @Before
    public void load(){
        logger.debug("Navigate to the site");
        sleep(1000);
        driver.get(URL);
        sleep(1000);

        logger.debug("Web application has been launched");

        sleep(1000);

        //Create a private board with a name
        WebElement boardNameField = driver.findElement(By.id("board"));
        boardNameField.sendKeys("Test Board 2");
        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"named-board-form\"]/input[2]"));
        goButton.click();
        //Wait for the board to load
        sleep(1000);
        logger.info("A new board has been created");
    }

    public void isLoaded() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl == null || !currentUrl.equals(URL)) {
            throw new Error("The page url is incorrect: " + currentUrl);
        }
    }



}
