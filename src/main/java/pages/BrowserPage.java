package pages;

public class BrowserPage extends BaseModel {

  public BrowserPage openUrl(String url) {
    actions.goToUrl(url);
    return this;
  }
}
