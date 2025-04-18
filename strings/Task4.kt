// Перемудрил с функцией .replace, хотелось убрать все знаки из предложения чтобы не
// учитывать их при разбивке при создании массива, не нашел другого способа кроме
// того, чтобы просто перечислить знаки препинания... В итоге выглядит ужасно, но работает)

fun main () {

    var count: Int = 0
    println("Введите предложение:")
    var userText = readln()
    println("Введите слово, которое нужно найти:")
    var userWord = readln().lowercase()
    var userTextArray = userText.lowercase().replace(",", "").replace(".", "").replace(":", "").replace(";", "").replace("!", "").replace("?", "").replace("...", "").replace("-", "").replace("\"", "").replace("(", "").replace(")", "").replace("/", "").replace("<", "").replace(">", "").split(" ")
    for (i in userTextArray) if (i == userWord) count++
    println("Количество слов \"$userWord\": $count")
}
