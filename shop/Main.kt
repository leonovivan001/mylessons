import jdk.jfr.Threshold
import kotlin.math.cbrt

class Application {
    companion object {
        @JvmStatic

        fun main(args: Array<String>) {
                var products: MutableList<Product> = mutableListOf(
                    Product("Телевизор LG", "Плазма, диагонать 50 см.", 36000.00, Category.ELECTRONICS),
                    Product("Холодильник \"Бирюса\"", "Двухкамерный, цвет белый", 28500.00, Category.ELECTRONICS),
                    Product("Утюг \"Tefal\"", "Титановый корпус, цвет белый", 3990.00, Category.ELECTRONICS),
                    Product("Компьютерный стол \"Zertex\"", "Модель 204, цвет черный", 18200.00, Category.FURNITURE),
                    Product("Компьютерное кресло \"Zertex\"", "Модель 302, цвет черный", 7999.00, Category.FURNITURE),
                    Product("Брелок \"Zertex\"", "Брелок на ключи с эмблемой магазина", 0.99, Category.ACCESSORIES ),
                )
            while (true) {
                val menu = Menu()

                when (menu.showMainMenu()) {
                    1 -> {
                        showProducts(products)
                        Thread.sleep(800)
                    }
                    2 -> {
                        products.add(createProduct())
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
                newProductPrice,
                newProductCategory
            )
            println("Создан продукт:\n$newProduct")
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
                try {
                    val textReadln = readln()
                    if ((textReadln.matches(Regex("^\\d+$")))) {
                        return textReadln.toDouble()
                    }
                    throw NewPriceException("Ошибка, введите сумму (цифры)")
                } catch (e: NewPriceException) {
                    println(e.message)
                    Thread.sleep(500)
                    println()
                }
            }
        }
        fun newProductCategory(): Category {
            while (true) {
                try {
                    println(
                        "1. ${Category.ELECTRONICS.rusName}\n" +
                        "2. ${Category.FURNITURE.rusName}\n" +
                        "3. ${Category.ACCESSORIES.rusName}"
                    )
                    val textReadln = readln().trim()
                    if (textReadln.matches(Regex("^\\d+$")) && textReadln.toInt() in 1..3) {
                        when (textReadln.toInt()) {
                            1 -> return Category.ELECTRONICS
                            2 -> return Category.FURNITURE
                            3 -> return Category.ACCESSORIES
                        }
                    }
                    throw NewCategoryException("Ошибка, введите цифру от 1 до 3")
                } catch (e: NewCategoryException) {
                    println(e.message)
                    Thread.sleep(500)
                    println()
                }
            }
        }
    }
}



// Почему мы "помечаем" товары маркерами ELECTRONICS, FURNITURE и тд именно через Enum, ведь это вполне можно сделать и через интерефейс?