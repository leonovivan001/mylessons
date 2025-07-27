class Triangle (
    val aBase: Double,
    val b: Double,
    val c: Double,
    val height: Double,
) :TwoDimensionalShape () {
    override fun calculateArea() {
        println("Площадь треугольника равна: ~${((aBase*height)/2).toInt()} см.")
    }

    override fun calculatePerimeter() {
        println("Периметр треугольника равен: ~${(aBase+b+c).toInt()} см.")
    }

}

// S = (aBase * h) / 2
// P = a + b + c
// height = 2S/a