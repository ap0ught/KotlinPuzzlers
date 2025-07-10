sealed class Shape
data class Circle(val r: Double) : Shape()
data class Square(val s: Double) : Shape()

// What’s the idiomatic way to compute area?
// 1. `if` ladder
// 2. `when` expression over `Shape`
// 3. Reflection
// 4. Visitor pattern
// See solutions.md for answer and explanation

