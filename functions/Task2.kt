fun main () {

    var userText = readln().lowercase().ifBlank { return }
    getStringStats(userText, includeSpaces = true)
    getStringStats(userText)



}

fun countVowels (text: String): Int {
    var vowels = listOf('а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я')
    var count = 0
    for (i in text) {
        for (a in vowels) {
            if (i == a) count++
        }
    }
    return count
}


fun getStringStats (input: String, includeSpaces: Boolean = false) {
    val extraText = if (includeSpaces == true) "с учетом пробелов" else "без учета пробелов"
    val vowels = countVowels (input)
    var length = 0
    if (includeSpaces) {
        length = input.length
    }else length = input.replace(" ", "").length

    println("Длина $extraText: $length, Гласных: $vowels")

}