package com.browserstack;

import controller.ServiceController;
import factories.DriverFactory;
import java.lang.reflect.Method;

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

  @Description("<!DOCTYPE html> \n" +
          "<html> \n" +
          "<body> \n" +
          "\n" +
          "<video width=\"400\" controls>\n" +
          "  <source src=\"https://automate.browserstack.com/sessions/3d2cf22661d1883c374d7d3a6ab1545ab195d029/video?token=VTUraVlySkxOMlNVK2JGOGxKNlZ3REd0aEx5ZVlHN3dhaCthcFhLRlVkbVBROFY4ZzR0MEU0bnNoallWOUMwdTZOR3E2WWJja093WEJObFJWaWNYN1E9PS0teDhqNVRnc25sY3Z4eHUxWWpBbWNBZz09--945d64ebef5c4d1a7997404e4e462180dc76ed29&source=rest_api&diff=1342.738433125\" type=\"video/mp4\">\n" +
          "</video>\n" +
          "\n" +
          "</body> \n" +
          "</html>")
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
