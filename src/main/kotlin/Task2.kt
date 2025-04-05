fun main () {

    //1. В этом же проекте создай файл с именем Task2.kt, а в нем класс Task2. (тут не понял как внутри файла создать класс)

    val income: Double = 339_723.00
    var taxRate = 1
    var tax: Double

    println("Выберите название организации:")
    println("1 - Сбербанк")
    println("2 - ГосМедицина")
    println("3 - Автоваз")
    var industry = readln().toString()
    when (industry) {

        "1" -> {
            when {
                income <= 249999.0 -> taxRate = 10
                income in 250000.0..499999.0 -> taxRate = 15
                income >= 500000.0 -> taxRate = 18

            }
            industry = "Сбербанк"
        }

        "2" -> {
            when {
                income <= 399999.0 -> taxRate = 7
                income in 400000.0..899999.0 -> taxRate = 12
                income >= 900000.0 -> taxRate = 15
            }
            industry = "ГосМедицина"
        }

        "3"-> {
            when {
                income <= 99000.0 -> taxRate = 9
                income in 100000.0..299999.0 -> taxRate = 13
                income >= 300000.0 -> taxRate = 17
            }
            industry = "Автоваз"
        }
        else -> {
            println("Ошибка")
            return
        }
    }

    tax = (income*taxRate)/100
    println("Поздравляем! Налог на сумму $income рублей для отрасли \"$industry\" составляет всего $tax рублей. Это буквально $taxRate процентов, вполне себе!")

}

