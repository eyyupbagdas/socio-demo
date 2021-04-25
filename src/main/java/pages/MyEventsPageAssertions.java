package pages;

import org.openqa.selenium.By;

public class MyEventsPageAssertions extends BaseModel {

  private final By POST_NAME = By.xpath("//*[contains(@class,'post-caption')]/span");

  public void checkPostIsListed(String postText) {
    validations.doesElementTextEqualsText(POST_NAME, postText);
  }
}
