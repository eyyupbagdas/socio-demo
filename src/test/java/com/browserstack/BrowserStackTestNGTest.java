package com.browserstack;

import controller.ServiceController;
import factories.DriverFactory;
import java.lang.reflect.Method;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Description;
import managers.DriverManager;
import managers.TestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.Configuration;

public class BrowserStackTestNGTest {

  Configuration configuration = new Configuration();

  @BeforeMethod(alwaysRun = true)
  @org.testng.annotations.Parameters(value = {"config", "environment"})
  public void setUp(String config_file, String environment, Method method) throws Exception {
    WebDriver driver = DriverFactory.createInstance(config_file, environment, method);
    DriverManager.setWebDriver(driver);
    ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
    driver.manage().window().maximize();
    TestManager.setTestStatus("passed");
    TestManager.setTestErrorMessage(null);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    new ServiceController().setTestStatus(DriverManager.getSessionId());
    DriverManager.quitDriver();
  }
}
