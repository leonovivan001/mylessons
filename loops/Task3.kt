fun main () {

    var listOfNumbers = IntArray(10)
    var randomNambers: Int

    for (a in listOfNumbers.indices) {
        randomNambers = (1..99).random()
        listOfNumbers[a] = randomNambers
    }

    println("\nСписок рандомных чисел до упорядочивания:")
for (x in listOfNumbers) print("${x.toString()} ")
    println()


    var bigger = 0
    var swapped = true

    do {
        swapped = false
        for (i in 0 until listOfNumbers.size-1) {
            if (listOfNumbers[i] > listOfNumbers[i + 1]) {
                bigger = listOfNumbers[i]
                listOfNumbers[i] = listOfNumbers[i + 1]
                listOfNumbers[i + 1] = bigger
                swapped = true
            }
        }
    }while (swapped)

    println()
    println("Список рандомных чисел после упорядочивания:")
    for (x in listOfNumbers) print("${x.toString()} ")
    println()

}
