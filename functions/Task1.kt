// В функции calculateRectangleArea я не сохранял результат умножения в промежуточную
// переменную, а сразу передал ее значение из функции как возвращаемое, упростив код.
// И ввел еще Null защиту, потренировался)

fun main () {

    var x: String = readln().ifBlank {
        println("Вы ничего не ввели.")
        return
    }
    if (x.any{it.isLetter()}) {
        println("Ошибка: Вы ввели не число.")
        return
    }
    var width = x.toDouble()

    var y = readln().ifBlank {
        println("Вы ничего не ввели.")
        return
    }
    if (y.any{it.isLetter()}) {
        println("Ошибка: Вы ввели не число.")
        return
    }
    var height = y.toDouble()

    printAreaInfo(calculateRectangleArea(width, height))
}

fun calculateRectangleArea(width: Double, height: Double): Double = width * height
fun printAreaInfo (area: Double) = println("Площадь прямоугольника равна $area")