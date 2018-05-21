package part3

class Greeting(val word: String)
open class HerFather() {
    init {
        say()
    }
    open fun say() = print("Luke! I'm your Father!")
}
class Girl() : HerFather() {
    private val greeting: Greeting
        get() {
            if (field == null) {
                field = Greeting("I Love you!")
            }
            return field
        }
    override fun say() = println(greeting.word)
}
Girl()

// What will it prints?
// 1. I Love you!
// 2. Luke! I'm your Father!
// 3. Will not compile
// 4. None of the above