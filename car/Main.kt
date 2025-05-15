
fun main() {

    val toyota = Car("Toyota", "Corolla", 1986, 4)
    val reno = Car("Reno", "Logan", 2014, 4)
    val mersedes = Car("Mersedes", "Benz", 2020, 4)
    val lada = Car("Lada", "Largus", 2015, 2)

    toyota.start()
    println("${toyota.getMark()} ${toyota.getModel()}, ${toyota.getYear()}г, ${toyota.getNumberOfDoors()} двери\n")
    reno.start()
    println("${reno.getMark()} ${reno.getModel()}, ${reno.getYear()}г, ${reno.getNumberOfDoors()} двери\n")
    mersedes.start()
    println("${mersedes.getMark()} ${mersedes.getModel()}, ${mersedes.getYear()}г, ${mersedes.getNumberOfDoors()} двери\n")
    lada.start()
    println("${lada.getMark()} ${lada.getModel()}, ${lada.getYear()}г, ${lada.getNumberOfDoors()} двери\n")

}