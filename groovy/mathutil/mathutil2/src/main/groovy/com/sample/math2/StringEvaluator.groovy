package com.sample.math2

class StringEvaluator {

    static def operators = Arrays.asList('+', '-', '*', '/')

    static Double evaluate(String expressions) {
        char[] tokens = expressions.replace(" ", "").toCharArray()

        def values = new Stack<Double>()

        def operators = new Stack<Character>()

        def i = 0
        while (i < tokens.size()) {
            if (tokens[i] >= 0 && tokens[i] <= 9) {
                def stringBuilder = new StringBuilder()
                while (i < tokens.size() && (tokens[i] >= 0 && tokens[i] <= 9)) {
                    stringBuilder.append(tokens[i++])
                }
                values.push(stringBuilder.toString().toDouble())
            } else if (tokens[i] == '(') {
                operators.push(tokens[i++])
            } else if (tokens[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperation(values.pop(), values.pop(), operators.pop()))
                }
                operators.pop()
                i++
            } else if (StringEvaluator.operators.contains(tokens[i])) {
                while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())) {
                    values.push(applyOperation(values.pop(), values.pop(), operators.pop()))
                }
                operators.push(tokens[i++])
            }
        }

        while (!operators.empty()) {
            values.push(applyOperation(values.pop(), values.pop(), operators.pop()))
        }

        return values.pop()
    }

    private static Double applyOperation(Double a, Double b, Character operator) {

        switch (operator) {
            case '+':
                OperationMath.add(b, a)
                break
            case '-': OperationMath.subtracts(b, a)
                break
            case '*': OperationMath.multiply(b, a)
                break
            case '/': OperationMath.divide(b, a)
                break
            default:
                throw new IllegalArgumentException("Illegal com.example.math.Operation")
                break
        }
    }

    private static Boolean hasPrecedence(Character a, Character b) {

        if (b == '(' || b == ')') {
            return false
        } else {
            return !((a == '*' || a == '/') && (b == '+' || b == '-'))
        }
    }
}
