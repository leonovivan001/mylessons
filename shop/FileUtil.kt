import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FileUtil {

    var productsBase = File("src/main/kotlin/products.csv")
    var productsLog = File("src/main/kotlin/logs.csv")

    // PRODUCTS BASE
    fun readProducts(): List<Product> {
        if (!productsBase.exists()) {
            throw FileNotFoundException("\nОшибка: Файл \"${productsBase.name}\" не найден.")
        }
        if (productsBase.readLines().drop(1).isEmpty()) {
            throw EmptyfileException("\nВнимание: Файл \"${productsBase.name}\" пуст.")
        }
        val products: MutableList<Product> = mutableListOf()     // возвращаемый список List (временный)
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
            line[3].toBigDecimal()
        } catch (e: Exception) {
            throw EmptyPriceException("\nОшибка: цена товара \"${line[0]}\" указана некорректно, проверьте файл ${productsBase.name}")
        }

        val tempProduct = Product(tempLineName, tempLineDescription, tempLineCategory, tempLinePrice)
        return tempProduct
    }

    // LOGS BASE
    fun readProductsLog(): List<LogEntry> {
        if (!productsLog.exists()) {
            throw FileNotFoundException("\nОшибка: Файл \"${productsLog.name}\" не найден.")
        }
        if (productsLog.readLines().drop(1).isEmpty()) {
            throw EmptyfileException("\nФайл \"${productsLog.name}\" пуст.")
        }
        val logsTemp: MutableList<LogEntry> = mutableListOf()     // возвращаемый список List (временный)
        val lines = productsLog.readLines().drop(1)
        for (line in lines) {
            logsTemp.add(line.toLongEntry())
        }
        return logsTemp
    }
    fun saveLogs(logs: List<LogEntry>) {
        Thread.sleep(900)
        println("✅В журнал продаж добавлена новая запись")
        Thread.sleep(800)
        logs.forEach {
            productsLog.appendText(it.toCsvString())
        }
    }
    fun String.toLongEntry(): LogEntry {
        val line = this.split(",", ";")
        val tempLineName = line[0]
        val tempLineCategory = Category.valueOf(line[1])
        val tempLinePrice = line[2].toBigDecimal()
        val tempLineDate = LocalDate.parse(line[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        val tempLogEntry = LogEntry(tempLineName, tempLineCategory, tempLinePrice, tempLineDate)
        return tempLogEntry
    }
    fun LogEntry.toCsvString(): String {
        return "\n${this.productName}," +
                "${this.category},${this.price}," +
                "${this.saleDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}"
    }
}