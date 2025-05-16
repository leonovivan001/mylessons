class Bedroom(
    name: String,
    description: String = "",
    items: Array<Item> = arrayOf(
        Item("Окно", "Обычное окно с ручкой на дальней стене комнаты, есть форточка."),
        Item("Тумбочка", "Небольшого размера деревяная тумба. Очень подходит к кровати."),
        Item("Кровать", "Незаправленная кровать из Икеи, вместе с тумбой смотрятся хорошо!"),
    ),
) :Room(name, description, items){

    override fun printItems() {
        if (items.isNotEmpty()) {
            print("$name содержит ${items.size} предмета: ")
            println(items.joinToString { it.name })
        } else println("$name полностью пуста")

    }
}