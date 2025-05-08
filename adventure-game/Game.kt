class Game() {

    companion object{
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


    val a = Bathroom("Ванная", "Просто Ванная")
    val b = Bedroom("Спальня", "Просто спальня")
    val c = Kitchen("Кухня", "Просто кухня")

    println(a.name)
    println(a.description)
}


