package part2

class Greeting(val word: String)
open class HerFather() {
    init {
        say()
    }
    open fun say() = print("Luke! I'm your Father!")
}

class Friend() : HerFather() {
    private val greeting: Greeting by lazy {
        Greeting("hello")
    }
    override fun say() = println(greeting.word)
}
Friend()

// What will it prints?
// 1. null
// 2. hello
// 3. Will not compile
// 4. None of the above