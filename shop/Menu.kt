class Menu {
    fun showMainMenu(): Int {
        menuMessage()
        when (textReadln()) {
            1 -> return 1
            2 -> return 2
            3 -> return 3
            100 -> println("Ошибка, введите цифру от 1 до 3")
        }
        return 0
    }
}

fun menuMessage() {
    println()
    println(
        "Выберите действие:\n" +
        "1. Показать все товары\n" +
        "2. Создать новый товар\n" +
        "3. Выйти из приложения"
    )
}
fun textReadln(): Int {
    val textReadln = readln().trim()
    if (textReadln.matches(Regex("^\\d+$"))
        && textReadln.toInt() in 1..3) {
        return textReadln.toInt()
    }
    return 100
}


