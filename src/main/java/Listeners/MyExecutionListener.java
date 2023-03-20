package Listeners;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MyExecutionListener extends RunListener {
    //Start and End time of the tests
    long startTime;
    long endTime;

    private WebDriver driver;

    @Override
    public void testRunStarted(Description description)
    {
        //Start time of the tests
        startTime = new Date().getTime();
        //Print the number of tests before the all tests execution
        System.out.println("Tests started! Number of test cases: " + description.testCount() + "\n");
    }

    @Override
    public void testRunFinished(Result result) throws Exception
    {
        //End time of the tests
        endTime = new Date().getTime();
        //Print the below lines when all tests are finished
        System.out.println("Tests finished! Number of test cases: " + result.getRunCount());
        long elapsedSeconds = (endTime - startTime) / 1000;
        System.out.println("Elapsed time of test execuation: " + elapsedSeconds + " seconds");
    }

    @Override
    public void testStarted(Description description)
    {
        //Write the test name when it starts
        System.out.println(description.getMethodName() + " test is starting...");
    }

    @Override
    public void testFinished(Description description)
    {
        //Write the test name when it ends
        System.out.println(description.getMethodName() + " test is finished...");
    }
    @Override
    public void testFailure(Failure failure) throws IOException {

        //Write the test name when it fails
        System.out.println(failure.getDescription().getMethodName() + " test FAILED!");
        //TODO: Add screenshot


        Date currentDate = new Date();
        String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":","-");
        System.out.println(screenshotFileName);

        //Take a screenshot
        File screnshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screnshotFile, new File(".//screenshot//" + screenshotFileName+ ".png"));

    }

    @Override
    public void testIgnored(Description description) throws Exception
    {
        super.testIgnored(description);
        Ignore ignore = description.getAnnotation(Ignore.class);
        String ignoreMessage = String.format("@Ignore test method '%s()': '%s'", description.getMethodName(), ignore.value());
        System.out.println(ignoreMessage + "\n");
    }
}
