
fun main () {

    /*

    arrayOf - массивы, может хранить данные только одного и того же типа.
    Array(3, {5}) - создает 3 элемента с числом 5
    arrayOfNulls<Int>(5)- если не знаем какой тип у элементов массива. (5) - количество элементов

    */

    var numbers = arrayOf(2, 5)

    for (i in numbers) println(i)
    println()
    println("The size is ${numbers.size}")

    println("Элемент \"4\" в списке? Ответ: ${4 in numbers} ")



}






//    for(n in 1..8){
//        if(n == 5) continue
//        println(n * n)
//    }

//    while(i > 0){        // сначала проверяется условие while, только потом выполняется тело
//        println(i*i)
//        i--
//    }


//    do {                 // сначала выполняется тело, только потом проверяется условие while
//        println(i * i)
//        i--
//    } while(i > 0)
//
//    i = 10
//
