class Item(
    val name: String,
    val description: String,
) {

    override fun toString(): String {
        return "$name. $description"
        // переопределяем toString чтобы при обращении
        // (например, println()) строка была такой, какая нам нужна
    }
}




