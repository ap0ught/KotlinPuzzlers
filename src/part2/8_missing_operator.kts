package part2

val jedi = "Yoda"
val isDark = false

// Original code
if (jedi != null && isDark) {
    println("What???")
}
// Improve code
jedi?.takeIf{ isDark }.apply{ println("Yoda is Dark!") }

// What will it prints?
// 1. What???
// 2. What??? Yoda is Dark!
// 3. Yoda is Dark!
// 4. Nothing