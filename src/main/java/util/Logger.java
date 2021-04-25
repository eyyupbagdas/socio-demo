package util;

import managers.TestManager;
import org.testng.Assert;

public class Logger {

  private final static org.apache.log4j.Logger
      log = org.apache.log4j.Logger.getLogger(org.apache.log4j.Logger.class.getName());

  public static void assertWithStatus(String message) {
    TestManager.setTestStatus("failed");
    TestManager.setTestErrorMessage(message);
    log(message, LogType.ERROR, Logger.class.getName());
    Assert.fail(message);
  }

  public enum LogType {
    INFO,
    DEBUG,
    ERROR,
    FATAL
  }

  public static void log(Object message, LogType logType, String className) {
    message = logType.toString() + " => " + message.toString() + " || Class : " + className;
    logMessage(message, logType);
  }

  public static void log(Object message, LogType logType, String className, Exception e) {
    message = logType.toString() + " => " + message.toString() + " ERROR : " + e
        + " || Class : " + className;
    logMessage(message, logType);
  }

  private static void logMessage(Object message, LogType logType) {
    switch (logType) {
      case FATAL:
        log.fatal(message);
        break;
      case ERROR:
        log.error(message);
        break;
      case DEBUG:
        log.debug(message);
        break;
      case INFO:
        log.info(message);
        break;

    }
  }
}
