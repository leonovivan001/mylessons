import java.io.File

class WordCounter {
    fun countWord (path: String, word: String) :Int {
        var count = 0
        val file = File(path)
        if (!file.exists()) throw FileNotFoundException("\nОшибка: по адресу ${file.path} файл не найден.")
        if (file.readLines().isEmpty()) throw EmptyFileException ("\nОшибка: Файл ${file.name} пуст.")

        for (line in file.readLines()) {
            val wordList = line.trim().replace(Regex("\\p{Punct}"), "").split(" ") // убираем все знаки препинания + тримим
            wordList.forEach { if(it.lowercase() == word.lowercase()) count++ }  // считаем слова без учета регистра
        }
        return count
    }
}