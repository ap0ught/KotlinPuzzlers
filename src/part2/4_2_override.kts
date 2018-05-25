package part2

open class Parent {
    open fun sum(a: Int = 1, b: Int = 2) = a + b
}

class Child : Parent() {
    override fun sum(b: Int, a: Int) = super.sum(a, b)
}

val p: Parent = Child()
val c: Child = Child()

println(p.sum(a = 0))
println(c.sum(a = 0))

// What will it prints?
// 1. 12
// 2. 22
// 3. 21
// 4. Will not compile