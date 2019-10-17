import com.sample.math.OperationMath

class Calculator {

    static void main(String[] args) {
        def number1 = 75.0
        def number2 = 3.00
        println("Expression = $number1/$number2")
        def result = String.format("%.2f", OperationMath.divide(number1, number2))
        println("Result = $result")
    }
}
