fun main () {

// 1
    println("Введите текст:")
    var text = readln()
    while (text == "") {
        println("Ошибка. Вы ввели пустую строку. Введите текст:")
        text = readln()
    }
        var char: CharArray
        char = text.toCharArray()
        val simbols = "[^A-Za-z0-9]".toRegex()

// 2
        println("Введите шифр (от 0 до 99):")
        var codeTemp = readln()
    while (codeTemp.toIntOrNull() == null) {
        println("Ошибка: ожидаем число. Введите шифр (от 0 до 99):")
        codeTemp = readln()
    }
        var code = codeTemp.toInt()

// 3
    for (i in char.indices) {
            var temp = char[i] + code
            char[i] = temp
        }
        var newText = String(char)
        println("Исходное сообщение: \"$text\"\nЗашифрованное сообщение: \"$newText\"\nШифр: \"$code\"")
}