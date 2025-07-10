tailrec fun countdown(n: Int): Int =
    if (n == 0) 0 else countdown(n - 1) + 0

fun main() {
    println(countdown(100000))
}

// What happens?
// 1. Prints 0
// 2. StackOverflowError
// 3. Infinite loop
// 4. It will not compile

// See solutions.md for answer and explanation

