package controller;

import util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ValidationController extends ElementController{

  public void doesElementTextEqualsText(By by, String expectedText) {
    WebElement element = getElement(by);
    if (!element.getText().equals(expectedText)) {
      Logger.assertWithStatus(element + " text does not equal to " + expectedText
          + " || Actual value : " + element.getText()
          + " || Expected value : " + expectedText);
    }
  }

  public void doesElementTextContainText(By by, String expectedText) {
    WebElement element = getElement(by);
    if (!element.getText().contains(expectedText)) {
      Logger.assertWithStatus(element + " text does not contain to " + expectedText
          + " || Actual value : " + element.getText()
          + " || Expected value : " + expectedText);
    }
  }
}
