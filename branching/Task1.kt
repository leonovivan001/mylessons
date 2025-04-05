fun main () {

    val score = 93

    print("Ваш результат: ")
    if (score in 1..25) println("Плохо")
    else if (score in 26..50) println("Ниже среднего")
    else if (score in 51..75) println("Выше среднего")
    else if (score in 76..100) println("Отлично!")
    else println("Ошибка")

}