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
        String equation = in.nextLine();
        Equation eq = new Equation(equation);
    }
}