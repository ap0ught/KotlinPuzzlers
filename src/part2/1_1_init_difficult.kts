package part2

class Friend() {
    val word: String
    init {
        say()
        word = "hello"
    }
    fun say() = if (word.isEmpty()) println("Hello") else println(word)
}

Friend()

// What will it prints?
// 1. Hello
// 2. hello
// 3. Will Not Compile
// 4. None of the above