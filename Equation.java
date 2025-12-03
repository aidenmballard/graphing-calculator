package GraphingCalculator.src;

// Import statements
import java.util.ArrayList;
import java.util.List;

/**
 * Aiden Ballard @ 11/19/2025
 * This class is used to represent an equation, it contains the string
 * of the equation, as well as the token.
 */
public class Equation {

    // Defines the equation and the list of tokens
    public String equation;
    private ArrayList<Token> tokens;
    private ArrayList<Token> simplifiedTokens = new ArrayList<>();

    // Assigns the equation and tokenizes it
    public Equation(String equation) {
        this.equation = equation;
        this.tokens = new Tokenizer().tokenize(equation);
        System.out.println(tokens);
        simplify(tokens);
    }

    public void simplify(List<Token> tokens) {
        double coefficient = 0;
        int index = 0;
        int operationIndex = 0;
        while (tokens.size() > 1) {
            Token token = tokens.get(index);
            if (token.type.equals("OPERATION")) {
                if (token.value.equals("+")) {
                    tokens.set(index - 1, new Token("NUMBER",
                            Double.toString(Double.parseDouble(tokens.get(index - 1).value)
                                    + Double.parseDouble(tokens.get(index + 1).value))));
                    tokens.remove(index + 1);
                    tokens.remove(index);
                }
            }

            index++;
            if (index == tokens.size()) {
                index = 0;
                operationIndex++;
            }
        }
        System.out.println(tokens);
        coefficient = Double.parseDouble(tokens.get(0).value);
        System.out.println(coefficient);
    }

    // Prints the equation and the tokens the equation is made up of
    @Override
    public String toString() {
        System.out.println(equation);
        for (Token token : tokens)
            System.out.println(token);
        return "";
    }
}
