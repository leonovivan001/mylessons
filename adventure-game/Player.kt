class Player(
    val name: String,
    val inventory: Array<Item?> = arrayOfNulls(20),

    ) {

    var currentRoom: Room? = null
        set(value) {
            println("Вы перешли в комнату: ${value?.name}. ${value?.description}")
            field = value
            interact()
        }

    fun showItems() {
        var countOfItem = 0
        println("Количество вещей в инвентаре: ${inventory.filter { it != null }.size}")
        for (item in inventory) {
            countOfItem++
            println("Ячейка $countOfItem: ${item?.name ?: "—"}")
        }
        println()
    }

    fun addInventory(numberOfItem: String): Boolean {
        if (inventory.contains(currentRoom?.items!![numberOfItem.toInt() - 1])) {
            println("\n❗Этот предмет уже есть в твоем инвенторе.")
            println()
            return false
        }
        val emptyPlaceIndex =
            inventory.indexOfFirst { it == null } // находим первую пустую ячейку (Null)
        inventory[emptyPlaceIndex] = currentRoom?.items!![numberOfItem.toInt() - 1] // добавляем предмет в массив
        println("\n✅Предмет добавлен в инвентарь!")
        println()
        return true
    }
    fun printWinText () {
        print("Что это?")
        println()
        Thread.sleep(1000)
        Thread.sleep(1800)
        print("Такой ")
        Thread.sleep(1000)
        print("маленький... ")
        Thread.sleep(1800)
        print("Блестящий... ")
        Thread.sleep(1800)
        print("Это ключ! ")
        Thread.sleep(1000)
        print("От наручников!")
        Thread.sleep(2000)
    }

    // Взаимодействия с предметами items
    fun interact() {
        while (true) {
            currentRoom?.printItems()
            println("----------------------\nС чем взаимодействуем? (0 - назад)")
            var numberOfItem = readln() // вводим и проверяем чтобы число было в рамках количества итемов
            if (!numberOfItem.contains("[а-яА-яA-Za-z]".toRegex()) && numberOfItem.isNotBlank() && numberOfItem.toInt() in 0..(currentRoom?.items!!.size)) {
                if (numberOfItem.toInt() == 0) {
                    return
                }
                when {       // Проверка на Collectible or Not
                    currentRoom?.items!![numberOfItem.toInt() - 1] is Collectible -> {
                        println()
                        Thread.sleep(1300)
                        println("\uD83D\uDCA1Описание предмета: ${currentRoom?.items!![numberOfItem.toInt() - 1].description}")
                        println()
                        println("Выберите действие:\n1 - Взаимодействовать с предметом\n0 - Назад")
                        val getOrUseselection = readln()
                        if (!getOrUseselection.contains("[а-яА-яA-Za-z]".toRegex()) && getOrUseselection.isNotBlank() && getOrUseselection.toInt() in 0..1) {
                            when (getOrUseselection.toInt()) {
                                0 -> {
                                    println()
                                    continue
                                }

                                1 -> {
                                    println()
                                    println("✅Вы осматриваете предмет \"${currentRoom!!.items[numberOfItem.toInt() - 1].name}\":")
                                    (currentRoom!!.items[numberOfItem.toInt() - 1] as? Useful)?.use()
                                    Thread.sleep(1800)
                                    // добавляем в момент взаимдействия возможность добавить предмет в инвентарь
                                    println()
                                    if (currentRoom!!.items[numberOfItem.toInt() - 1].name == "\uD83D\uDD11Ключ от наручников") {
                                        Thread.sleep(1500)
                                        println("\uD83C\uDF1FВот он - КЛЮЧ! Похоже, это именно то, что я искал!")
                                        Thread.sleep(1500)
                                        println("\nВаши действия:\n1 - Используем ключ и выходим на свободу\n0 - Продолжить изучать дом")
                                        val finishMe = readln()
                                        if (!finishMe.contains("[а-яА-яA-Za-z]".toRegex()) && finishMe.isNotBlank() && finishMe.toInt() in 0..1) {
                                            when (finishMe.toInt()) {
                                                1 -> {
                                                    Game.isGameFinished = true
                                                    Thread.sleep(1500)
                                                    println("Происходит следующее:")
                                                    println()
                                                    Thread.sleep(1500)
                                                    return
                                                }
                                                0 -> continue
                                            }
                                        }
                                    }
                                    var IGotIt: String
                                    do {
                                        println("Забрать себе? \n1 - Да \n0 - Нет")
                                        IGotIt = readln()
                                        if (!IGotIt.contains("[а-яА-яA-Za-z]".toRegex()) && IGotIt.isNotBlank() && IGotIt.toInt() in 0..1) {
                                            when (IGotIt.toInt()) {
                                                0 -> continue
                                                1 -> if (addInventory(numberOfItem) == false) continue
                                            }
                                        } else {
                                            println("❗Ошибка! Введите номер из списка.")
                                            println()
                                        }

                                    } while (IGotIt.toIntOrNull() !in 0..1)
//                                    val IGotIt = readln()
//                                    if (!IGotIt.contains("[а-яА-яA-Za-z]".toRegex()) && IGotIt.isNotBlank() && IGotIt.toInt() in 0..1) {
//                                        when (IGotIt.toInt()) {
//                                            0 -> continue
//                                            1 -> if (addInventory(numberOfItem) == false) continue
//                                        }
//                                    } else {
//                                        print("❗Ошибка! Введите номер из списка.")
//                                        println()
//                                    }
                                    // добавляем возможность в момент взаимодействия добавить предмет в инвентарь
                                }
                            }
                        } else {
                            print("❗Ошибка! Введите номер из списка.")
                            println()
                        }
                    }
                    //      конец блока проверки на Collectible

                    currentRoom?.items!![numberOfItem.toInt() - 1] !is Collectible -> {
                        Thread.sleep(1300)
                        println("\uD83D\uDCA1Описание предмета: ${currentRoom?.items!![numberOfItem.toInt() - 1].description}")
                        Thread.sleep(1800)
                        print("✅Вы осматриваете предмет \"${currentRoom!!.items[numberOfItem.toInt() - 1].name}\": ")
                        (currentRoom!!.items[numberOfItem.toInt() - 1] as? Useful)?.use() // as? Useful)? как бы проверяет является ли выбранный обьект обьектом класса Useful проверять надо так как программа не знает этого
                        Thread.sleep(1800)

                        // добавляем возможность найти предмет внутри предмета

                        if ((currentRoom?.items!![numberOfItem.toInt() - 1] is Furniture)
                            && (currentRoom?.items!![numberOfItem.toInt() - 1].name == "Кровать")
                        ) {

                            if (currentRoom?.items!!.size == 12) {
                                println()
                                Thread.sleep(1600)
                                println("Показалось. Кроме грязных носков здесь больше ничего нет")
                                Thread.sleep(1600)
                                println()
                                continue
                            }
                            println()
                            currentRoom?.items!!.add(Key("\uD83D\uDD11Ключ от наручников", "Хм, ключ кажется необычным, стоит рассмотреть получше"))
                            printWinText()
                            println()
                            println("\n✅ВНИМАНИЕ✅ Вы нашли новый предмет: Ключ от наручников")
                            Thread.sleep(1800)
                            println("Список предметов в комнате обновлен")
                            Thread.sleep(1800)
                        } else {
                            println()
                            println("✅Результат: ничего полезного НЕ НАЙДЕНО")
                            Thread.sleep(2500)
                        }
                    }
                }
            } else {
                print("Ошибка! Введите номер из списка.\n")
            }
        }
    }
}
