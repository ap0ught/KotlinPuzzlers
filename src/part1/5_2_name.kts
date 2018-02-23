package part1

fun sayHello(name: String) {
    print("Hello $name")
}

class Person {
    val name: String? = System.getProperty("name")
}
sayHello(Person::name.name)

// What will it prints?
// 1. Hello name
// 2. Hello null
// 3. Will not compile
// 4. None of the above