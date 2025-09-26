import LogEntry
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Application {
    companion object {
        @JvmStatic

        fun main(args: Array<String>) {
            val fileUtil = FileUtil()                                    // Пременная класса FileUtil для манипуляций
            var products: MutableList<Product>                           // Временный список товаров (пустой)
            var logs: MutableList<LogEntry> = mutableListOf()            // Временный список LOG (пустой)

            try {
                products = fileUtil.readProducts().toMutableList()    // Актуализируем временный список из файла данных
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

            try {
                logs = fileUtil.readProductsLog().toMutableList()    // Актуализируем временный список из файла logs.csv
            } catch (e: FileNotFoundException) {
                println(e.message)
                Thread.sleep(800)
                return
            }catch (e: EmptyfileException) {
            }catch (e: Exception) {               // любые иные ошибки с файлом LOG или строкой
                println("\nВ журнале продаж содержится ошибка заполнения или файл поврежден")
                return
            }

            while (true) {
                val menu = Menu()
                when (menu.showMainMenu()) {
                    1 -> {                              //  Показать товар
                        try {
                            showProducts(products)
                        }catch (e: EmptyfileException) {
                            println(e.message)
                        }catch (e: Exception) {
                            println("В списке содержится ошибка или файл поврежден")
                            return
                        }
                        Thread.sleep(800)
                    }
                    2 -> {                              //  Добавить товар
                        products.add(createProduct())
                        fileUtil.saveProducts(products)
                        Thread.sleep(800)
                    }
                    3 -> {                              //  Покупка товара
                        try {
                            BuyProductsShow(products)                                                   // показываем
                        } catch (e: EmptyfileException) {
                            println(e.message)
                        }
                        try {
                            BuyProductsDelete(BuyProductsChoose(products), products, logs)     // выбираем и удаляем
                        }catch (e: NullProductException) {
                            println(e.message)
                            Thread.sleep(900)
                            println("_________________________________________________")
                            continue
                        }catch (e: BackToMenuException) {
                            continue
                        }
                        fileUtil.saveProducts(products)    // Сохраняем/обновляем список товаров
                        fileUtil.saveLogs(logs)            // Сохраняем/обновляем журнал LOG
                    }
                    4 -> {
                        try {
                            logs = fileUtil.readProductsLog().toMutableList()    // Актуализируем временный список из файла logs.csv
                        }catch (e: EmptyfileException) {
                            println(e.message)
                            Thread.sleep(800)
                            continue
                        }
                        println("Журнал продаж:")
                        logs.forEach { it
                            println("Товар: ${it.productName}, Категория: ${it.category}, Цена: ${it.price}, Дата продажи: ${it.saleDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
                        }
                        Thread.sleep(800)
                    }
                    0 -> {
                        println("Выходим...")
                        Thread.sleep(800)
                        return
                    }
                    100 -> {
                        Thread.sleep(800)
                        continue
                    }
                }
            }
        }

        // Показываем товар
        fun showProducts(products: MutableList<Product>) {
            var count = 0
            if (products.isEmpty()) {
                throw EmptyfileException("\nВнимание: список товаров пуст")
            }
            println("Товары, которые есть в наличии на складе:")
            for (product in products) {
                count++
                println("#$count $product")
                Thread.sleep(300)
            }
        }
        // Содаем товар
        fun createProduct(): Product {                 // возвращает product
            println("Введите наименование товара:")
            val newProductName = newProductString()
            println("Введите описание товара:")
            val newProductDescription = newProductString()
            println("Введите стоимость (только цифры):")
            val newProductPrice = newProductDecimal()
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
        fun newProductString(): String {
            return readln().trim()
        }
        fun newProductDecimal(): BigDecimal {
            while (true) {
                val textReadln = readln()
                if (!(textReadln.trim().matches(Regex("^\\d+([.,]\\d+)?$")))) {
                    println("Введите стоимость (только цифры)")
                    Thread.sleep(500)
                    println()
                } else return textReadln.replace(',', '.').toBigDecimal().setScale(2)  // возвращаем созданную цену
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
                    println("Ошибка, введите цифру от 1 до 3")
                    Thread.sleep(500)
                    println()
                } else {
                    when (textReadln.toInt()) {
                        1 -> return Category.ELECTRONICS
                        2 -> return Category.FURNITURE     // возвращаем созданную категорию
                        3 -> return Category.ACCESSORIES
                    }
                }
            }
        }
        // Покупаем товар
        fun BuyProductsShow (products: MutableList<Product>) {
            if (products.isEmpty()) {
                throw EmptyfileException("\nВнимание: список товаров пуст")
            }
            var count = 0
            for (product in products) {
                count++
                println("$count. ${product.simpleShow()}")
                Thread.sleep(300)
            }
        }
        fun BuyProductsChoose(products: MutableList<Product>): Int {
            println("___________________________________________")
            println("Введите номер товара, кторый хотите купить: (0 - назад)")
            val textReadln = readln().trim()
            if ((!(textReadln.matches(Regex("^\\d+$"))
                        && textReadln.toInt() in 0..products.size))
            ) {
                throw NullProductException("Ошибка: товара под номером $textReadln не существует.")
            }
            if (textReadln.toInt() == 0) {
                throw BackToMenuException ("")
            }
            return textReadln.toInt()
        }
        fun BuyProductsDelete(number: Int, products: MutableList<Product>, logs: MutableList<LogEntry>) {
            logs.add(
                LogEntry(
                    products[number - 1].name,
                    products[number - 1].category,
                    products[number - 1].price,
                    LocalDate.now()
                )
            )
            println("✅Товар ${products[number - 1].name} успешно продан.")
            products.removeAt(number - 1)
        }
        fun Product.simpleShow(): String {
            return "${this.name}, ${this.description} (${this.category.rusName}), цена: ${this.price}"  // упрощенное отображание списка
        }
    }
}

//Наименование товара,Описание товара,Категория,Цена
//Кровать,Стильная молодежная кровать,FURNITURE,67990.00
//Кресло компьютерное Zertex,Модель 302 цвет черный,FURNITURE,7990.00
//Стол компьютерный Zertex,Модель 204 цвет черный,FURNITURE,18200.00
//Телевизор LG,Плазма диагонать 50 см,ELECTRONICS,36000.00
//Холодильник Бирюса,Двухкамерный цвет белый,ELECTRONICS,100.00
//Утюг Tefal,Титановый корпус цвет белый,ELECTRONICS,3990.00
//Брелок Zertex,Брелок на ключи с эмблемой магазина,ACCESSORIES,99.99
//Шкатулка,Золотая на ножках,ACCESSORIES,999.99