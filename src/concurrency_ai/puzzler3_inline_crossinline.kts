// ...existing code...
inline fun runTwice(crossinline block: () -> Unit) {
    repeat(2) { block() }
}

fun main() {
    runTwice {
        // return // What happens if you uncomment this?
        println("Hello")
    }
}

// What happens if you uncomment the return statement inside the lambda?
// 1. It prints "Hello" twice
// 2. It throws an exception at runtime
// 3. It will not compile
// 4. It prints "Hello" once
