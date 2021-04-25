package factories;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.Constants;

public class DriverFactory {
  public static WebDriver createInstance(String config_file, String environment, Method method)
      throws IOException, ParseException {
    PropertyConfigurator.configure(Constants.LOG4J_PATH);
    JSONParser parser = new JSONParser();
    JSONObject config =
        (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + config_file));
    JSONObject envs = (JSONObject) config.get("environments");

    DesiredCapabilities capabilities = new DesiredCapabilities();

    Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
    Iterator it = envCapabilities.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
      if (pair.getValue().toString().equals("chrome")) {

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        //0 is default , 1 is enable and 2 is disable
        prefs.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));

        options.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      }
    }

    Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
    it = commonCapabilities.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      if (capabilities.getCapability(pair.getKey().toString()) == null) {
        capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
      }
    }

    capabilities.setCapability("name", method.getName());
    capabilities.setCapability("resolution", "1920x1080");

    String username = System.getenv("BROWSERSTACK_USERNAME");
    if (username == null) {
      username = (String) config.get("user");
    }

    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    if (accessKey == null) {
      accessKey = (String) config.get("key");
    }

    return new RemoteWebDriver(
        new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"),
        capabilities);
  }

  private static Map<String,Object> getClipBoardSettingsMap(int settingValue) {
    Map<String,Object> map = new HashMap<>();
    map.put("last_modified",String.valueOf(System.currentTimeMillis()));
    map.put("setting", settingValue);
    Map<String,Object> cbPreference = new HashMap<>();
    cbPreference.put("[*.],*",map);
    return cbPreference;
  }
}
