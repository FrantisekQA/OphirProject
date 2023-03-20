package core;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private static final Logger logger_DriverFactory = LoggerFactory.getLogger(DriverFactory.class);

    public static final String REMOTE_DOWNLOAD_FILE_NAME = "Ophir_download_folder";
    public static final String REMOTE_DOWNLOAD_FILE_PATH_ROOT = "C:" + File.separator + REMOTE_DOWNLOAD_FILE_NAME;

    public static final String LOCAL_DOWNLOAD_FILE_PATH_ROOT = System.getProperty("user.home")
            + File.separator + "Downloads" + File.separator + "Ophir-Download";

    private static DriverFactory instance = new DriverFactory();

    public String downloadFilePath;

    private DriverFactory()
    {

    }

    public static DriverFactory getInstance(){return instance;}

    //Driver configuration
    public WebDriver createDriver(DriverConfiguration testConfig, boolean headless){
        switch (testConfig.getTestMode()){
            case LOCAL:
                return getLocalDriver(testConfig.getBrowser(), headless);
            default:
                throw new InvalidParameterException("Unexpected test mode: " + testConfig.getTestMode());
        }
    }

    private WebDriver getLocalDriver(String browser, boolean headless){
        WebDriver result = null;
        String driverLocation = getDriverLocation(browser);
        downloadFilePath = LOCAL_DOWNLOAD_FILE_PATH_ROOT + File.separator + "driver"+ Thread.currentThread().getId();

        switch(browser){
        default:
        case "chrome":
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverLocation);
        System.setProperty("webdriver.chrome.logfile",
                System.getProperty("java.io.tmpdir") + File.separator + "chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        HashMap<String, Object> chromePrefs = new HashMap<>();

        chromePrefs.put("download.default_directory", downloadFilePath);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("Browser.setDownloadBehavior", "allow");
        ChromeOptions chromeOptions = new ChromeOptions();

        LoggingPreferences prefChrome = new LoggingPreferences();
        prefChrome.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability("goog:loggingPrefs", prefChrome);

        if (headless) {
            chromeOptions.addArguments("--headless");
        }
        chromeOptions.addArguments("--disable-backgrounding-occluded-windows");
        chromeOptions.addArguments("disable-web-security"); // this should be removed after CORS policy issue is resolved
        chromeOptions.addArguments("window-size=1920x1080");
        //options.addArguments("screenshot");
        //chromeOptions.setExperimentalOption("prefs", chromePrefs);
        //chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        setDriverBrowserLogging(chromeOptions);
        result = new ChromeDriver(chromeOptions);
        if (!headless) {
            result.manage().window().setSize(new Dimension(1920, 1080));
        }
        break;
    }
    return result;
    }

    private void setDriverBrowserLogging(MutableCapabilities mc){
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        logs.enable(LogType.PERFORMANCE, Level.ALL);
        mc.setCapability("goog:loggingPrefs",logs);
    }
    private String getDriverLocation(String browser){
        String driverLocation = null;
        logger_DriverFactory.info("OS: " + System.getProperty("os.name"));
        String osLowerCase = StringUtils.deleteWhitespace(System.getProperty("os.name").toLowerCase());

        if(browser.equalsIgnoreCase("firefox")) {
            if (osLowerCase.contains("windows")) {
                driverLocation = new File("./src/test/resources/drivers/geckodriver.exe").getAbsolutePath();
            }
        }
        else if(browser.equalsIgnoreCase("chrome")){
            if (osLowerCase.contains("windows")) {
                driverLocation = new File("./src/test/resources/drivers/chromedriver.exe").getAbsolutePath();
            }
        }
        else if(browser.equalsIgnoreCase("edge")){
                if (osLowerCase.contains("windows")) {
                    driverLocation = new File("./src/test/resources/drivers/msedgedriver.exe").getAbsolutePath();
                }
            }
        return driverLocation;
    }
}
