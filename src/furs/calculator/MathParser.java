package furs.calculator;

import java.util.LinkedList;

/**
 * @author Fursa Ilya
 * @email fursa.ilya@gmail.com
 */
public class MathParser {
    /**
     * @param c оператор представленый в виде символа
     *          метод возвращает true если символ является мат.операцией
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*' || c == '^' || c == '%';
    }

    /**
     * Определяем приоритет операции
     *
     * @param operation
     * @return
     */
    private int priorityOfOperation(char operation) {
        if (operation == '+' || operation == '-') {
            return 0;
        } else if (operation == '*' || operation == '/') {
            return 1;
        } else return -1;
    }

    /**
     * @param nums
     * @param operation берем значения из конца и
     *                  производим с ними заданную операцию
     */
    private void calculate(LinkedList<Double> nums, char operation) {
        double lastOne = nums.removeLast();
        double lastTwo = nums.removeLast();

        switch (operation) {
            case '+':
                nums.add(lastTwo + lastOne);
                break;
            case '-':
                nums.add(lastTwo - lastOne);
                break;
            case '/':
                nums.add(lastTwo / lastOne);
                break;
            case '*':
                nums.add(lastTwo * lastOne);
                break;
            case '^':
                nums.add(Math.pow(lastTwo, lastOne));
                break;
            case '%':
                nums.add(lastTwo % lastOne);
                break;
        }
    }


    /**
     * @param expression
     * @return парсим строку в выражение и считаем
     */
    public double calculateExpression(String expression) {
        LinkedList<Double> numbers = new LinkedList<>();
        LinkedList<Character> characters = new LinkedList<>();

        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == '(') {
                    characters.add('(');
                } else if (c == ')') {
                    while (characters.getLast() != '(') {
                        calculate(numbers, characters.removeLast());
                    }
                    characters.removeLast();
                } else if (isOperator(c)) {
                    while (!characters.isEmpty() && priorityOfOperation(characters.getLast()) >= priorityOfOperation(c)) {
                        calculate(numbers, characters.removeLast());
                    }

                    characters.add(c);
                } else {
                    String operand = "";

                    while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                        operand += expression.charAt(i++);
                    }
                    --i;
                    numbers.add(Double.valueOf(operand));
                }
            }

            while (!characters.isEmpty()) {
                calculate(numbers, characters.removeLast());
            }

        } catch (Exception e) {
            System.out.println("Oops! Something was wrong!!!");
        }

        return numbers.get(0);
    }
}
