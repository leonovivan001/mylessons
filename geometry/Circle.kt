class Circle(
    val r: Double
) :TwoDimensionalShape () {
    override fun calculateArea() {
        println("Площадь круга равна: ~${(Math.PI*r*r).toInt()} см.")
    }

    override fun calculatePerimeter() {
        println("Периметр круга равен: ~${(2* Math.PI*r).toInt()} см.")
    }
}

// P = 2πr
// S = π × r²