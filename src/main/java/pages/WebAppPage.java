package pages;

import org.openqa.selenium.By;

public class WebAppPage extends BaseModel {

  private final By WEB_APP_LOGIN_EMAIL = By.id("email");
  private final By WEB_APP_LOGIN_CONTINUE = By.id("continue-button");
  private final By WEB_APP_LOGIN_PASSWORD = By.id("password-input");
  private final By WEB_APP_LOGIN_BUTTON = By.id("login-button");
  private final By WALL = By.xpath("//*[@data-testid=\"listItem\"]//*[contains(text(),'Wall')]");
  private final By WHATS_YOUR_MIND = By.xpath("//*[contains(text(),'your mind')]");
  private final By WHATS_YOUR_MIND_INPUT = By.xpath("//*[contains(@placeholder,'your mind')]");
  private final By WHATS_YOUR_MIND_UPLOAD_IMAGE = By.xpath(getDataTestId("upload_image"));
  private final By WHATS_YOUR_MIND_UPLOAD_IMAGE_INPUT = By.xpath("//input[@id='fsp-fileUpload']");
  private final By WHATS_YOUR_MIND_UPLOAD_IMAGE_SAVE = By.xpath("//*[@title=\"Save\"]");
  private final By WHATS_YOUR_MIND_UPLOAD_IMAGE_UPLOAD = By.xpath("//*[@title=\"Upload\"]");
  private final By WHATS_YOUR_MIND_SEND = By.xpath("(//*[contains(text(),'Send')])[2]");
  private final By WHATS_YOUR_MIND_NEW_POST = By.xpath("//*[@alt=\"new_post\"]");

  public WebAppPage loginToWebApp(String mail, String pass) {
    actions.setText(WEB_APP_LOGIN_EMAIL, mail);
    actions.clickElement(WEB_APP_LOGIN_CONTINUE);
    actions.setText(WEB_APP_LOGIN_PASSWORD, pass);
    actions.clickElement(WEB_APP_LOGIN_BUTTON);
    return this;
  }

  public WebAppPage openWall() {
    actions.clickElement(WALL);
    return this;
  }

  public WebAppPage sendToWall(String textMessage, String imagePath) {
    actions.clickElement(WHATS_YOUR_MIND);
    actions.setText(WHATS_YOUR_MIND_INPUT, textMessage);
    actions.clickElement(WHATS_YOUR_MIND_UPLOAD_IMAGE);
    actions.setText(WHATS_YOUR_MIND_UPLOAD_IMAGE_INPUT, imagePath);
    actions.clickElement(WHATS_YOUR_MIND_UPLOAD_IMAGE_SAVE);
    actions.clickElement(WHATS_YOUR_MIND_UPLOAD_IMAGE_UPLOAD);
    actions.waitElementAppear(WHATS_YOUR_MIND_NEW_POST);
    actions.clickElement(WHATS_YOUR_MIND_SEND);
    return this;
  }
}
