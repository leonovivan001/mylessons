open class Vehicle(
    private val mark: String,
    private val model: String,
    private val year: Int,
) {

    fun start() = println("Двигатель автомобиля запущен")
    fun getMark() = mark   // это геттеры, через main можем добраться к приватным переменным
    fun getModel() = model
    fun getYear() = year


}