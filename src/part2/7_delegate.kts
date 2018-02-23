package part2

class Group(var names: Map<String, String>) {
    val he by names
    val she by names
}

val group = Group(mapOf(
    "he" to "Hanna",
    "she" to "Jack"
))

group.names = emptyMap()

println("He is ${group.he} and She is ${group.she}")

// What will it prints?
// 1. He is Hanna and She is Jack
// 2. He is null and She is null
// 3. He is he and She is she
// 4. NullPointerException