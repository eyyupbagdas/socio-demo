package factories;

import controller.ActionController;
import controller.ValidationController;

public class ControllerFactory {
  public ActionController getActionController() { return new ActionController(); }

  public ValidationController getValidationController() {
    return new ValidationController();
  }
}
