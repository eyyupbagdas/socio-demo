package pages;

import controller.ActionController;
import controller.ValidationController;
import factories.ControllerFactory;

public class BaseModel {

  protected ActionController actions;
  protected ValidationController validations;

  public BaseModel() {
    ControllerFactory controllerFactory = new ControllerFactory();
    actions = controllerFactory.getActionController();
    validations = controllerFactory.getValidationController();
  }

  public String getDataTestId(String dataTestIdValue) {
    return "//*[@data-testid=\""+dataTestIdValue+"\"]";
  }
}
