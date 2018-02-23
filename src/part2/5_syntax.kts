package part2

fun foo(a: Boolean, b: Boolean) = print("a=$a, b=$b")

val a = 1
val b = 2
val c = 3
val d = 4
foo(c < a, b > d)

// What will it prints?
// 1. a=true, b=true
// 2. a=false, b=false
// 3. a=null, b=null
// 4. Will not compile