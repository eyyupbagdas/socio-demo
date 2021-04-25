package pages;

import org.openqa.selenium.By;
import util.Constants;

public class HomePage extends BaseModel {

  private final By DASH_TITLE = By.xpath(getDataTestId("titleDashead"));
  private final By NAVBAR_EVENT_APP = By.id("event-app-nav");
  private final By EVENT_APP_MY_EVENTS = By.xpath("//*[@title=\""+Constants.MY_EVENTS+"\"]");

  public MyEventsPage openMyEvents() {
    if (!actions.getText(DASH_TITLE).equals(Constants.MY_EVENTS)) {
      actions.clickElement(NAVBAR_EVENT_APP);
      actions.clickElement(EVENT_APP_MY_EVENTS);
    }
    return new MyEventsPage();
  }

}
