class Player(
    var name: String,
    var inventory:Array<Item?> = arrayOfNulls(10),

) {

//   var currentRoom: Room = Kitchen("Кухня")
     var currentRoom: Room? = null


}

// не могу задать по умолчанию currentRoom как обьект Room, так как оно требует обязательные поля
