package part2

class Greeting(val word: String)

open class HerFather() {
    init {
        say()
    }

    open fun say() {
        print("Hi! I'm her Father!")
    }
}

class Friend() : HerFather() {
    private var greeting: Greeting? = null
        get() {
            if (field == null) {
                field = Greeting("hello")
            }
            return field
        }

    override fun say() = println(greeting!!.word)
}

Friend()

// What will it prints?
// 1. null
// 2. hello
// 3. Will not compile
// 4. None of the above