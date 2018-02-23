package part1

fun sayHello(name: String) {
    print("Hello $name")
}

val name = System.getProperty("name")
sayHello(name.nonNull())

fun String?.nonNull() = this ?: "null"

// What will it prints?
// 1. Hello name
// 2. Hello null
// 3. Will not compile
// 4. None of the above