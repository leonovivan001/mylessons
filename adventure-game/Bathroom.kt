class Bathroom(
    val name: String,
    val description: String = "",
    val items: Array<Item> = arrayOf(
        Item("Зеркало", "Круглое зеркало над раковиной, почти винтажное."),
        Item("Раковина", "Белая керамическая раковина со следами зубной пасты."),
        Item("Душевая кабина", "На вид стеклянная, но я легко узнаю дешевый пластик."),
    ),
) {

//  constructor(name: String) : this(name, description = "") - он не нужен, так как в основном констр. прописали переменные

}