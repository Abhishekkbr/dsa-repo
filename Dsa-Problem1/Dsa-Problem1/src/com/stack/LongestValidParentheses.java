package com.stack;

import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));   // 4
        System.out.println(longestValidParentheses("(()"));      // 2
        System.out.println(longestValidParentheses(""));        // 0
    }

    private static int longestValidParentheses(String pattern) {
        Stack<Integer> st = new Stack<>();
        st.push(-1); // if first value is  "(" then no point to comes out
        int maxLength = 0;

        for (int i = 0 ; i < pattern.length() ; i++) {
            char ch = pattern.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    int currLength = i - st.peek();
                    maxLength = Math.max(maxLength, currLength);
                }
            }
        }
        return maxLength;
    }
}
