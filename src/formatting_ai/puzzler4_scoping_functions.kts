fun printLength(s: String?) {
    if (s != null) {
        println(s.length)
    }
}
// What’s more idiomatic?
// 1. Keep the `if`
// 2. Use `s?.let { println(it.length) }`
// 3. Use `run`
// 4. Throw if null
// See solutions.md for answer and explanation

