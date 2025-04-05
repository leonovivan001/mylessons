fun main () {

    var originalPrice: Double
    var saleSize: Double
    originalPrice = 12000.0
    saleSize = 20.0
    var discountAmount = originalPrice * saleSize / 100
    var finalPrice = originalPrice - discountAmount

    println("Цена без скидки: $originalPrice")
    println("Цена со скидкой: $finalPrice (скидка $saleSize%)")

}
