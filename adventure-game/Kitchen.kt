class Kitchen(
    val name: String,
    val description: String = "",
    val items: Array<Item> = arrayOf(
        Item("Плита", "Плита газовая, белорусская. Выглядит тяжело."),
        Item("Выдвижной ящик", "Верхний ящик под столешницей с вилками и ложками."),
        Item("Холодильник", "Советский холодильник \"Бирюса\". Ни разу не ремонтировался."),
    ),
) {

//  constructor(name: String) : this(name, description = "") - он не нужен, так как в основном констр. прописали переменные

}