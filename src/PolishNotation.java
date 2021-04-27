import java.util.ArrayDeque;
import java.util.Deque;

public class PolishNotation {

    public static void main(String[] args) {
        show("5 - 12 * ( 17 - 5 )");
    }

    static void show(String input) {
        String polishNotation = toPolishNotation(input);
        String result = parsePolishNotation(polishNotation);
        System.out.println(result);
    }

    static String toPolishNotation(String input) {
        String[] tokens = input.split(" ");
        StringBuilder sb = new StringBuilder();
        Deque < String > stack = new ArrayDeque < String > ();

        for (String token: tokens) {
            // Читаем очередной символ
            switch (token) {
                case "*":
                case "/":
                case "-":
                case "+":
                    // Если символ token является бинарной операцией, тогда:
                    // 1) пока на вершине стека префиксная функция…
                    //     … ИЛИ операция на вершине стека приоритетнее или с тем же приоритетом, что и token
                    //     … выталкиваем верхний элемент стека в выходную строку;
                    while (!stack.isEmpty()) {
                        String peekOp = stack.peek();
                        // выйти из цикла, если токен строго более приоритен, чем операция на вершине стека
                        if (token.equals("*") && (peekOp.equals("+") || peekOp.equals("-")) ||
                                token.equals("/") && (peekOp.equals("+") || peekOp.equals("-"))) {
                            break;
                        }
                        if (peekOp.equals("(") || peekOp.equals(")")) {
                            break;
                        }
                        sb.append(" ").append(stack.pop());
                    }
                    // 2) помещаем операцию o1 в стек.
                    stack.push(token);
                    break;
                case "(":
                    // Если символ является открывающей скобкой, помещаем его в стек.
                    stack.push(token);

                    break;
                case ")":
                    // Если символ является закрывающей скобкой:
                    // До тех пор, пока верхним элементом стека не станет открывающая скобка, выталкиваем элементы из стека в выходную строку.
                    // При этом открывающая скобка удаляется из стека, но в выходную строку не добавляется.
                    while (!stack.peek().equals("(")) {
                        sb.append(" ").append(stack.pop());
                    }
                    stack.pop();

                    break;
                default:
                    // Если символ является числом, добавляем его к выходной строке
                    if (sb.length() > 0) {
                        sb.append(" ");
                    }
                    sb.append(token);
                    break;
            }
        }
        // Когда входная строка закончилась, выталкиваем все символы из стека в выходную строку
        while (!stack.isEmpty()) {
            sb.append(" ").append(stack.pop());
        }
        return sb.toString();
    }

    static String parsePolishNotation(String input) {
        Deque < String > stack = new ArrayDeque < String > ();
        String[] tokens = input.split(" ");

        for (String token: tokens) {
            String stringResult;
            switch (token) {
                case "*":
                    int operationResult = Integer.valueOf(stack.pop()) * Integer.valueOf(stack.pop());
                    stringResult = String.valueOf(operationResult);
                    stack.push(stringResult);
                    break;
                case "/":
                    int first =Integer.valueOf(stack.pop());
                    int second =Integer.valueOf(stack.pop());
                    operationResult = second/first;
                    stringResult = String.valueOf(operationResult);
                    stack.push(stringResult);
                    break;
                case "-":
                    operationResult = -(Integer.valueOf(stack.pop()) - Integer.valueOf(stack.pop()));
                    stringResult = String.valueOf(operationResult);
                    stack.push(stringResult);
                    break;
                case "+":
                    operationResult = Integer.valueOf(stack.pop()) + Integer.valueOf(stack.pop());
                    stringResult = String.valueOf(operationResult);
                    stack.push(stringResult);
                    break;
                default:
                    stringResult = token;
                    stack.push(stringResult);
            }
        }

        return stack.pop();
    }

}
