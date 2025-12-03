package GraphingCalculator.src;

// Import statements
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aiden Ballard @ 11/17/2025
 * This class is the main class of the program. It contains the code to have the
 * UI function and the menu options work
 */
public class App {
    // The main method of the program
    public static void main(String[] args) throws Exception {
        // The scanner that is used to get user input
        Scanner in = new Scanner(System.in);
        // The list of equations for the user
        ArrayList<Equation> equations = new ArrayList<Equation>();
        // The code of the operation that is being used
        int code = 0;

        // Loops until the exit code is entered
        while (code != equations.size() + 1) {
            // Prints out the options of what can be done
            System.out.println("0: Add an equation");
            for (Equation equation : equations)
                System.out.println(equations.indexOf(equation) + 1 + ": " + equation.equation);
            System.out.println(equations.size() + 1 + ": Exit");

            // Gets the new input code
            code = Integer.parseInt(in.nextLine());

            // Loops through adding equations until the user enters another exit code
            if (code == 0) {
                int code2 = 0;
                while (code2 != 1) {
                    System.out.println("0: Enter an equation:");
                    System.out.println("1: Go back");
                    code2 = Integer.parseInt(in.nextLine());
                    if (code2 == 0) {
                        System.out.println("Enter an equation:");
                        String equation = in.nextLine();
                        System.out.println(equation);
                        System.out.println("Is this correct? (y/n)");
                        System.out.println(equation);
                        String answer = in.nextLine();
                        if (answer.equals("y"))
                            equations.add(new Equation(equation));
                        else if (!answer.equals("n"))
                            System.out.println("Invalid input, loser.");
                    }
                    // Exits the equation adder
                    if (code2 == 1)
                        System.out.println("Going back...");
                }
            }

            // Quits the program if the user enters the exit code
            if (code == equations.size() + 1) {
                System.out.println("Exiting...");
                break;
            }
        }
    }
}