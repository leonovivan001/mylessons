data class Product (
    val name: String,
    val description: String,
    val price: Double,
    val category: Category,
) {
    override fun toString(): String {
        return "Категория: ${category.rusName}, Имя: $name, Описание: $description, Цена: $price руб."
    }
}


