package com.browserstack;

import controller.OperationController;
import pages.BrowserPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyEventsPage;
import pages.WebAppPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import util.Constants;

public class DemoTest extends BrowserStackTestNGTest {

  @Test
  public void postToWebAppWall() {
    String socioMail = configuration.getSocioMail();
    String socioPass = configuration.getSocioPass();

    BrowserPage browserPage = new BrowserPage();
    browserPage.openUrl(Constants.URL);

    LoginPage loginPage = new LoginPage();
    HomePage homePage =
        loginPage.loginWithCredentials(socioMail, socioPass);

    MyEventsPage myEventsPage = homePage.openMyEvents();
    String webAppLink = myEventsPage
        .openEvent(Constants.MY_EVENT_NAME)
        .openEventWebAppTab()
        .enableWebApp()
//        .selectLogInPrivacySetting()
        .saveWebApp()
        .copyWebAppLink();

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
}