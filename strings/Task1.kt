
fun main () {

var listOfNumbers = IntArray(5)

    var check = true
    val simbols = "[^A-Za-z0-9]".toRegex()
    println("Введите 5 чисел по одной на строку \nПосле ввода нажмите Enter:")
    for (i in listOfNumbers.indices) {
      val number = readln()
        if ((number.any {it.isLetter()}) || (simbols.containsMatchIn(number))){
            println("\nОшибка: вы ввели НЕ число. Программа закрывается...")
            check = false
            break
        }else listOfNumbers[i] = number.toInt()
    }
    if (check == true) {
        for (i in listOfNumbers.indices) {
            if ((listOfNumbers[i] % 2) == 0) println("Число ${listOfNumbers[i]} чётное") else println("Число ${listOfNumbers[i]} не чётное")
        }
    }
}
