fun main () {

    var userBalance = 30000.00
    val withdrawLimit: Double = 50000.00 // — лимит снятия денег с банкомата
    var withdrawAmount: Double

        println()
        println("Введите сумму кратно 100:")
    val specialCharacters = ".,-+=<>!\"\'№;%:?*()!@#$%^&*(){}[]|"
    var howMuch = readln()  // — сумма, которую пользователь хочет снять

    if ((!howMuch.any { it.isLetter() }) && (!howMuch.any { it in specialCharacters })) {

        withdrawAmount = howMuch.toDouble()      //желаемая сумма для снятия

        if (userBalance < withdrawAmount) println("Ошибка: недостаточно средств")
        else if (withdrawAmount >= withdrawLimit) println("Ошибка: превышен лимит, уменьшите сумму запроса")
        else if ((withdrawAmount % 100).toInt() != 0) println("Ошибка: сумма должна быть кратна 100")
        else {
            userBalance = userBalance - withdrawAmount
            println("Операция успешно завершена! Ваш баланс: $userBalance")
        }
    } else println("Ошибка, сумма указывается только в цифрах кратно 100")

}
