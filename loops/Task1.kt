fun main () {

    var names: Array <String> = arrayOf("John", "Bob", "Mary", "Peter", "Sarah")

    println()    // FOR
    for (i in names) {
        if (i.length < 4) println("Имя $i содержит меньше 4-х букв (точное значение: ${i.length})")
        else if (i.length > 4) println("В имени $i больше 4 букв (точное значение: ${i.length})")
        else println("В имени $i ровно 4 буквы")
    }

    println()    // WHILE
    var indexOfNames = 0
    while (indexOfNames < 5) {
        if (names[indexOfNames].length < 4) println("Имя ${names[indexOfNames]} содержит меньше 4-х букв (точное значение: ${names[indexOfNames].length})")
        else if (names[indexOfNames].length > 4) println("В имени ${names[indexOfNames]} больше 4 букв (точное значение: ${names[indexOfNames].length})")
        else println("В имени ${names[indexOfNames]} ровно 4 буквы")
        indexOfNames++
    }

    println()    // DO WHILE
    indexOfNames = 0
    do {
        if (names[indexOfNames].length < 4) println("Имя ${names[indexOfNames]} содержит меньше 4-х букв (точное значение: ${names[indexOfNames].length})")
        else if (names[indexOfNames].length > 4) println("В имени ${names[indexOfNames]} больше 4 букв (точное значение: ${names[indexOfNames].length})")
        else println("В имени ${names[indexOfNames]} ровно 4 буквы")
        indexOfNames++
    }while (indexOfNames < 5)
}
