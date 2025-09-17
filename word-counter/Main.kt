
fun main() {

    println("Введите путь к файлу:")
    val userPath = readln().trim()
    println("Введите слово для поиска:")
    val userWord = readln().trim()
    val wordCount = WordCounter()

    try {
        println("Найдено совпадений: ${wordCount.countWord(userPath, userWord)}")
    }catch (e: FileNotFoundException) {
        println(e.message)
    }catch (e:EmptyFileException) {
        println(e.message)
    }
}

// src/main/kotlin/products.csv