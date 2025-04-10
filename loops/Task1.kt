fun main () {

    var names: Array <String> = arrayOf("John", "Bob", "Mary", "Peter", "Sarah")


    for (i in names) {
        if (i.length < 4) println("В имени $i содержится меньше 4-х букв.")
        else if (i.length > 4) println("Имя $i содержит больше 4 букв.")
        else println("В имени $i ровно 4 буквы.")
    }
    println()


    var indexCount = names.size
    while (indexCount == names.size) {
        indexCount--
        names.forEach {
            if (it.length < 4) println("В имени $it содержится меньше 4-х букв.")
            else if (it.length > 4) println("Имя $it содержит больше 4 букв.")
            else println("В имени $it ровно 4 буквы.")
        }
    }
    println()


    do {
        names.forEach {
            if (it.length < 4) println("В имени $it содержится меньше 4-х букв.")
            else if (it.length > 4) println("Имя $it содержит больше 4 букв.")
            else println("В имени $it ровно 4 буквы.")
        }
    }while (false)
}