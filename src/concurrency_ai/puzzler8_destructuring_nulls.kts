data class Pair(val a: Int, val b: Int)

fun main() {
    val p: Pair? = null
    val (x, y) = p
    println("$x,$y")
}

// What happens?
// 1. Prints "null,null"
// 2. Throws NullPointerException
// 3. Prints "0,0"
// 4. It will not compile

// See solutions.md for answer and explanation

