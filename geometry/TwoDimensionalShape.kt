abstract class TwoDimensionalShape () :Shape {

//    override fun calculateArea() {
//        println()
//    }
//    override fun calculatePerimeter() {
//         println()
//    }

}

// зачем нужен TwoDimensionalShape если можно напрямую создать три класса фигур с интерфейсом Shape?
// зачем в классе TwoDimensionalShape переопределять calculateArea() если методы уже существуют в интерфейсе Shape они все равно переопределяются в классах фигур?