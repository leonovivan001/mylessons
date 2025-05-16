class Kitchen(
    name: String,
    description: String = "",
    items: Array<Item> = arrayOf(
        Item("Плита", "Плита газовая, белорусская. Выглядит тяжело."),
        Item("Выдвижной ящик", "Верхний ящик под столешницей с вилками и ложками."),
        Item("Холодильник", "Советский холодильник \"Бирюса\". Ни разу не ремонтировался."),
    ),
) :Room(name, description, items){

    override fun printItems() {
        if (items.isNotEmpty()) {
            print("$name содержит ${items.size} предмета: ")
            println(items.joinToString { it.name })
        } else println("$name полностью пуста")

    }

}