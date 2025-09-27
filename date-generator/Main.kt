import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main() {
    val randomDateGenerator = RandomDateGenerator()
    println("Введите начальную дату в формате yyyy-MM-dd:")
    val startDate = readln()
    println("Введите конечную дату в формате yyyy-MM-dd:")
    val endDate = readln()
    try {
        println(
            "Случайная дата между двумя датами: " +
                    "${randomDateGenerator.generateRandomDate(startDate, endDate).format(DateTimeFormatter.ofPattern("MM.dd.yyyy"))}"
        )
        Thread.sleep(1000)
    } catch (e: Exception) {
        println("Ошибка: Введенные вами данные не подходят под формат \"yyyy-MM-dd\".")
    }
}
//2025-08-27
//2025-09-27
