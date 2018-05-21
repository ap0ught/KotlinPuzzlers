package part1

infix operator fun Int.plus(i: Int) = this - i

println(-1 + 1)
println(-1 plus 1)
println(-1.plus(1))

// What will it prints?
// 1. -2  -2  -2
// 2.  0  -2  -2
// 3.  0  -2   0
// 4. -2  -2   0