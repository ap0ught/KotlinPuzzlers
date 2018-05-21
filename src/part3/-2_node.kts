package part3

open class Person(val name: String) {
    fun sayName() = println(name)
}

class Father : Person("Father") {
    fun makeChild(name: String): Person? = Person(name)

    val child1 = makeChild("Luke")?.apply { sayName() }
    val child2 = makeChild("Leia").apply { this.sayName() }
}

Father()

// What will it prints?
// 1. Luke Leia
// 2. Luke Father
// 3. Father Leia
// 4. Will not compile