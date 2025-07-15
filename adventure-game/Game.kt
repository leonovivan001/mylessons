import Game.Companion.isGameFinished

class Game() {
    companion object {
        var isGameFinished: Boolean = false
    }
}
fun main () {

    startMessage()

// Создание комнат, добавление комнат в дом(массив)
    val rooms: MutableList<Room> = mutableListOf()
    val roomBathroom = Bathroom()
    val roomBedroom = Bedroom()
    val roomKitchen = Kitchen()
    rooms.add(roomBathroom)
    rooms.add(roomBedroom)
    rooms.add(roomKitchen)

// Имя персонажа
    print("Введите имя вашего героя: ")
    val player = Player(readln().ifBlank {
        println("Ошибка: имя должно содержать символы.")
        return
    }

    )
    println("----------------------")

// Начало основной механики игры, действует циклично
    while (true) {
        if (isGameFinished == true) {
            endMessage(player.name, player.inventory)
            return
        }
        when (showMenu()) {
            1 -> {
                var whatIsShowRooms = showRooms(rooms)
                if (whatIsShowRooms in 1..3) player.currentRoom = rooms[whatIsShowRooms - 1]
                else println()
            }
            2 -> player.showItems()
            0 -> {
                println("Вы выходите из игры. До новых встреч!")
                return
            }
        }
    }

}

// Методы (функции) вынесены отдельно, чтобы не сбиваться в кучу
fun startMessage() {
    println(
        """
            ====================================================
            |          Добро пожаловать в игру "Дом"!          |
            |                                                  |
            |     ...Вы обнаруживаете себя в чужой квартире.   |
            |  С вами есть еще кто-то и это существо нуждается |
            |                  в вашей помощи.                 |
            |                                                  |
            |   Вам нужно найти особый предмет, который может  |
            |                  быть где угодно.                |
            |                                          Удачи!  |
            ====================================================
            """
    )
}
fun showMenu(): Int {
    while (true) {
        println("Выберите действие:\n1 - Перейти в другую комнату \n2 - Показать инвентарь\n0 - Выход")
        val chooseAction = readln()
        if (!chooseAction.contains("[а-яА-яA-Za-z]".toRegex()) && chooseAction.isNotBlank() && chooseAction.toInt() in 0..2) return chooseAction.toInt()
        else print("Ошибка! Введите номер из списка. ")
    }
}
fun showRooms(rooms: MutableList<Room>): Int {
    while (true) {
        println("----------------------")
        println("Выберите комнату:\n1 - ${rooms[0].name}\n2 - ${rooms[1].name}\n3 - ${rooms[2].name}\n0 - Назад")
        val chooseRoom = readln()
        if (!chooseRoom.contains("[а-яА-яA-Za-z]".toRegex()) && chooseRoom.isNotBlank() && chooseRoom.toInt() in 0..3) return chooseRoom.toInt()
        else print("Ошибка! Введите номер из списка.\n")
        if (chooseRoom.toInt() == 0) println("Нажал ноль")
    }
}
fun endMessage(name: String, inventory: Array<Item?>) {
    val listOfInventory: MutableList <String> = mutableListOf()

    inventory.filterNotNull().forEach {
        if(it.name != "Несколько пачек денег"){
            listOfInventory.add(it.name)
        }
    }
    println(
        """
            =========================================================
            |     Вы  поворачиваетесь к Сереге  -  старому  другу,  |
            | боевому товарищу, а  в  этот раз еще и напарнику  по  |
            | квартирным кражам: Серега все еще прикован к батарее. | 
            | Наручниками.                                          |
            |     Завидя  в  ваших  руках  заветные  ключи, Сергей, |
            | воодушевясь,  нелепо   вскакивает  с  колен,  словно  |
            | готовясь к старту. Щелчок.                            |
            |     Металлический  лязг  разорвал  тишину  квартиры,  | 
            | отделив   мясного   Серегу  от  чугунной   батареи.   |
              "Спасибо, $name, братан!" - радуется Серега.            
            |     "Свобода. Берем бабки и валим отсюда"             |
            |                                                       |
            |    
            |   Остаток  вечера  вы провели  в  одном  из  самых    |
            | дорогих   ресторанов  города, рассыпаясь друг  перед  |
            | другом  в  бесконечном  уважении  и  верности дружбе. |
            | А потом, пока  ты  спорил с диджеем о музыке, Серега  |
            | внезапно исчез. 
            |    А вместе с ним исчезли и все заработанные деньги.  |
            |      
            |    Спустя неделю из выпуска новостей вы узнали, что   |
            | его  приняли  в  аэропорту с  поддельным  паспортом и |
            | фальшивыми купурами.                                  |
            | Как и почему Серега  оказался прикованным наручниками | 
            | в этом злаполучном доме - история умалчивает.         |
            |                                                       |
            |                                               КОНЕЦ   |
            =========================================================

Для вас история закончилась хорошо, в память о ней напоминают лишь вещи, оставшиеся у вас: 
${listOfInventory.joinToString()}... 
Денег у вас нет, но зато есть маленький ключик от наручников с красивой филигранью и гравировкой в виде буквы "L".
            """)
}


