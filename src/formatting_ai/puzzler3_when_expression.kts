fun grade(score: Int) {
    when (score) {
        in 90..100 -> println("A")
        in 80..89  -> println("B")
        else       -> println("F")
    }
}
// How to return the grade instead of printing?
// 1. Use multiple `return` in branches
// 2. Assign `when` to a `val`
// 3. Use `if-else`
// 4. Keep as is
// See solutions.md for answer and explanation

