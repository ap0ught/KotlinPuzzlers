fun String.trimAndLower() = trim().toLowerCase()
fun process(s: String) {
    println(s.trimAndLower())
}
// Why might you prefer a top-level util instead?
// 1. Better discoverability on String
// 2. Avoid polluting String API
// 3. No difference
// 4. Always use extension
// See solutions.md for answer and explanation

