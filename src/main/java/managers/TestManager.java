package managers;

public class TestManager {

  private static ThreadLocal<String> testErrorMessage = new ThreadLocal<>();
  private static ThreadLocal<String> testStatus = new ThreadLocal<>();

  public static String getTestErrorMessage() {
    return testErrorMessage.get();
  }

  public static void setTestErrorMessage(String errorMessage) {
    testErrorMessage.set(errorMessage);
  }

  public static String getTestStatus() {
    return testStatus.get();
  }

  public static void setTestStatus(String status) {
    testStatus.set(status);
  }
}
