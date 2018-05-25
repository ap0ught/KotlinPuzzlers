package part2

class Greeting(val word: String)
open class HerFather() {
    init {
        say()
    }
    open fun say() = print("Luke! I'm your Father!")
}
class FixedGirl() : HerFather() {
    private var greeting: Greeting? = null
        get() {
            if (field == null) {
                field = Greeting("I Love you!")
            }
            return field
        }
    override fun say() = println(greeting!!.word)
}
FixedGirl()

// What will it prints?
// 1. null
// 2. Luke! I'm your Father!
// 3. Will not compile
// 4. None of the above