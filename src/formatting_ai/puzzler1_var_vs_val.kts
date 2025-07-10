fun main() {
    var count = 0
    listOf(1,2,3).forEach { count += it }
    println(count)
}
// What’s the best practice here?
// 1. Keep `count` as `var`
// 2. Use `fold` and keep `count` as `val`
// 3. Use `var` and a regular `for`
// 4. Combine both approaches
// See solutions.md for answer and explanation

