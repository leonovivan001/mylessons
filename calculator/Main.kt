import kotlin.concurrent.thread

fun main() {

    println("Введите первое число:")
    var num1 = createNum()
    println("Введите второе число:")
    var num2 = createNum()
    println("Введите тип операции: addition (сложение), subtraction (вычетание), multiplication (умножение), division (деление)")
    var operation = chooseOperation()
    val num3 = Calculator()
    println("Окей, задача ясна: $num1 ${operation.rusName} $num2, считаю...")
    println()
    Thread.sleep(900)

    try {
        println("✅Готово! Ответ: ${num3.calculate(num1, num2, operation)}")
        Thread.sleep(900)
    } catch (e: ArithmeticException) {
        println(e.message)
        Thread.sleep(900)
    }

}

fun createNum(): Double {
    while (true) {
        try {
            return checkNumber(readln().trim())
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

fun chooseOperation(): Operations {
    while (true) {
        try {
            return checkOperation(readln().uppercase().trim())
        } catch (e: OperationsException) {
            println(e.message)
        }
    }
}

fun checkNumber(num: String): Double {
    if ((num.matches(Regex("^\\d+$")))) {
        return num.toDouble()
    } else throw Exception("❗Ошибка: Вводить можно только цифры.")
}

fun checkOperation(text: String): Operations {
    if (!(text.matches(Regex("^\\d+$")))) {
        return when (text) {
            "ADDITION" -> Operations.ADDITION
            "SUBTRACTION" -> Operations.SUBTRACTION
            "MULTIPLICATION" -> Operations.MULTIPLICATION
            "DIVISION" -> Operations.DIVISION
            else -> throw OperationsException("❗Ошибка: Неверное название операции.")
        }
    } else throw OperationsException("❗Ошибка: Неверное название операции.")
}