package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
  Properties properties;

  public Properties getProperties() {
    properties = new Properties();
    FileInputStream configFile = null;
    try {
      configFile = new FileInputStream(System.getProperty("user.dir") + "//config.properties");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      properties.load(configFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties;
  }

  public String getUsername() {
    return getProperties().getProperty("username");
  }

  public String getPassword() {
    return getProperties().getProperty("password");
  }

  public String getSocioMail() {
    return getProperties().getProperty("socio_mail");
  }

  public String getSocioPass() {
    return getProperties().getProperty("socio_pass");
  }
}
