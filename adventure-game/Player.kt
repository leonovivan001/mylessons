class Player(
    var name: String,
    var inventory:Array<Item?> = arrayOfNulls(10),

) {

    var currentRoom: Room? = null
        set(value) {
            println("Вы перешли в комнату: ${value?.name}")
            field = value
            println()
        }

    fun showItems() {
        var countOfItem = 0
        println("Количество вещей в инвентаре: ${inventory.filter {it != null}.size}")
        for (item in inventory) {
            countOfItem++
            println("Ячейка $countOfItem: ${item?.name ?: "—"}")
        }
        println()
    }


}
