import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class Product (
    val name: String,
    val description: String,
    val category: Category,
    val price: BigDecimal,
    val addedDate: LocalDate = LocalDate.now()
) {
    override fun toString(): String {
        return "Наименование товара: $name, Описание товара: $description, Категория: ${category.rusName}, Цена: $price руб."
    }
}


