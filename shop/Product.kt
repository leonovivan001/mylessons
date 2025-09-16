data class Product (
    val name: String,
    val description: String,
    val category: Category,
    val price: Double,
) {
    override fun toString(): String {
        return "Наименование товара: $name, Описание товара: $description, Категория: ${category.rusName}, Цена: $price руб."
    }
}


