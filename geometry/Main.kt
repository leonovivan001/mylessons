
fun main () {

    var geometry: MutableList<Shape> = mutableListOf(
        Circle(30.5),
        Square(29.0, 46.5),
        Triangle(7.0, 4.0, 6.0, 3.42),
    )
    println()
    geometry.forEach {
        it.calculateArea()
        it.calculatePerimeter()
        println()
    }
}


