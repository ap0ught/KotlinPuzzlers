inline fun <reified T> isType(value: Any) = value is T

fun main() {
    val result = isType<String>("Kotlin") to isType<Int>("Kotlin")
    println(result)
}

// What does it print?
// 1. (true, false)
// 2. (false, true)
// 3. (true, true)
// 4. (false, false)

// See solutions.md for answer and explanation

