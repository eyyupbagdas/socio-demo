package controller;

import java.util.List;

import io.qameta.allure.Step;
import managers.DriverManager;
import org.openqa.selenium.WebElement;
import util.Constants;
import util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionController extends ElementController{

  private String getClassName() {
    return ActionController.class.getName();
  }

  /**
   * Open given url
   * @param url
   */
  @Step("Go to url")
  public void goToUrl(String url) {
    try {
      DriverManager.getDriver().get(url);
      Logger.log(Constants.OPEN_URL + url, Logger.LogType.INFO, getClassName());
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_OPEN_URL + url + " Error : " + e);
    }
  }

  /**
   * Clicks element
   * @param by target element locator
   */
  @Step("Click element")
  public void clickElement(By by) {
    try {
      getElement(by).click();
      Logger.log(Constants.CLICK_ELEMENT + by, Logger.LogType.INFO, getClassName());
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_CLICK_ELEMENT + by + "Error : " +e);
    }
  }

  /**
   * Wait until element appear
   * @param by target element locator
   */
  @Step("Wait element appear")
  public void waitElementAppear(By by) {
    Logger.log(Constants.WAIT_ELEMENT + by, Logger.LogType.INFO, getClassName());
    getElement(by);
  }

  /**
   * Clicks element from list with text
   * @param by target element locator
   */
  public void clickListElementWithText(By by, String elementText) {
    try {
      List<WebElement> elements = getElements(by);
      elements.stream().filter(e -> e.getText().equals(elementText))
          .findFirst()
          .get()
          .click();

      Logger.log(Constants.CLICK_ELEMENT + by, Logger.LogType.INFO, getClassName());
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_CLICK_ELEMENT + by + "Error : " +e);
    }
  }

  /**
   * Set a text to element
   * @param by   target element locator
   * @param data given text
   */
  @Step("Fill input field")
  public void setText(By by, String data) {
    try {
      getElement(by).sendKeys(data);
      Logger.log(Constants.SEND_KEY_TO_ELEMENT + data, Logger.LogType.INFO, getClassName());
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_SEND_KEY_TO_ELEMENT + by + "Error : " +e);
    }
  }

  /**
   *
   * @param elementScript
   */
  public void clickToJsElement(String elementScript) {
    try {
      getElementWithJsPath(elementScript).click();
      Logger.log(Constants.CLICK_ELEMENT + elementScript, Logger.LogType.INFO, getClassName());
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_CLICK_ELEMENT + elementScript + "Error : " +e);
    }
  }

  /**
   * Get text from element
   * @param by target element locator
   * @return text of element
   */
  public String getText(By by) {
    try {
      Logger.log(Constants.GET_TEXT_OF_ELEMENT + by, Logger.LogType.INFO, getClassName());
      String text = getElement(by).getText();
      Logger.log(Constants.TEXT_OF_ELEMENT + text, Logger.LogType.INFO, getClassName());
      return text;
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_GET_TEXT_OF_ELEMENT + by + "Error : " +e);
      return null;
    }
  }

  /**
   * Get a value of an element attribute
   * @param by        target element locator
   * @param attribute given attribute of element
   * @return given attribute value of element
   */
  @Step("Get attribute value")
  public String getAttributeValue(By by, String attribute) {
    try {
      Logger.log(Constants.GET_TEXT_OF_ELEMENT + by, Logger.LogType.INFO, getClassName());
      String text = getElement(by).getAttribute(attribute);
      Logger.log(Constants.TEXT_OF_ELEMENT + text, Logger.LogType.INFO, getClassName());
      return text;
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_GET_ATTRIBUTE_OF_ELEMENT + by + "Error : " +e);
      return null;
    }
  }

  /**
   * Scroll to end of page
   *
   */
  public void scrollToEndOfPage() {
    try {
      Logger.log(Constants.SCROLL_TO_END, Logger.LogType.INFO, getClassName());
      ((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    } catch (Exception e) {
      Logger.assertWithStatus(Constants.COULD_NOT_SCROLL_TO_END + "Error : " +e);
    }
  }

  public void waitForPageLoaded() {
    ExpectedCondition<Boolean> expectation = new
        ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver driver) {
            return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                .toString().equals("complete");
          }
        };
    try {
      Thread.sleep(1000);
      WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 30);
      wait.until(expectation);
    } catch (Throwable error) {
      Assert.fail("Timeout waiting for Page Load Request to complete.");
    }
  }
}