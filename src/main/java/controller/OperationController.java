package controller;

import java.util.ArrayList;
import managers.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import util.Constants;
import util.Logger;

public class OperationController {

  private String getClassName() {
    return OperationController.class.getName();
  }

  /**
   * Open url in new window
   * @param url
   */
  public void openNewWindow(String url) {
    try {
      ((JavascriptExecutor) DriverManager.getDriver())
          .executeScript("window.open(arguments[0])", url);
      ArrayList<String> tabs = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
      DriverManager.getDriver().switchTo().window(tabs.get(1));
      Logger.log(Constants.OPEN_URL + url, Logger.LogType.INFO, getClassName());
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_OPEN_URL + url + " Error : " + e);
    }
  }

  /**
   * Switch to main window
   */
  public void switchToMainWindow() {
    try {
      DriverManager.getDriver().close();
      ArrayList<String> tabs = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
      DriverManager.getDriver().switchTo().window(tabs.get(0));
      Logger.log("Switch to main window", Logger.LogType.INFO, getClassName());
    } catch (Exception e) {
      Logger.assertWithStatus("Could not switch to main window ||" + " Error : " + e);
    }
  }
}
