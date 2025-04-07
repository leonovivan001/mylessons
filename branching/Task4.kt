fun main () {
    //Цитата из задания "В текущем проекте создай новый файл с именем Task4.kt, а в нем класс Task4."
    // про создание "классов" так и не понял

    val itemName1 = "Русский Квас"
    val itemName2 = "Coca Cola"
    val itemName3 = "Pepsi"
    val itemName4 = "Sprite"
    val itemName5 = "Mirinda"

    val itemPrice1 = 1.99
    val itemPrice2 = 1.20
    val itemPrice3 = 1.40
    val itemPrice4 = 1.45
    val itemPrice5 = 1.30

    var itemQuantity1 = 10
    var itemQuantity2 = 12
    var itemQuantity3 = 8
    var itemQuantity4 = 13
    var itemQuantity5 = 20

    var selectedItem = "Русский Квас"  // введите название товара...

    when (selectedItem) {
        itemName1 -> {
            if (itemQuantity1 > 0) {
                println("\nПокупка товара \"$selectedItem\" за $itemPrice1$ прошла успешно!")
                itemQuantity1--
            }else println("\nТовар \"$selectedItem\" закончился.")
        }
        itemName2 -> {
            if (itemQuantity2 > 0) {
                println("\nПокупка товара \"$selectedItem\" за $itemPrice2$ прошла успешно!")
                itemQuantity2--
            }else println("\nТовар \"$selectedItem\" закончился.")
        }
        itemName3 -> {
            if (itemQuantity3 > 0) {
                println("\nПокупка товара \"$selectedItem\" за $itemPrice3$ прошла успешно!")
                itemQuantity3--
            }else println("\nТовар \"$selectedItem\" закончился.")
        }
        itemName4 -> {
            if (itemQuantity4 > 0) {
                println("\nПокупка товара \"$selectedItem\" за $itemPrice4$ прошла успешно!")
                itemQuantity4--
            }else println("\nТовар \"$selectedItem\" закончился.")
        }
        itemName5 -> {
            if (itemQuantity5 > 0) {
                println("\nПокупка товара \"$selectedItem\" за $itemPrice5$ прошла успешно!")
                itemQuantity5--
            }else println("\nТовар \"$selectedItem\" закончился.")
        }
        else -> println("\nТовара \"$selectedItem\" не существует.")
    }
    println("\nСписок товаров в наличии:")
    println("1. $itemName1, Цена: $itemPrice1$, Количество: $itemQuantity1")
    println("2. $itemName2, Цена: $itemPrice2$, Количество: $itemQuantity2")
    println("3. $itemName3, Цена: $itemPrice3$, Количество: $itemQuantity3")
    println("4. $itemName4, Цена: $itemPrice4$, Количество: $itemQuantity4")
    println("5. $itemName5, Цена: $itemPrice5$, Количество: $itemQuantity5")
}
