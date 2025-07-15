class Window(
    name: String,
    description: String,
    val text: String,
) :Item(name, description), Useful {
    override fun use() {
        println(text)
    }
}