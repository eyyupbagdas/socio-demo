package util;

import controller.ServiceController;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import managers.DriverManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class.getName());

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.log(Level.INFO, "{} is started.", getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        logger.log(Level.INFO, "{} is finished.{} Test result is SUCCESS{}", getTestMethodName(iTestResult), ANSI_GREEN, ANSI_RESET);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        logger.log(Level.ERROR,  "{} is finished.{} Test result is FAILURE{}", getTestMethodName(iTestResult), ANSI_RED, ANSI_RESET);
        saveScreenshot(DriverManager.getDriver());
        Allure.descriptionHtml("<!DOCTYPE html>" +
                "<html>" +
                "<body>" +
                "<video width='400' controls>" +
                "<source src='"+new ServiceController().getVideoUrl(DriverManager.getSessionId())+"' type='video/mp4'>" +
                "</video>" +
                "</body>" +
                "</html>");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        logger.log(Level.WARN, "{} is finished.{} Test result is SKIPPED{}", getTestMethodName(iTestResult), ANSI_YELLOW, ANSI_RESET);
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
