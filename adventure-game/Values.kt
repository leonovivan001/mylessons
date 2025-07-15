class Values(
    name: String,
    description: String,
    val text: String,
) :Item(name, description), Collectible, Useful {
    override fun use() {
        println(text)
    }
}