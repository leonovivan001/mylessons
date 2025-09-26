import java.math.BigDecimal
import java.time.LocalDate

class LogEntry (
    val productName: String,
    val category: Category,
    val price: BigDecimal,
    val saleDate: LocalDate,
) {


}