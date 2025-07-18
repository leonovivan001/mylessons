class Bathroom :Room(
    name = "Ванная комната",
    description = "Обычный совмещенный санузел.",
    items = mutableListOf(
        Furniture("Зеркало", "Круглое зеркало над раковиной, почти винтажное.", "Вы смотрите в зеркало, а зеркало смотрит в вас."),
        Window("Форточка", "Небольшая форточка для проветривание ванной", "Запотевшее, можно нарисовать сердечко"),
        Key("Ключ со стены в ванной", "Массивный ключ, вероятно запирает двери ванной изнутри"),
        Furniture("Раковина", "Белая керамическая раковина со следами зубной пасты.", "Твои ощущения: \"Холодная.\""),
        Note("Стикер со стиральной машины", "Выцвевший от времени стикер с какой-то инструкцией", "1 - Режим, 2 - Время, 3 - Старт. Если зависнет перезапустить."),
        Furniture("Стиральная машина", "Indesit 2010 года с функцией отжима", "Твои мысли: \"Люди раньше этим пользовались?\""),
        Furniture("Душевая кабина", "На вид стеклянная, но я легко узнаю дешевый пластик.", "Мысленно рассуждаешь: \"Возможно, позже, но не сейчас.\""),
        Values("Золотая статуэтка", "\"Оскар\" за лучшую игру года", "Наверняка стоит целое состояние"),
    ),
) {

    override fun printItems() {
        if (items.isNotEmpty()) {
            println()
            print("Предметов в комнате \"$name\": ${items.size}")
            print(items.withIndex().joinToString (separator = "") { "\n${it.index + 1}. ${it.value.name}"})
            println()
        } else println("$name полностью пуста")
    }
}
