class Note(
    name: String,
    description: String,
    val text: String,
    actualPair: String = "Пусто",
    useMessage: String = "Пусто",
) :Item(name, description, actualPair, useMessage), Collectible, Useful {
    override fun use() {
        println("Текст из записки: \"$text\"")
    }
}