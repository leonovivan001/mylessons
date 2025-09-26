class Menu {
    fun showMainMenu(): Int {
        menuMessage()
        val choice = textReadln()
        if (choice == 100) {
            println("Ошибка, введите цифру от 1 до 4")
            return 100
        }
        return choice
    }
}

fun menuMessage() {
    println()
    println(
        "Выберите действие:\n" +
        "1. Показать все товары\n" +
        "2. Создать новый товар\n" +
        "3. Купить товар\n" +
        "4. Показать журнал продаж\n" +
        "______________________\n" +
        "0. Выйти из приложения"

    )
}
fun textReadln(): Int {
    val textReadln = readln().trim()
    if (!(textReadln.matches(Regex("^\\d+$"))
        && textReadln.toInt() in 0..4)) {
        return 100
    }
    return textReadln.toInt()
}


