import Game.Companion.isGameFinished
import kotlin.contracts.contract
import kotlin.math.cbrt

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
        do {
            println("Количество вещей в инвентаре: ${inventory.filter { it != null }.size}")
            for (item in inventory) {
                countOfItem++
                println("⏹\uFE0F Ячейка $countOfItem: ${item?.name ?: "—"}")
            }
            println("----------------------\nС чем взаимодействуем? (0 - назад)")
            val numberOfItem = textReadln(inventory.size) // вводим и проверяем чтобы число было в рамках количества итемов
            when (numberOfItem) { // начало выбор предметов

                100 -> {
                    textWrong()
                    countOfItem = 0
                }
                0 -> {
                    println()
                    return
                }
                in 0..inventory.size -> {
                    val item: Item? = inventory[numberOfItem - 1]
                    if (item != null) {

                        if (handleCollectible(item) != 1) {  //  предложение юзать, плюс проверка на ключ
                            countOfItem = 0
                        } else return

                    } else {
                        println("⏹\uFE0F Ячейка $numberOfItem пуста\n"); Thread.sleep(1500)
                        countOfItem = 0
                        continue
                    }
                }
            }      // конец проверки выбора предметов
        } while (true)
    }
    fun addInventory(numberOfItem: Item): Boolean {
        if (inventory.contains(numberOfItem)) {
            println("\n❗Этот предмет уже есть в твоем инвенторе.")
            println()
            return false
        }
        if (!inventory.contains(null)) {
            println("❗Некуда класть, ваш инвентарь полон")
            Thread.sleep(1800)
            return false
        }
        val emptyPlaceIndex = inventory.indexOfFirst { it == null } // находим первую пустую ячейку (Null)
        inventory[emptyPlaceIndex] = numberOfItem // добавляем предмет в массив
        println("\n✅Предмет добавлен в инвентарь!")
        Thread.sleep(1500)
        println()
        return true
    }
    fun handleCollectible(item: Item) :Int {

        if (item is Collectible) {      // если Collectible ДА
            println()
            Thread.sleep(1300)
            println("✅Вы осматриваете предмет \"${item.name}\"")
            println("\uD83D\uDCA1Описание предмета: ${item.description}")
            println()
            Thread.sleep(1500)
            do {

                if (keyCheck(item) == 1) { // в инвентаре есть и это ключ
                    textWinKey() // победный текст
                    println()
                    Thread.sleep(1500)
                    when (getFinish(item, item)) {  // вариант завершить игру
                        1 -> return 1
                        0 -> break
                    }

                } else if (keyCheck(item) == 0) {     // в инвентаре есть и это НЕ ключ
                    (item as Useful).use()
                    Thread.sleep(1500)
                    println()
                    println("----------------------\nВы можете совмещать предметы, например, \uD83E\uDDF5+ \uD83E\uDEA1= \uD83D\uDC55 \nС каким предметом совместить \"${item.name}\"? (0 - назад)")
                    val numberOfItem = textReadln(inventory.size) // вводим и проверяем чтобы число было в рамках количества итемов
                    when (numberOfItem) { // начало выбор предметов
                        100 -> {
                            textWrong()
                        }

                        0 -> {
                            return 0
                        }

                        in 0..inventory.size -> {
                            val item2: Item? = inventory[numberOfItem - 1]
                            if (item2 != null) {
                                useItems(item, item2) // логика проверки мэтча что делать с предметами (сиги + спички  = курить)
                            } else {
                                println("Совместить не с чем, ячейка $numberOfItem пуста\n"); Thread.sleep(1500)
                            }
                        }
                    }      // конец проверки выбора предметов

                    return 0
                }
                println("Выберите действие:\n1 - Взаимодействовать с предметом\n0 - Назад")
                when (textReadln(1)) {
                    0 -> {
                        println()
                        return 0
                    }
                    1 -> {          // начало взаимодействия
                        println()
                        println("✅Результат взаимодействия:")
                        Thread.sleep(1500)
                        (item as Useful).use()
                        Thread.sleep(1800)
                        println()

                        do {
                            println("Забрать себе? \n1 - Да \n0 - Нет")
                            when (textReadln(1)) {
                                0 -> {
                                    println()
                                    println("Вы оставляете ${item.name} на месте")
                                    println()
                                    Thread.sleep(1500)
                                    return 0
                                }

                                1 -> {
                                    if (!addInventory(item)) {
                                        return 0
                                    }
                                    currentRoom?.items!!.remove(item) // удалить предмет из списка в комнате
                                    return 0
                                }
                                100 -> textWrong()
                            }
                        } while (true)
                    }    // конец взаимодействия
                    100 -> textWrong()
                }
            } while (true)
        }
        return 1
    }
    fun handleUseful(item: Item) {

        if (item is Furniture) {       //если НЕ Collectible
            Thread.sleep(1300)
            println("\uD83D\uDCA1Описание предмета: ${item.description}")
            Thread.sleep(1800)
            println("✅Вы осматриваете предмет \"${item.name}\": ")
            Thread.sleep(1500)
            (item as Useful).use()
            Thread.sleep(1800)

            val contained = item.internalItem as? Item
            println()
            if ((contained?.name == "Пусто.") || (contained?.name == "Вы уже здесь все осмотрели.")) { // Если в мебели ПУСТО или уже
                println(contained.name)
                println()
                Thread.sleep(1500)
                return
            } else {                           // Если в мебели ЕСТЬ предмет
                println("✅Успешно! Вы находите предмет: ${contained!!.name}")
                Thread.sleep(1500)
                println("(${contained.description})")
                Thread.sleep(1800)
                println()
                do {
                    println("Забрать себе? \n1 - Да \n0 - Нет")
                    when (textReadln(1)) {
                        0 -> {
                            println()
                            println("Вы оставляете ${contained.name} внутри предмета \"${item.name}\"")
                            println()
                            break
                        }

                        1 -> if (contained.name == "\uD83D\uDD11Ключ от наручников") { // проверка на ключ
                            textWinKey() // победный текст
                            when (getFinish(item, contained)) {
                                1 -> break
                                0 -> break
                            }
                        } else {                         // добавляем в инвентарь и удаляем из мебели
                            if (addInventory(contained) == false) {
                                continue
                            } else {
                                item.internalItem = Key("Вы уже здесь все осмотрели.", "Пусто")
                                break
                            }
                        }

                        100 -> {
                            textWrong()
                        }
                    }
                } while (true)
            }
        } else if (item is Window) {
            Thread.sleep(1300)
            println("\uD83D\uDCA1Описание предмета: ${item.description}")
            Thread.sleep(1800)
            println("✅Вы осматриваете предмет \"${item.name}\": ")
            Thread.sleep(1500)
            (item as Useful).use()
            Thread.sleep(1800)
            println()
            println("Ничего не происходит, вы просто смотрите на ${item.name.lowercase()}")
            Thread.sleep(1800)
        }
    }
    fun useItems(item1: Item, item2: Item) {

        if (item1 == item2) {
            println()
            Thread.sleep(1200)
            println("Хотелось бы посмотреть как ты совместишь ${item1.name} с ${item2.name})))")
            Thread.sleep(1000)
            println("Cовмеcитить можно только разные предметы")
            Thread.sleep(1800)
            println()
            return
        }
        if (item1.name == item2.actualPair) {
            println("✅Успех! ${item1.useMessage}")
            Thread.sleep(1800)
            println()
            Thread.sleep(1000)
        } else println("Эти вещи несовместимы :-(")
        println()
        Thread.sleep(1800)
        println()

    }
    fun textReadln(range: Int): Int {
        val textReadln = readln().trim()
        if (textReadln.matches(Regex("^\\d+$"))
            && textReadln.toInt() in 0..range) {
            return textReadln.toInt()
        }
        return 100
    }
    fun textWrong() {
        println("❗Ошибка! Введите номер из списка.")
        println()
        return
    }
    fun textWinKey(){
        Thread.sleep(2000); println("\uD83C\uDF1FПохоже, это именно то, что я искал!")
    }
    fun getFinish(item: Item, contained: Item) :Int {
        do {
            println("\nВаши действия:\n1 - Используем ключ и выходим на свободу\n0 - Продолжить изучать дом, оставив ключ у себя")
            when (textReadln(1)) {
                1 -> {
                    isGameFinished = true
                    return 1
                }
                0 -> {
                    if (inventory.contains(contained)) {  // проверка на ключ, если да, то addInventory() скипаем
                        println()
                        return 0
                    }
                    if (addInventory(contained) == false) {
                        continue
                    }
                    (item as Furniture).internalItem = Key("Вы уже здесь все осмотрели.", "Пусто")
                    return 0
                }
            }
        } while (true)
    }
    fun keyCheck(item: Item): Int {
        if ((inventory.contains(item)) && (item.name == "\uD83D\uDD11Ключ от наручников")) {
            return 1
        } else if ((inventory.contains(item)) && (item.name != "\uD83D\uDD11Ключ от наручников")) {
            return 0
        } else return 2
    }

    fun interact() {                          // Взаимодействия с предметами items
        while (isGameFinished == false) {
            currentRoom?.printItems()
            println("----------------------\nС чем взаимодействуем? (0 - назад)")
            val numberOfItem = textReadln(currentRoom?.items!!.size) // вводим и проверяем чтобы число было в рамках количества итемов
            if (numberOfItem != 100) { // начало выбор предметов
                if (numberOfItem == 0) {
                    return
                }
                val item: Item = currentRoom?.items!![numberOfItem - 1]

                if (handleCollectible(item) == 1) {  // проверяем item Collectible
                    handleUseful(item)               // если нет, то Useful
                }
            } else textWrong() // конец проверки выбора предметов
        }
    }
}


/*

Список изменений:
 +++ Добавили наличие предметов внутри мебели
 +++ Удаляем Collectible предмет из списка предметов в комнате после добавления его в инвентарь
 +++ Добавляем цикличность при неверном ответе "Берем себе? Да-Нет" и "Взаимодействовать Да-Нет"
 +++ Изменены фильтра на ввод: теперь пропускает только цифры
 +++ Логика проверки на Collectible и Useful веведены в отдельные методы
 +++ Список действий в инвентаре (сиги + спички = покурить) и использовать предметы из инвенторя

 */



