abstract open class Room(
    val name: String,
    val description: String,
    val items: MutableList<Item> = mutableListOf()
) {

    abstract fun printItems()

}