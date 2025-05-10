class Game() {
    companion object {
        var isGameFinished: Boolean = false
    }
}
fun main () {

    println("""
            ====================================================
            |          Добро пожаловать в игру "Дом"!          |
            |       Цель игры: выбраться из дома. Удачи!       |
            ====================================================
            """)

    val roomBathroom = Bathroom("Ванная комната", "Обычный совмещенный санузел.")
    val roomBedroom = Bedroom("Спальня", "Просторная спальня с двуспальной кроватью.")
    val roomKitchen = Kitchen("Кухня", "Большая кухня с совмещенной столовой.")


    print("Введите имя вашего героя: ")
    val player = Player(readln().ifBlank {  // проверка на null и пустую строку, чтобы игра не крашнулась
        println("Ошибка: имя должно содержать символы.")
        return
    }
    )


                                  // временные строки для проверки кода
    print("\nПроверяем код ")
    for (it in 1..5){
        Thread.sleep(150)
        print(">")
    }
    println("\nИмя игрока: ${player.name}")
    println(
        "Количество предметов в комнате ${roomBathroom.name}: ${roomBathroom.items.size}\n" +
        "Количество предметов в комнате ${roomBedroom.name}: ${roomBedroom.items.size}\n" +
        "Количество предметов в комнате ${roomKitchen.name}: ${roomKitchen.items.size}\n" +
        "Количество мест под инвентарь у игрока: ${player.inventory.size}"
    )

}


