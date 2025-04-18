fun main () {
    /*
    В задании сказано, что "если хоть одна пара символов не совпадает, то False", а я изначально
    сделал наоборот: если "все символы совпадают, то True", но потом подумал, что более
    грамотно будет словить хотя бы 1 ошибку и тут же прервать цикл - так мы сэкономим время и
    память программе, чтобы она не работала "вхолостую" и не доводила цикл до конца.
    */
    println("Введите слово или текст:")
        var userText = readln()
        var userTextCheck = userText.lowercase().replace(" ", "")
        var CheckFalse = true

    for (i in userTextCheck.indices) {
        var j = userTextCheck.length - 1 - i
        if (userTextCheck[i] != userTextCheck[j]) CheckFalse = false
        if (CheckFalse == false) break
    }
        if (CheckFalse == true) println("\nСтрока \"$userText\" - это палиндром!")
        else println("\nСтрока \"$userText\", к сожалению, не палиндром.")

}

//    а Роза упала на лапу Азора
//    Анна
