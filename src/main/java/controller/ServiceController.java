package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import managers.TestManager;
import org.json.JSONObject;
import util.Configuration;
import util.Constants;
import util.Logger;

public class ServiceController {

  protected final Configuration configuration = new Configuration();

  /**
   * Set test status as "failed" or "passed" and error message
   * on browserstack dashboard
   * @param sessionId
   */
  public void setTestStatus(String sessionId) {
    JSONObject requestParams = new JSONObject();
    requestParams.put("status", TestManager.getTestStatus());
    requestParams.put("reason", TestManager.getTestErrorMessage());

    Response response = RestAssured.given()
        .auth()
        .basic(configuration.getUsername(), configuration.getPassword())
        .contentType("application/json")
        .body(requestParams.toString())
        .when()
        .put(Constants.API_URL + Constants.SESSIONS_PATH + sessionId + ".json");

    int statusCode = response.getStatusCode();

    if (statusCode != 200) {
      Logger.log("Test status could not set."
              + " || Status code : " + statusCode
              + " || Message : " + response.jsonPath().get("message")
          , Logger.LogType.ERROR, ServiceController.class.getName()
      );
    }
  }

  public String getVideoUrl(String sessionId) {

    Response response = RestAssured.given()
        .auth()
        .basic(configuration.getUsername(), configuration.getPassword())
        .contentType("application/json")
        .when()
        .get(Constants.API_URL + Constants.SESSIONS_PATH + sessionId + ".json");

      return response.jsonPath().get("automation_session.video_url").toString();
  }
}
