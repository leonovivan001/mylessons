import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class RandomDateGenerator {
    fun generateRandomDate(startDate: String, endDate: String): LocalDate {
        val startDateActual = LocalDate.parse(startDate)
        val endDateActual = LocalDate.parse(endDate)
        val period = ChronoUnit.DAYS.between(startDateActual, endDateActual)
        val randomDays = Math.random()*period
        println()
        println("Разница между датами составляет $period дней")
        return startDateActual.plusDays(randomDays.toLong())
    }
}