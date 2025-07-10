data class Person(val name: String, val age: Int)

fun main() {
    val p1 = Person("Alice", 30)
    val p2 = p1.copy(age = 31)
    println(p2)
}

// Why `copy` is preferred?
// 1. Manual constructor call
// 2. `copy` ensures immutability
// 3. `var` properties
// 4. `apply` block
// See solutions.md for answer and explanation

