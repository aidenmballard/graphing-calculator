package GraphingCalculator.src;

// Import statements
import java.util.ArrayList;

/**
 * Aiden Ballard @ 11/19/2025
 * This class is used to represent a token, it contains the type of the token
 * and the value of the token.
 */
class Token {
    // Defines the type and value of the token
    public String type;
    public String value;

    // Defines the possible digits and operations that a token could be
    public static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public static char[] operations = { '(', ')', '^', '*', '/', '+', '-' };

    // Constructor for the token class
    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    // Prints the type and value of the token if it has a value, otherwise just the
    // type
    @Override
    public String toString() {
        if (value != null)
            return "Token(" + type + ", " + value + ")";
        return "Token(" + type + ")";
    }
}

/**
 * Aiden Ballard @ 11/19/2025
 * This class is used to create tokens. It passes in the equation, takes each
 * part,
 * and assigns a token.
 */
public class Tokenizer {
    // Creates the tokenize method
    public ArrayList<Token> tokenize(String equation) {
        // Ceates an empty list of the tokens and the empty number string
        ArrayList<Token> tokens = new ArrayList<Token>();
        String number = "";

        // Loops through each character in the equation
        for (int i = 0; i < equation.length(); i++) {
            // Checks if the current character is an operation
            if (contains(Token.operations, equation.charAt(i)))
                tokens.add(new Token("OPERATION", Character.toString(equation.charAt(i))));
            // Checks if the current character could be part of a number
            else if (contains(Token.digits, equation.charAt(i)) || equation.charAt(i) == '.') {
                number += equation.charAt(i);
                // Ends the number if the next character could not be part of a number
                if (i == equation.length() - 1
                        || (equation.charAt(i + 1) != '.' && !contains(Token.digits, equation.charAt(i + 1)))) {
                    tokens.add(new Token("NUMBER", number));
                    number = "";
                }
                // Checks if the curent character is something to be a variable
            } else if (equation.charAt(i) != ' ')
                tokens.add(new Token("VARIABLE", Character.toString(equation.charAt(i))));
        }

        // Returns the token list
        return tokens;
    }

    // Checks if a character is in a list
    public boolean contains(char[] arr, char c) {
        for (char character : arr)
            if (character == c)
                return true;
        return false;
    }
}