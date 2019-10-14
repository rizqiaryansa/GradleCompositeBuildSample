package com.example.math

import java.util.*

class StringEvaluator {
    companion object {

        private val operators = arrayOf('+', '-', '*', '/')

        fun evaluate(expressions: String): Double {
            val tokens = expressions.replace(" ", "").toCharArray()

            val values = Stack<Double>()

            val operators = Stack<Char>()

            var i = 0
            while (i < tokens.size) {
                if (tokens[i] in '0'..'9') {
                    val stringBuilder = StringBuilder()
                    while (i < tokens.size && (tokens[i] in '0'..'9' || tokens[i] == '.')) {
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
                } else if (tokens[i] in StringEvaluator.operators) {
                    while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())) {
                        values.push(applyOperation(values.pop(), values.pop(), operators.pop()))
                    }
                    operators.push(tokens[i++])
                } else {
                    i++
                }
            }

            while (!operators.empty()) {
                values.push(applyOperation(values.pop(), values.pop(), operators.pop()))
            }

            return values.pop()
        }

        private fun applyOperation(a: Double, b: Double, operator: Char) = when (operator) {
            '+' -> Operation.add(b, a)
            '-' -> Operation.substract(b, a)
            '*' -> Operation.multiply(b, a)
            '/' -> Operation.divide(b, a)
            else -> throw IllegalArgumentException("Illegal Operation")
        }

        private fun hasPrecedence(a: Char, b: Char): Boolean {
            return if (b == '(' || b == ')') {
                false
            } else !((a == '*' || a == '/') && (b == '+' || b == '-'))
        }
    }
}