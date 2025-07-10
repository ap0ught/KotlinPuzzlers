class MyClass {
    companion object
}

fun MyClass.Companion.foo() = "Hi"

fun main() {
    println(MyClass.foo())
}

// What does it print?
// 1. Hi
// 2. null
// 3. It will not compile
// 4. Runtime exception

// See solutions.md for answer and explanation

