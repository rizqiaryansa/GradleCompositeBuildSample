import java.util.*

class Calculator {
    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val exp = "1.25 + 2 * 3 + 4 / 5 - 6 + ( 5 * 1 + 9 - 7 / 5 )"
            println("Expression = $exp")
            val result = String.format("%.2f", StringEvaluator.evaluate(exp))
            println("Result = $result")
        }
    }
}