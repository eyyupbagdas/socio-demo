package controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import managers.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementController {

  public static final int TIME_OUT_IN_SECONDS = 30;
  private static final int DEFAULT_SLEEP_TIMEOUT = 2;
  private final WebDriverWait wait =
      new WebDriverWait(DriverManager.getDriver(), TIME_OUT_IN_SECONDS);

  protected WebElement getElement(By by) {
    try {
      return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    } catch (Exception e) {
      Logger.assertWithStatus("Could not find element || Element Locator =>" + by + " Error : " + e);
      return null;
    }
  }

  protected List<WebElement> getElements(By by) {
    try {
      getElement(by);
      return wait.ignoring(StaleElementReferenceException.class)
          .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    } catch (Exception e) {
      Logger.assertWithStatus("Could not find elements || Element Locator =>" + by + " Error : " + e);
      return null;
    }
  }

  protected WebElement getElementWithJsPath(String elementScript) {
    WebElement element = getJSElement(elementScript);
    if (element == null) {
      Logger.assertWithStatus("Could not find element with jspath => " + elementScript);
    }
    return element;
  }

  private WebElement getJSElement(String elementScript) {
    long currentTime = System.currentTimeMillis();
    long endTime = currentTime + TimeUnit.SECONDS.toMillis(TIME_OUT_IN_SECONDS);

    WebElement element = null;

    while (System.currentTimeMillis() <= endTime && element == null) {
      try {
        Thread.sleep(DEFAULT_SLEEP_TIMEOUT);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        element = (WebElement) ((JavascriptExecutor) DriverManager.getDriver())
            .executeScript("return " + elementScript);
      } catch (Exception e) {
        Logger.log("Trying to find js element || Js path => " + elementScript + " Error : " + e,
                Logger.LogType.INFO, ElementController.class.getName());
      }
    }
    return element;
  }
}
