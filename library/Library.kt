class Library(
    var books: MutableList<Book> = mutableListOf(),

    ) {

    fun searchByTitle(name: String): Book? {
        for (book in books) if (name == book.name.lowercase()) return book
        return null
    }
}
