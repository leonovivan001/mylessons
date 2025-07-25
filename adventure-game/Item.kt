abstract class Item(
     val name: String,
     val description: String,
     var actualPair: String = "Пусто",
     var useMessage: String = "Пусто",
) {
    override fun toString(): String {
        return "$name $description"
    }
}
