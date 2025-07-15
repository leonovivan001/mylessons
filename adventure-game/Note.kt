class Note(
    name: String,
    description: String,
    val text: String,
) :Item(name, description), Collectible, Useful {
    override fun use() {
        println("Текст из записки: \"$text\"\n")
    }
}