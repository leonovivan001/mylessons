open class Room(
    val name: String,
    val description: String = "",
    val items: Array<Item> = arrayOf()
) {

    open fun printItems() {
        println("В этой комнате нет доступных предметов")
    }

}