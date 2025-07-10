package part1

val yes: String? = "yes"
// This will not compile in Kotlin: ternary operator (? :) is not supported.
// See the answer and explanation in solutions.md
println( yes == "yes" ? "yes" : "maybe" )


// What will it prints (male or female logic)?
// 1. yes
// 2. maybe
// 3. will not compile
// 4. TypeCastExcception
