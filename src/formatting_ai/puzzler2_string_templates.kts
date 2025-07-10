fun greet(name: String) {
    val msg = "Hello, " + name + "!"
    println(msg)
}
// What’s the cleaner, idiomatic approach?
// 1. Keep concatenation
// 2. Use `StringBuilder`
// 3. Use string template `"Hello, $name!"`
// 4. Use `format`
// See solutions.md for answer and explanation

