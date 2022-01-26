package pages;

import io.qameta.allure.Step;

public class BrowserPage extends BaseModel {

  @Step("Open url")
  public BrowserPage openUrl(String url) {
    actions.goToUrl(url);
    return this;
  }
}
