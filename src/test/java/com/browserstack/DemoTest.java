package com.browserstack;

import controller.OperationController;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
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

  @Link("https://example.org")
  @Link(name = "Testrail Link", type = "testrail", value = "3000")
//  @Description("<!DOCTYPE html> \n" +
//          "<html> \n" +
//          "<body> \n" +
//          "\n" +
//          "<video width=\"400\" controls>\n" +
//          "  <source src=\"https://automate.browserstack.com/sessions/3d2cf22661d1883c374d7d3a6ab1545ab195d029/video?token=VTUraVlySkxOMlNVK2JGOGxKNlZ3REd0aEx5ZVlHN3dhaCthcFhLRlVkbVBROFY4ZzR0MEU0bnNoallWOUMwdTZOR3E2WWJja093WEJObFJWaWNYN1E9PS0teDhqNVRnc25sY3Z4eHUxWWpBbWNBZz09--945d64ebef5c4d1a7997404e4e462180dc76ed29&source=rest_api&diff=1342.738433125\" type=\"video/mp4\">\n" +
//          "</video>\n" +
//          "\n" +
//          "</body> \n" +
//          "</html>")
  @Test
  public void postToWebAppWall() {
    String socioMail = configuration.getSocioMail();
    String socioPass = configuration.getSocioPass();

    BrowserPage browserPage = new BrowserPage();
    browserPage.openUrl(Constants.URL);

    LoginPage loginPage = new LoginPage();
    HomePage homePage =
        loginPage.loginWithCredentials(socioMail, socioPass);

    AllureLifecycle allureLifecycle = new AllureLifecycle();
    allureLifecycle.updateTestCase(testResult -> testResult.setDescription("<!DOCTYPE html> \n" +
            "<html> \n" +
            "<body> \n" +
            "\n" +
            "<video width=\"400\" controls>\n" +
            "  <source src=\"https://automate.browserstack.com/sessions/3d2cf22661d1883c374d7d3a6ab1545ab195d029/video?token=VTUraVlySkxOMlNVK2JGOGxKNlZ3REd0aEx5ZVlHN3dhaCthcFhLRlVkbVBROFY4ZzR0MEU0bnNoallWOUMwdTZOR3E2WWJja093WEJObFJWaWNYN1E9PS0teDhqNVRnc25sY3Z4eHUxWWpBbWNBZz09--945d64ebef5c4d1a7997404e4e462180dc76ed29&source=rest_api&diff=1342.738433125\" type=\"video/mp4\">\n" +
            "</video>\n" +
            "\n" +
            "</body> \n" +
            "</html>"));

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
}
