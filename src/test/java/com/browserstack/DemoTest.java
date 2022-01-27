package com.browserstack;

import controller.OperationController;
import controller.ServiceController;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import managers.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import pages.BrowserPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyEventsPage;
import pages.WebAppPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import util.Constants;
import util.TestListener;

@Listeners({TestListener.class})
public class DemoTest extends BrowserStackTestNGTest {

  @Link(name = "Testrail Link", type = "testrail", value = "3000")
  @Test
  public void postToWebAppWall() {
    String socioMail = configuration.getSocioMail();
    String socioPass = configuration.getSocioPass();

    BrowserPage browserPage = new BrowserPage();
    browserPage.openUrl(Constants.URL);

//    LoginPage loginPage = new LoginPage();
//    HomePage homePage =
//        loginPage.loginWithCredentials(socioMail, socioPass);



//    MyEventsPage myEventsPage = homePage.openMyEvents();
//    myEventsPage
//        .openEvent(Constants.MY_EVENT_NAME)
//        .openEventWebAppTab();
//        .enableWebApp()
//        .selectLogInPrivacySetting()
//        .saveWebApp()
//        .copyWebAppLink();

//    OperationController operationController = new OperationController();
//    operationController.openNewWindow(webAppLink);
//
//    WebAppPage webAppPage = new WebAppPage();
//    String postMessage = RandomStringUtils.randomAlphabetic(10);
//    webAppPage
//        .loginToWebApp(socioMail, socioPass)
//        .openWall()
//        .sendToWall(postMessage, Constants.IMAGE_PATH);
//
//    operationController.switchToMainWindow();
//
//    myEventsPage.openEditEventTab().openEditWall();
//    myEventsPage.assertions().checkPostIsListed(postMessage);
  }


  @Link(name = "Testrail Link", type = "testrail", value = "3000")
  @Test
  public void test1() {
    String socioMail = configuration.getSocioMail();
    String socioPass = configuration.getSocioPass();

    BrowserPage browserPage = new BrowserPage();
    browserPage.openUrl(Constants.URL);

    LoginPage loginPage = new LoginPage();
    HomePage homePage =
        loginPage.loginWithCredentials(socioMail, socioPass);

//    Allure.descriptionHtml("<!DOCTYPE html> \n" +
//            "<html> \n" +
//            "<body> \n" +
//            "<video width=\"400\" controls>\n" +
//            "<source src=\""+new ServiceController().getVideoUrl(DriverManager.getSessionId())+"\" type=\"video/mp4\">\n" +
//            "</video>\n" +
//            "</body> \n" +
//            "</html>");
  }

}
