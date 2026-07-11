package com.random;

import java.util.Stack;

public class ValidateExpression {
    public static void main(String[] args) {
        ValidateExpression sol = new ValidateExpression();

        // FALSE cases
        System.out.println(sol.isValid("}"));        // false
        System.out.println(sol.isValid("}*"));       // false
        System.out.println(sol.isValid("{}}"));      // false
        System.out.println(sol.isValid("{{}"));      // false
        System.out.println(sol.isValid("{{**}"));    // false

        System.out.println("---");

        // TRUE cases
        System.out.println(sol.isValid("{}"));       // true
        System.out.println(sol.isValid("{*}"));      // true
        System.out.println(sol.isValid("*"));        // true
        System.out.println(sol.isValid("**"));       // true
        System.out.println(sol.isValid("{{**}}"));  // true
    }

    private boolean isValid(String input) {
        Stack<Integer> openStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        for (int i = 0 ; i < input.length() ; i++) {
            char c = input.charAt(i);
            if (c == '{') {
                openStack.push(i);
            }
            else if (c == '*') {
                starStack.push(i);
            } else {
                if (!openStack.isEmpty()) {
                    openStack.pop();
                }
                else if (!starStack.isEmpty()) {
                    starStack.pop();
                }
                else return false;
            }
        }

        while (!openStack.isEmpty() && !starStack.isEmpty()) {
            int openIndex = openStack.pop();
            int starIndex = starStack.pop();

            if (openIndex > starIndex) return false;
        }
        return openStack.isEmpty();
    }
}
