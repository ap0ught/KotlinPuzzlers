package part2

class Person {
    val name: String? = System.getProperty("name")
}
print("Hello ${Person::name.name}")

// What will it prints?
// 1. Hello name
// 2. Hello null
// 3. Hello Roman
// 4. None of the above