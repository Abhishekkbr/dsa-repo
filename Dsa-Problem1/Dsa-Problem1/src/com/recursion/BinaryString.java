package com.recursion;

import java.util.ArrayList;
import java.util.List;

public class BinaryString {
   public static void main(String[] args) {

       List<String> result = new ArrayList<>();
       binaryStringLength3(result, "");
       System.out.println(result);
    }

    private static void binaryStringLength3(List<String> result, String current) {
       if (current.length() == 3) {
               result.add(current);
           return;
       }
       for (int i = 0; i < 2 ; i++) {
           String input = current + i;
           binaryStringLength3(result, input);
       }
    }
}
