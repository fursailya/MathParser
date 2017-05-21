package furs.calculator;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        MathParser parser = new MathParser();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter expression, you want to calculate: ");
        String expression = scanner.nextLine();
        System.out.print("\nResult: " + parser.calculateExpression(expression));

    }
}
