fun printInfo(value: String) = println("Строка: $value")
fun printInfo(value: Int) = println("Число: $value")

fun main() {
    printInfo("Привет")  // Строка
    printInfo(42) // Число
    printInfo("1")
}
open class Animal {
    open fun speak() {
        println("Какое-то животное подаёт звук")
    }
}

interface Animal2 {
    fun speak() {
        println("Какое-то животное подаёт звук")
    }
}