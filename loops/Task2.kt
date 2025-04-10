fun main () {

    var numbers = IntArray(10)
    var summOfNumbers: Int = 0

    for (i in numbers.indices) numbers[i] = (1..999).random()
    for (a in numbers) println(a)

    for (i in numbers) {
        summOfNumbers = summOfNumbers + i
    }
println("\nСумма всех элементов массива равна $summOfNumbers")

    var everage = summOfNumbers / numbers.size
    println("Среднее значение элементов массива: $everage")



}