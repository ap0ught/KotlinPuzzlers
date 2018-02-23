package part2

open class Person(val name: String) {
    fun sayName() = println(name)
}

class Father : Person("Father") {
    fun makeChild(name: String): Person? = Person(name)

    val child1 = makeChild("child1")?.apply { sayName() }
    val child2 = makeChild("child2").apply { sayName() }
}

Father()

// What will it prints?
// 1. child1 child2
// 2. child1 Father
// 3. Father child2
// 4. Will not compile