import java.io.File

class FileUtil {

    var productsBase = File("src/main/kotlin/products.csv")

    fun readProducts(): List<Product> {
        if (!productsBase.exists()) {
            throw FileNotFoundException("\nОшибка: Файл \"${productsBase.name}\" не найден.")
        }
        if (productsBase.readLines().drop(1).isEmpty()) {
            throw EmptyfileException("\nОшибка: Файл \"${productsBase.name}\" пуст.")
        }
        val products: MutableList<Product> = mutableListOf()     // возвращаемый список List
        val lines = productsBase.readLines().drop(1)
        for (line in lines) {
            products.add(
                line.toProduct() ?: throw NullProductException(
                    "\nОшибка: строка товара \"${line.split(Regex("[,;]"), limit = 2)[0]}\" заполнена некорректно, проверьте файл ${productsBase.name}") // Боже, храни GPT чат за такие красивые решения, несмотря на их внутреннее уродство!
            )
        }
        return products
    }

    fun saveProducts(products: List<Product>) {
        productsBase.writeText("")
        productsBase.appendText("Наименование товара,Описание товара,Категория,Цена")
        products.forEach {
            productsBase.appendText(it.toCsvString())
        }
    }

    fun Product.toCsvString(): String {
        return "\n${this.name},${this.description},${this.category},${this.price}"
    }

    fun String.toProduct(): Product? {
        val line = this.split(",", ";")
        if (line.size < 4) return null
        if (line[2].isEmpty()) throw NullProductException("\nОшибка: строка товара \"${line[0]}\" заполнена некорректно, проверьте файл ${productsBase.name}")
        val tempLineName = line[0]
        val tempLineDescription = line[1]
        val tempLineCategory = Category.valueOf(line[2])
        val tempLinePrice = try {
            line[3].toDouble()
        } catch (e: Exception) {
            throw EmptyPriceException("\nОшибка: цена товара \"${line[0]}\" указана некорректно, проверьте файл ${productsBase.name}")
        }

        val tempProduct = Product(tempLineName, tempLineDescription, tempLineCategory, tempLinePrice)
        return tempProduct
    }
}