package part1

fun sayHello(name: String) {
    print("Hello $name")
}

val name = System.getProperty("name")
sayHello(name.nonNull())

fun String?.nonNull() = this ?: "null"


// What will it prints (male or female logic)?
// 1. 123done
// 2. 12
// 3. Infinite loop
// 4. Will not compile
