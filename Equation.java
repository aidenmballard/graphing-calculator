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

    // Assigns the equation and tokenizes it
    public Equation(String equation) {
        this.equation = equation;
        this.tokens = new Tokenizer().tokenize(equation);
        // simplify(this.tokens);
    }

    public void simplify(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.value.equals("*")) {
                Token previousToken = tokens.get(tokens.indexOf(token) - 1);
                Token nextToken = tokens.get(tokens.indexOf(token) + 1);
                this.tokens.add(new Token("NUMBER",
                        String.valueOf(Integer.parseInt(previousToken.value) * Integer.parseInt(nextToken.value))));
            } else if (token.value.equals("/")) {
                Token previousToken = tokens.get(tokens.indexOf(token) - 1);
                Token nextToken = tokens.get(tokens.indexOf(token) + 1);
                this.tokens.add(new Token("NUMBER",
                        String.valueOf(Integer.parseInt(previousToken.value) / Integer.parseInt(nextToken.value))));
            } else if (token.value.equals("+")) {
                Token previousToken = tokens.get(tokens.indexOf(token) - 1);
                Token nextToken = tokens.get(tokens.indexOf(token) + 1);
                this.tokens.add(new Token("NUMBER",
                        String.valueOf(Integer.parseInt(previousToken.value) + Integer.parseInt(nextToken.value))));
            } else if (token.value.equals("-")) {
                Token previousToken = tokens.get(tokens.indexOf(token) - 1);
                Token nextToken = tokens.get(tokens.indexOf(token) + 1);
                this.tokens.add(new Token("NUMBER",
                        String.valueOf(Integer.parseInt(previousToken.value) - Integer.parseInt(nextToken.value))));
            }
        }
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
