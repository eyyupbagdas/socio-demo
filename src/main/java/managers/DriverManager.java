package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
  private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

  public static WebDriver getDriver() {
    return webDriver.get();
  }

  public static void setWebDriver(WebDriver driver) {
    webDriver.set(driver);
  }

  public static void quitDriver() {
    webDriver.get().quit();
  }

  public static String getSessionId() {
    return String.valueOf(((RemoteWebDriver) webDriver.get()).getSessionId());
  }
}
