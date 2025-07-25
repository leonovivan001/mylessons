class Furniture(
    name: String,
    description: String,
    val text: String,
    var internalItem: Collectible,
): Item(name, description), Useful {
    override fun use() {
        println(text)
    }
}