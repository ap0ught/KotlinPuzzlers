package part2

typealias f = (Int) -> Unit

fun startCounting(one: f = {}, two: f = {}) {
    one(1)
    two(2)
}

startCounting { print(it) }
startCounting({ print(it) })

// What will it prints?
// 1. 11
// 2. 22
// 3. 12
// 4. 21

// See the answer and explanation in solutions.md
