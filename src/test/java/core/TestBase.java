package core;


import core.rules.ConsoleLogRule;
import core.rules.LoggerRule;
import core.rules.RetryRule;
import core.rules.ScreenshotRule;
import io.qameta.allure.Step;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.RuleChain;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestBase
{
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);
    private boolean retryFlag = false;

    protected static DriverConfiguration driverConfiguration;

    static int retry;

    static{
        driverConfiguration = new DriverConfiguration();

        if(System.getProperty("retry") != null){
            retry = Integer.parseInt(System.getProperty("retry"));
        }else{
            retry = 1;
        }
        logger.debug("Retry: " + retry);
    }

    protected WebDriver driver;

    @Rule
    public RuleChain allRules = setRules(driverConfiguration.getBrowser());
    public static final int DEFAULT_WAIT = 10;

    public TestBase(){

    }

    public RuleChain setRules(String browser) {

        RuleChain rule = null;

        if (browser == "chrome") {
            rule = RuleChain.outerRule(new RetryRule(retry, this))
                    .around(new LoggerRule())
                    .around(new ConsoleLogRule(this))
                    .around(new ScreenshotRule(this));
        }
        return rule;
    }

    public void initDriver(boolean headless){
        driver = DriverFactory.getInstance().createDriver(driverConfiguration, headless);

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
    }

    protected final void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public WebDriver getDriver() {
        return driver;
    }

    public String getDownloadFilePath() {
        switch (driverConfiguration.getTestMode()) {
            case LOCAL:
                return DriverFactory.LOCAL_DOWNLOAD_FILE_PATH_ROOT + File.separator + "driver"
                        + Thread.currentThread().getId();
            default:
                throw new Error("The test mode is not supported yet: " + driverConfiguration.getTestMode());
        }
    }

    public static DriverConfiguration getDriverConfiguration() {
        return driverConfiguration;
    }

    public void setRetryFlagTo(boolean bool) {
        retryFlag = bool;
    }

    public boolean getRetryFlag() {
        return retryFlag;
    }
}
