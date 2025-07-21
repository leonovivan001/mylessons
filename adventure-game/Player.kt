import Game.Companion.isGameFinished
import kotlin.collections.get
import kotlin.contracts.contract
import kotlin.coroutines.coroutineContext
import kotlin.time.measureTime

class Player(
    val name: String,
    val inventory: Array<Item?> = arrayOfNulls(20),
) {

    var currentRoom: Room? = null
        set(value) {
            println("✅Вы перешли в комнату: ${value?.name}. ${value?.description}")
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
    fun addInventory(numberOfItem: Item): Boolean {
        if (inventory.contains(numberOfItem)) {
            println("\n❗Этот предмет уже есть в твоем инвенторе.")
            println()
            return false
        }
        val emptyPlaceIndex = inventory.indexOfFirst { it == null } // находим первую пустую ячейку (Null)
        inventory[emptyPlaceIndex] = numberOfItem // добавляем предмет в массив
        println("\n✅Предмет добавлен в инвентарь!")
        println()
        return true
    }
    fun printWinText (){
//        Thread.sleep(1300); print("Что это? ")
//        Thread.sleep(1500); print("Такой ")
//        Thread.sleep(500); print("маленький... ")
//        Thread.sleep(1800); print("Блестящий... ")
//        Thread.sleep(1800); print("Это ключ! ")
//        Thread.sleep(1000); println("От наручников!")
        Thread.sleep(2000); println("\uD83C\uDF1FПохоже, это именно то, что я искал!")
//        Thread.sleep(1500)
    }
    fun getFinish (item: Item, contained: Item) :Int {
        var choice = 100
        do {
            println("\nВаши действия:\n1 - Используем ключ и выходим на свободу\n0 - Продолжить изучать дом, оставив ключ у себя")
            when (textReadln(1)) {
                1 -> {
                    isGameFinished = true
                    choice = 1
                    return 1
                }
                0 -> {
                    if (addInventory(contained) == false) {
                        continue
                    }
                    (item as Furniture).internalItem = Key("Вы уже здесь все осмотрели.", "Пусто")
                    choice = 0
                    return 0
                }
            }
        } while (choice == 100)
        return 3
    }
    fun wrong() {
        println("❗Ошибка! Введите номер из списка.")
        println()
        return
    }
    fun textReadln(range: Int): Int {
        val textReadln = readln().trim()
        if (textReadln.matches(Regex("^\\d+$"))
            && textReadln.toInt() in 0..range) {
            return textReadln.toInt()
        }
        return 100
    }

    fun interact() {        // Взаимодействия с предметами items
        while (true) {
            currentRoom?.printItems()
            println("----------------------\nС чем взаимодействуем? (0 - назад)")
            var numberOfItem = textReadln(currentRoom?.items!!.size) // вводим и проверяем чтобы число было в рамках количества итемов
            if (numberOfItem != 100) { // начало выбор предметов
                if (numberOfItem == 0) {
                    return
                }
                var item: Item = currentRoom?.items!![numberOfItem - 1]

                when {       // Проверка на Collectible
                    item is Collectible && item is Useful -> { // если Collectible ДА
                        println()
                        Thread.sleep(1300)
                        println("\uD83D\uDCA1Описание предмета: ${item.description}")
                        println()
                        var choice1 = 100
                        do {
                            println("Выберите действие:\n1 - Взаимодействовать с предметом\n0 - Назад")
                            when (textReadln(1)) {
                                0 -> {
                                    println()
                                    break
                                }
                                1 -> {       // начало
                                    println()
                                    println("✅Вы осматриваете предмет \"${item.name}\":")
                                    item.use()
                                    Thread.sleep(1800)
                                    println()
                                    do {
                                        println("Забрать себе? \n1 - Да \n0 - Нет")
                                        when (textReadln(1)) {
                                            0 -> {
                                                println()
                                                println("Вы оставляете ${item.name} на месте")
                                                println()
                                                choice1 = 0
                                                break
                                            }
                                            1 -> {
                                                if (addInventory(item) == false) {
                                                    choice1 = 0
                                                    break
                                                }
                                                currentRoom?.items!!.remove(item) // удалить предмет из списка в комнате
                                                choice1 = 0
                                                break
                                            }
                                            100 -> wrong()
                                        }
                                    } while (true)
                                }    // конец
                                100 -> wrong()
                            }
                        } while (choice1 == 100)

                    }

                    item is Useful -> {           //если НЕ Collectible
                        Thread.sleep(1300)
                        println("\uD83D\uDCA1Описание предмета: ${item.description}")
                        Thread.sleep(1800)
                        print("✅Вы осматриваете предмет \"${item.name}\": ")
                        item.use()
                        Thread.sleep(1800)

                        if (item is Furniture) {
                            val contained = item.internalItem as? Item
                            println()
                            if ((contained?.name == "Пусто.")  // Если в мебели ПУСТО
                                || (contained?.name == "Вы уже здесь все осмотрели.")) {
                                println(contained)
                                println()
                                Thread.sleep(1800)
                                continue
                            } else {                           // Если в мебели ЕСТЬ предмет
                                println("✅Успешно! Вы находите предмет: ${contained!!.name}")
                                println()
                                var choice: Int = 100
                                do {
                                    println("Забрать себе? \n1 - Да \n0 - Нет")
                                    when (textReadln(1)) {
                                        0 -> {
                                            println()
                                            println("Вы оставляете ${contained!!.name} внутри предмета \"${item.name}\"")
                                            println()
                                            choice = 0
                                            break
                                        }
                                        1 -> if (contained.name == "\uD83D\uDD11Ключ от наручников") { // проверка на ключ
                                            printWinText () // победный текст
                                            when (getFinish(item, contained)) {
                                                1 -> return
                                                0 -> return
                                            }
                                        } else { // добавляем в инвентарь и удаляем из мебели
                                            if (addInventory(contained) == false) {
                                                continue
                                            }
                                            else {
                                                item.internalItem = Key("Вы уже здесь все осмотрели.", "Пусто")
                                                choice = 1
                                                break
                                            }
                                        }
                                        100 -> {
                                            choice = 100
                                            wrong()
                                        }
                                    }
                                } while (choice == 100)

                            }
                        } else {
                            println()
                            println("Ничего не происходит, вы просто смотрите на ${item.name.lowercase()}")
                            Thread.sleep(1800)
                        }
                    }
                }
            } else wrong() // конец проверки выбора предметов
        }
    }
}


/*

Список изменений:
 +++Добавили наличие предметов внутри мебели
 +++Удаляем Collectible предмет из списка предметов в комнате после добавления его в инвентарь
 +++Добавляем цикличность при неверном ответе "Берем себе? Да-Нет" и "Взаимодействовать Да-Нет"
 +++Изменены фильтра на ввод: теперь пропускает только цифры
 Вывести отдельно в методы проверка на Useful и на Collectible
 Список действий в инвентаре (сиги + спички = покурить) и использовать предметы из инвенторя
 Вернуть WINtext НЕ ЗАБЫТЬ

 */




