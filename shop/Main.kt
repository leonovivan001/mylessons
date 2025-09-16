import jdk.jfr.Threshold
import java.io.File
import kotlin.math.cbrt

class Application {
    companion object {
        @JvmStatic

        fun main(args: Array<String>) {

            val fileUtil = FileUtil()                                // Пременная класса FileUtil для манипуляций
            var products: MutableList<Product> = mutableListOf()     // Список товаров временный (пустой)

            try {
                fileUtil.readProducts().forEach { products.add(it) } // Формируем временный список из файла данных
            }catch (e: FileNotFoundException) {
                println(e.message)
                println("Программа закрывается...")
                Thread.sleep(800)
                return
            }catch (e: EmptyfileException) {
                println(e.message)
                println("Программа закрывается...")
                Thread.sleep(800)
                return
            }catch (e: NullProductException) {
                println(e.message)
                println("Программа закрывается...")
                Thread.sleep(800)
                return
            }catch (e: EmptyPriceException) {
                println(e.message)
                println("Программа закрывается...")
                Thread.sleep(800)
                return
            }

            while (true) {
                val menu = Menu()
                when (menu.showMainMenu()) {
                    1 -> {
                        showProducts(products)
                        Thread.sleep(800)
                    }
                    2 -> {
                        products.add(createProduct())
                        fileUtil.saveProducts(products)
                        Thread.sleep(800)
                    }
                    3 -> {
                        println("Выходим...")
                        Thread.sleep(800)
                        return
                    }
                }
            }
        }


        fun createProduct() :Product{  // возвращает product
            println("Введите наименование товара:")
            val newProductName = newProductString()
            println("Введите описание товара:")
            val newProductDescription = newProductString()
            println("Введите стоимость (только цифры):")
            val newProductPrice = newProductDouble()
            println("Выберите категорию товара:")
            val newProductCategory = newProductCategory()

            val newProduct = Product(
                newProductName,
                newProductDescription,
                newProductCategory,
                newProductPrice,
            )
            println("Создан товар:\n$newProduct")
            return newProduct
        }
        fun showProducts(products: MutableList<Product>) {
            for (product in products) {
                println(product)
                Thread.sleep(300)
            }
        }
        fun newProductString() : String {
            return readln().trim()
        }
        fun newProductDouble(): Double {
            while (true) {
                val textReadln = readln()
                if (!(textReadln.matches(Regex("^\\d+$")))) {
                    println("Введите стоимость (только цифры)") // Поменял проброс исключения на else, в данной проверке IF это проще
                    Thread.sleep(500)
                    println()
                } else return textReadln.toDouble()
            }
        }
        fun newProductCategory(): Category {
            while (true) {
                println(
                    "1. ${Category.ELECTRONICS.rusName}\n" +
                    "2. ${Category.FURNITURE.rusName}\n" +
                    "3. ${Category.ACCESSORIES.rusName}"
                )
                val textReadln = readln().trim()
                if (!(textReadln.matches(Regex("^\\d+$")) && textReadln.toInt() in 1..3)) {
                    println("Ошибка, введите цифру от 1 до 3")  // Поменял проброс исключения на else, в данной проверке IF это проще
                    Thread.sleep(500)
                    println()
                } else {
                    when (textReadln.toInt()) {
                        1 -> return Category.ELECTRONICS
                        2 -> return Category.FURNITURE
                        3 -> return Category.ACCESSORIES
                    }
                }
            }
        }
    }
}