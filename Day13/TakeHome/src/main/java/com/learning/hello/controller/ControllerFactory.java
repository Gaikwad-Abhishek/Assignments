package com.learning.hello.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

  private static Map<String, IController> controllers;
  static {
    controllers = new HashMap<>();
//    controllers.put("/odometer", new OdometerControllerNew());
//    controllers.put("/mankatha/register", new MankathaRegistration());
//    controllers.put("/mankatha/reset", new MankathaReset());
//    controllers.put("/mankatha/play", new MankathaPlay());
      controllers.put("/notices/add", new NoticesAdd());
      controllers.put("/notices/display", new NoticesDisplay());
  }
  
  public static IController get(String path) {
    return controllers.get(path);
  }
  
}