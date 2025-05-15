class Bathroom(
    name: String,
    description: String = "",
    items: Array<Item> = arrayOf(
        Item("Зеркало", "Круглое зеркало над раковиной, почти винтажное."),
        Item("Раковина", "Белая керамическая раковина со следами зубной пасты."),
        Item("Душевая кабина", "На вид стеклянная, но я легко узнаю дешевый пластик."),
    ),
) :Room(name, description, items) {
}
