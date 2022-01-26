package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BaseModel {

  private final By LOGIN_LINK_BUTTON = By.xpath(getDataTestId("linkLogin"));
  private final By EMAIL_INPUT_FIELD = By.xpath(getDataTestId("inputEmail"));
  private final By PASSWORD_INPUT_FIELD = By.xpath(getDataTestId("inputPassword"));
  private final By LOGIN_BUTTON = By.xpath(getDataTestId("btnLoginaa"));

  @Step("Login with credentials")
  public HomePage loginWithCredentials(String mail, String pass) {
    actions.clickElement(LOGIN_LINK_BUTTON);
    actions.setText(EMAIL_INPUT_FIELD, mail);
    actions.setText(PASSWORD_INPUT_FIELD, pass);
    actions.clickElement(LOGIN_BUTTON);
    return new HomePage();
  }
}
