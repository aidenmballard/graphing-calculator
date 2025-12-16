package GraphingCalculator.src;

// Import statements
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Aiden Ballard @ 11/19/2025
 * This class is used to represent an equation, it contains the string
 * of the equation, as well as the tokens.
 */
public class Equation {

    // Defines the equation and the list of tokens
    public String equation;
    private ArrayList<Token> tokens;
    HashMap<String, Double> variableValues;

    // Assigns the equation and tokenizes it
    public Equation(String equation) {
        this.equation = equation;
        this.tokens = new Tokenizer().tokenize(equation);
        this.variableValues = new HashMap<>();
        System.out.println(tokens);
        combineLikeTerms();
    }

    public void combineLikeTerms() {
        String variable = "";

        for (Token token : tokens) {
            if (token.type.equals("VARIABLE")) {
                if (tokens.indexOf(token) + 1 < tokens.size()
                        && tokens.get(tokens.indexOf(token) + 1).type.equals("OPERATION") &&
                        tokens.get(tokens.indexOf(token) + 1).value.equals("^")) {
                    variable = token.value + "^" + tokens.get(tokens.indexOf(token) + 2).value;
                } else {
                    variable = token.value;
                }

                double coefficient = 1.0;

                if (tokens.get(tokens.indexOf(token) - 1).type.equals("NUMBER")) {
                    coefficient = Double.parseDouble(tokens.get(tokens.indexOf(token) - 1).value);
                    if (tokens.get(tokens.indexOf(token) - 1).type.equals("OPERATION")
                            && tokens.get(tokens.indexOf(token) - 1).value.equals("-"))
                        coefficient *= -1.0;
                } else if (tokens.get(tokens.indexOf(token) - 1).type.equals("OPERATION")
                        && tokens.get(tokens.indexOf(token) - 1).value.equals("-")) {
                    coefficient *= -1.0;
                }

                if (variableValues.containsKey(variable))
                    variableValues.put(variable, variableValues.get(variable) + coefficient);
                else
                    variableValues.put(variable, coefficient);
            }
        }

        System.out.println("Combined Like Terms: " + variableValues);
    }

    // Simplifies an expression of numbers to one constant
    public double simplify(List<Token> tokens) {
        // Creates the coefficient value, index, and operation index
        double coefficient = 0;
        int index = 0, operationIndex = 0;

        // Loops until there is only one token left
        while (tokens.size() > 1) {
            // Gets the token at the current index and checks if it is an operation
            Token token = tokens.get(index);
            if (token.type.equals("OPERATION")) {
                // Checks if the current operation is the same as the operation at
                // operationIndex
                if (token.value.equals(String.valueOf(Token.operations[operationIndex])))
                    // Evaluates the operation in accordance to PEDMAS
                    if (token.value.equals("(")) {
                        // Finds the right parenthesis index
                        int rightParenIndex = 0;
                        for (int i = index + 1; i < tokens.size(); i++) {
                            if (tokens.get(i).value.equals(")")) {
                                rightParenIndex = i;
                                break;
                            }
                        }

                        // Simplifies the expression inside the parentheses and sets that value
                        List<Token> subTokens = tokens.subList(index + 1, rightParenIndex);
                        tokens.set(index + 1, new Token("NUMBER", Double.toString(simplify(subTokens))));
                    } else if (token.value.equals(")")) {
                        // Checks if the next charater is a number for implicit multiplication
                        if (index + 1 < tokens.size() && tokens.get(index + 1).type.equals("NUMBER")) {
                            tokens.set(index + 1, new Token("NUMBER",
                                    Double.toString(Double.parseDouble(tokens.get(index - 1).value)
                                            * Double.parseDouble(tokens.get(index + 1).value))));
                            tokens.remove(index);
                            tokens.remove(index - 1);
                            tokens.remove(index - 2);
                        } else {
                            tokens.remove(index);
                            tokens.remove(index - 2);
                        }
                        // Evaluates exponents
                    } else if (token.value.equals("^")) {
                        tokens.set(index - 1, new Token("NUMBER",
                                Double.toString(Math.pow(Double.parseDouble(tokens.get(index - 1).value),
                                        Double.parseDouble(tokens.get(index + 1).value)))));
                        tokens.remove(index + 1);
                        tokens.remove(index);
                        // Evaluates multiplication
                    } else if (token.value.equals("*")) {
                        tokens.set(index - 1, new Token("NUMBER",
                                Double.toString(Double.parseDouble(tokens.get(index - 1).value)
                                        * Double.parseDouble(tokens.get(index + 1).value))));
                        tokens.remove(index + 1);
                        tokens.remove(index);
                        // Evaluates division
                    } else if (token.value.equals("/")) {
                        tokens.set(index - 1, new Token("NUMBER",
                                Double.toString(Double.parseDouble(tokens.get(index - 1).value)
                                        / Double.parseDouble(tokens.get(index + 1).value))));
                        tokens.remove(index + 1);
                        tokens.remove(index);
                        // Evaluates addition
                    } else if (token.value.equals("+")) {
                        tokens.set(index - 1, new Token("NUMBER",
                                Double.toString(Double.parseDouble(tokens.get(index - 1).value)
                                        + Double.parseDouble(tokens.get(index + 1).value))));
                        tokens.remove(index + 1);
                        tokens.remove(index);
                        // Evaluates subtraction
                    } else if (token.value.equals("-")) {
                        tokens.set(index - 1, new Token("NUMBER",
                                Double.toString(Double.parseDouble(tokens.get(index - 1).value)
                                        - Double.parseDouble(tokens.get(index + 1).value))));
                        tokens.remove(index + 1);
                        tokens.remove(index);
                    }
            }

            // Incements the index and operation index appropriately
            index++;
            if (index >= tokens.size()) {
                index = 0;
                operationIndex++;
            }

            // Breaks the loop if all operations have been evaluated
            if (operationIndex >= Token.operations.length)
                break;
        }

        // Returns the final token, which is the simplified value
        System.out.println("Printing tokens: " + tokens);
        coefficient = Double.parseDouble(tokens.get(0).value);
        System.out.println(coefficient);
        return coefficient;
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
