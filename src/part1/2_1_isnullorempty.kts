package part1

val s: String? = null
if (s.isNullOrEmpty()) print("empty")


// What will it prints?
// 1. empty
// 2. Nothing
// 3. Will not compile
// 4. NullPointerException