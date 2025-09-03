class Calculator {
    fun calculate(number1: Double, number2: Double, operation: Operations): Double {

        return when (operation) {
            Operations.ADDITION -> number1 + number2
            Operations.SUBTRACTION -> number1 - number2
            Operations.MULTIPLICATION -> number1 * number2
            Operations.DIVISION -> {
                if (number2 != 0.0) {
                    return number1 / number2
                } else throw ArithmeticException("❗Ошибка: Невозможно разделить на ноль")
            }
        }
    }
}