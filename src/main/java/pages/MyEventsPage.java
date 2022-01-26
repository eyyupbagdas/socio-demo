package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import util.Constants;

public class MyEventsPage extends BaseModel {

  public MyEventsPageAssertions assertions() {
    return new MyEventsPageAssertions();
  }

  private final By EVENT_NAMES = By.xpath("//*[@data-testid=\"colEvent\"] //h3");
  private final By EVENT_WEB_APP_TAB =
      By.xpath("//*[@id=\"rs-wrapper\"]/aside //*[contains(text(),'Web App')]");
  private final By EDIT_EVENT_TAB =
      By.xpath("//*[@id=\"rs-wrapper\"]/aside //*[contains(text(),'Edit')]");
  private final By ENABLE_WEB_APP_TOGGLE =
      By.xpath("//*[contains(@class,'bootstrap-switch wrapper')]");
  private final By PRIVACY_SETTINGS_LOGIN =
      By.xpath("//*[contains(@class,'radio-custom')] //*[contains(text(),'Log In')] /parent::label");
  private final By WEB_APP_SAVE_BUTTON = By.xpath("//*[@class=\"btn btn-success\"]");
  private final By WEB_APP_SHAREABLE_LINK_BUTTON =
      By.xpath("//*[contains(text(),'Get Shareable Link')]");
  private final By WEB_APP_COPY_LINK_BUTTON =
      By.xpath("//*[@class=\"modal-content\"]//*[contains(text(),'Copy Link')]");
  private final By WEB_APP_LINK =
      By.xpath("//*[@class=\"modal-content\"]//input");
  private final By CLOSE_MODAL = By.className("close");
  private final By WALL = By.id("Wall");
  private final By EDIT_WALL = By.xpath(getDataTestId("buttonEditFeature"));

  @Step("Open event")
  public MyEventsPage openEvent(String eventName) {
    actions.clickListElementWithText(EVENT_NAMES, eventName);
    return this;
  }

  @Step("Open event web app tab")
  public MyEventsPage openEventWebAppTab() {
    actions.waitForPageLoaded();
    actions.clickElement(EVENT_WEB_APP_TAB);
    return this;
  }

  public MyEventsPage openEditEventTab() {
    actions.clickElement(EDIT_EVENT_TAB);
    return this;
  }

  public MyEventsPage openEditWall() {
    actions.clickElement(WALL);
    actions.clickElement(EDIT_WALL);
    return this;
  }

  @Step("Enable web app")
  public MyEventsPage enableWebApp() {
    if (actions.getAttributeValue(ENABLE_WEB_APP_TOGGLE, Constants.CLASS)
        .contains("bootstrap-switch-off")) {
      actions.clickElement(ENABLE_WEB_APP_TOGGLE);
    }
    return this;
  }

  @Step("Select privacy setting")
  public MyEventsPage selectLogInPrivacySetting() {
    if (!actions.getAttributeValue(PRIVACY_SETTINGS_LOGIN, Constants.CLASS)
        .contains("radio-info")) {
      actions.clickElement(PRIVACY_SETTINGS_LOGIN);
    }
    return this;
  }

  @Step("Save web app")
  public MyEventsPage saveWebApp() {
    actions.clickElement(WEB_APP_SAVE_BUTTON);
    return this;
  }

  public String copyWebAppLink() {
    actions.clickElement(WEB_APP_SHAREABLE_LINK_BUTTON);
    actions.clickElement(WEB_APP_COPY_LINK_BUTTON);
    String url = actions.getAttributeValue(WEB_APP_LINK, "value");
    actions.clickElement(CLOSE_MODAL);
    return url;
  }
}
