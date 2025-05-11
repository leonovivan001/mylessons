class Main {
}
// Для чего мы делали класс main, если он нигде не задействован?

fun main() {

    val book1 = Book("Золотая рыбка", "А. Пушкин", "001-N0023")
    val book2 = Book("Война и Мир", "Л. Толстой", "002-N0443")
    val book3 = Book("Преступление и наказание", "Ф. Достоевский", "003-N0390")
    val book4 = Book("Чапаев и Пустота", "В. Пелевин", "004-N0276")
    val book5 = Book("Хоббит", "Р. Толкин", "111-N0444")

    val library = Library() // создаем саму "библиотеку", через которую можно обращаться к книгам

    library.books.add(book1)
    library.books.add(book2)
    library.books.add(book3)
    library.books.add(book4)
    library.books.add(book5)

    println("Введите название желаемой книги:")
    println(
        library.searchByTitle(
            readln().lowercase().ifBlank {    // проверка на null и пустую строку, чтобы база не крашнулась
                println("Ошибка: вы ничего не ввели.")
                return
            }
        )?: "Книга в базе не найдена."
    )
}

// Я понял ошибку с companion object)) Я думал будет правильнее в main вызвать функцию searchByTitle напрямую через класс, а не через обьект класса, поэтому сделал companion, а передал в функцию массив, так как из-за companion object функция не видела массива внутри класса, оказывается...
