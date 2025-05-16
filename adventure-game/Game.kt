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
    val roomBathroom = Bathroom("Ванная комната", "Обычный совмещенный санузел.")
    val roomBedroom = Bedroom("Спальня", "Просторная спальня с двуспальной кроватью.")
    val roomKitchen = Kitchen("Кухня", "Большая кухня с совмещенной столовой.")
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

// Начало основной механики игры, действует циклично
    while (true) {
        if (isGameFinished == true) {
            println("Поздравляем, вы победили!")
            return
        }
        when (showMenu()) {
            1 -> player.currentRoom = rooms[showRooms(rooms) - 1]
            2 -> player.showItems()
            0 -> {
                println("Вы выходите из игры. До встречи!")
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
            |       Цель игры: выбраться из дома. Удачи!       |
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
        println("Выберите комнату:\n1 - ${rooms[0].name}\n2 - ${rooms[1].name}\n3 - ${rooms[2].name}")
        val chooseRoom = readln()
        if (!chooseRoom.contains("[а-яА-яA-Za-z]".toRegex()) && chooseRoom.isNotBlank() && chooseRoom.toInt() in 1..3) return chooseRoom.toInt()
        else print("Ошибка! Введите номер из списка. ")
    }
}





