class Car(
    mark: String,
    model: String,
    year: Int,
    private var numberOfDoors: Int,
) : Vehicle(mark, model, year) {

    fun getNumberOfDoors() = numberOfDoors  // геттер

}