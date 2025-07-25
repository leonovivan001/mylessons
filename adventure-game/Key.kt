class Key(
    name: String,
    description: String,
    actualPair: String = "Пусто",
    useMessage: String = "Пусто",
) : Item(name, description, actualPair, useMessage), Collectible, Useful {
    override fun use() {
        if (name == "\uD83D\uDD11Ключ от наручников") {
            Thread.sleep(1800)
            println("Блестящий ключик с красивой филигранью и гравировкой в виде буквы \"L\". Выглядит очень заманчиво.")
            return
        }else println("Без грамотного подхода $name ценности не представляет")
    }
}