package part2

val jedi = "Yoda"
val isDark = false

// Original code
if (jedi != null && isDark) {
    println("What???")
}
// Improve code
jedi?.takeIf{ isDark }.apply{ println("Of course Dark!") }

// What will it prints?
// 1. What???
// 2. What??? Of course Dark!
// 3. Of course Dark!
// 4. Nothing