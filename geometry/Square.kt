class Square (
    val a: Double,
    val b: Double
) :TwoDimensionalShape () {
    override fun calculateArea() {
        println("Площадь прямоугольника равна: ~${(a*b).toInt()} см.")
    }

    override fun calculatePerimeter() {
        println("Периметр прямоугольника равен: ~${(2 * (a + b)).toInt()} см.")
    }
}

// S=a×b
// P=2a+2b