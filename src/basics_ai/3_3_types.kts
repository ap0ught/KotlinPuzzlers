package part1

val i = 3.5

when(i) {
    in 0..3 -> println("$i in 0..3")
    !in 0..3 -> println("$i not in 0..3")
    else -> println("else")
}


// What will it prints?
// 1. 3.5 in 0..3
// 2. 3.5 not in 0..3
// 3. else
// 4. Will not compile

// See the answer and explanation in solutions.md
