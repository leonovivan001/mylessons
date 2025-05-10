class Library(
    var books: MutableList<Book> = mutableListOf(),

    ) {

    companion object {                  // позволяет вызвать функцию обращаясь напрямую через класс
        fun searchByTitle(name: String, books: MutableList<Book>): String {  // пришлось в функцию передать массив, иначе она его не видела
            for (book in books) {
                if (name == book.name.lowercase()) return "Найдена книга: $book"
            }
            return "К сожалению, такой книги нет."    // вместо возврата null сразу возвращает сообщение
        }
    }
}
