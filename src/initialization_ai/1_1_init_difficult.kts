package part2

class Friend() {
    val word: String
    init {
        say()
        word = "Hello"
    }
    fun say() = if (word.isEmpty()) println("Goodbye") else println(word)
}

Friend()

// What will it prints?
// 1. Hello
// 2. Goodbye
// 3. Will Not Compile
// 4. None of the above

// See the answer and explanation in solutions.md
