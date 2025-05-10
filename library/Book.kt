class Book(
    val name: String,
    val author: String,
    val ISBN: String,
) {

    override fun toString(): String {
        return "$name, автор: $author (ISBN: $ISBN)"
    }
}