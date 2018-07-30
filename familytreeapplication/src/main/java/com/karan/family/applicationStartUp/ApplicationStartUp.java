package com.karan.family.applicationStartUp;

import com.karan.family.controller.FamilyController;
import com.karan.family.exception.CustomException;
import java.util.Scanner;

/**
 * This class is used as a startup service which will invoke controller based on specific request.
 */
public class ApplicationStartUp {

  public static void main(String inputs[]) {
    getInstructions();

    while (true) {
      Scanner sc = new Scanner(System.in);
      FamilyController familyController = new FamilyController();
      switch (sc.nextLine()) {
        case "Display":
          try {
            System.out.println(familyController.displayRelation());
            getInstructions();
          } catch (CustomException e) {
            System.out.println(e.getMessage());
            getInstructions();
          }
          break;

        case "Add":
          try {
            System.out.println(familyController.addRelation());
            getInstructions();
          } catch (CustomException e) {
            System.out.println(e.getMessage());
            getInstructions();
          }
          break;

        case "Exit":
          System.exit(0);
      }
    }
  }

  private static void getInstructions() {
    System.out.println("Please enter one of the choice from below");
    System.out.println("1. Type 'Display' to show relation ");
    System.out.println("2. Type 'Add' to assign a relation");
    System.out.println("3. Type 'Exit' to exit from program");
  }
}

