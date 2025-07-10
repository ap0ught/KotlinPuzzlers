# Kotlin Puzzlers: Initialization & Inheritance (Part 2) Solutions

## Table of Contents

1. [Init Difficult](#1-init-difficult-1_1_init_difficultkts)
2. [Super Init](#2-super-init-1_2_superinitkts)
3. [Super Init (Broken Getter)](#3-super-init-broken-getter-1_3_superinitkts)
4. [Super Init Fixed](#4-super-init-fixed-1_4_superinit_fixedkts)
5. [Hello Functions](#5-hello-functions-2_hellokts)
6. [Return](#6-return-3_returnkts)
7. [Default Value Lambdas](#7-default-value-lambdas-4_1_defaultvalkts)
8. [Override Default Arguments](#8-override-default-arguments-4_2_overridekts)
9. [Java Interop](#9-java-interop-5_0_javainteropkts)
10. [Java Interop with Extension](#10-java-interop-with-extension-5_1_javainteropkts)
11. [Property Reference Name](#11-property-reference-name-5_2_namekts)
12. [Generic Variance](#12-generic-variance-6_generickts)
13. [Delegate Map](#13-delegate-map-7_delegatekts)
14. [Missing Operator](#14-missing-operator-8_missing_operatorkts)
15. [Node Creation](#15-node-creation-9_nodekts)

---

## 1. Init Difficult (`1_1_init_difficult.kts`) <a id="1-init-difficult-1_1_init_difficultkts"></a>

**Code:**
```kotlin
class Friend() {
    val word: String
    init {
        say()
        word = "Hello"
    }
    fun say() = if (word.isEmpty()) println("Goodbye") else println(word)
}
Friend()
```

**Solution:**  
The `say()` function is called before `word` is initialized, so accessing `word.isEmpty()` throws an exception at runtime.

**Why is this a great example?**
- Demonstrates pitfalls of property initialization order.
- Shows how accessing uninitialized properties can crash your program.
- Encourages careful design of initialization logic.

**Answer:**
4. throws exception

**Further Insights:**
- Calling open methods in `init` can see uninitialized state.
- Order of property initialization matters.

**Related Topics:**
- Kotlin initialization order
- `init` blocks and constructors
- Best practices for safe initialization

**Corrected code:**
```kotlin
class Friend() {
    val word: String
    init {
        word = "Hello"
        say()
    }
    fun say() = if (word.isEmpty()) println("Goodbye") else println(word)
}
Friend()
```

[Back to top](#table-of-contents)

---

## 2. Super Init (`1_2_superinit.kts`) <a id="2-super-init-1_2_superinitkts"></a>

**Code:**
```kotlin
class Greeting(val word: String)
open class HerFather() {
    init {
        say()
    }
    open fun say() = print("Luke! I'm your Father!")
}
class Girl() : HerFather() {
    private val greeting by lazy { Greeting("I Love you!") }
    override fun say() = println(greeting.word)
}
Girl()
```

**Solution:**  
When `Girl()` is constructed, `HerFather`'s init block calls `say()`, but at this point `greeting` is not initialized, so the base class's implementation is used.

**Why is this a great example?**
- Shows order of initialization and method dispatch in inheritance.
- Demonstrates how lazy properties interact with superclass constructors.
- Highlights subtle bugs in object construction.

**Answer:**
2. Luke! I'm your Father!

**Further Insights:**
- Lazy properties aren’t initialized until first use, which may come after `super` init.
- Avoid open calls in `init` to prevent base/derived dispatch issues.

**Related Topics:**
- Lazy delegation
- Inheritance and `init` order
- Virtual method calls in constructors

**Corrected code:**
```kotlin
class Greeting(val word: String)
open class HerFather() {
    init {
        // Avoid calling open functions in init
    }
    open fun say() = print("Luke! I'm your Father!")
}
class Girl() : HerFather() {
    private val greeting by lazy { Greeting("I Love you!") }
    override fun say() = println(greeting.word)
    init {
        say()
    }
}
Girl()
```

[Back to top](#table-of-contents)

---

## 3. Super Init (Broken Getter) (`1_3_superinit.kts`) <a id="3-super-init-broken-getter-1_3_superinitkts"></a>

**Code:**
```kotlin
class Greeting(val word: String)
open class HerFather() {
    init {
        say()
    }
    open fun say() = print("Luke! I'm your Father!")
}
class Girl() : HerFather() {
    private val greeting: Greeting
        get() {
            if (field == null) {
                field = Greeting("I Love you!")
            }
            return field
        }
    override fun say() = println(greeting.word)
}
Girl()
```

**Solution:**  
This code will not compile: you cannot assign to `field` in a custom getter for a `val` property.

**Why is this a great example?**
- Highlights restrictions on property accessors in Kotlin.
- Shows how improper use of `field` leads to compilation errors.
- Reinforces Kotlin's property rules.

**Answer:**
3. Will not compile

**Further Insights:**
- Custom getters for `val` cannot assign to `field`.
- Use a backing `var` for lazy initialization in getters.

**Related Topics:**
- Property accessors
- Backing fields
- Lazy initialization patterns

**Corrected code:**
```kotlin
class Greeting(val word: String)
open class HerFather() {
    init {
        say()
    }
    open fun say() = print("Luke! I'm your Father!")
}
class Girl() : HerFather() {
    private var _greeting: Greeting? = null
    val greeting: Greeting
        get() {
            if (_greeting == null) {
                _greeting = Greeting("I Love you!")
            }
            return _greeting!!
        }
    override fun say() = println(greeting.word)
}
Girl()
```

[Back to top](#table-of-contents)

---

## 4. Super Init Fixed (`1_4_superinit_fixed.kts`) <a id="4-super-init-fixed-1_4_superinit_fixedkts"></a>

**Code:**
```kotlin
class Greeting(val word: String)
open class HerFather() {
    init {
        say()
    }
    open fun say() = print("Luke! I'm your Father!")
}
class FixedGirl() : HerFather() {
    private var greeting: Greeting? = null
        get() {
            if (field == null) {
                field = Greeting("I Love you!")
            }
            return field
        }
    override fun say() = println(greeting!!.word)
}
FixedGirl()
```

**Solution:**  
When `FixedGirl()` is constructed, `HerFather`'s init block calls `say()`, which triggers the overridden method. The getter initializes `greeting` and prints "I Love you!".

**Why is this a great example?**
- Shows how to properly implement lazy initialization with a mutable property.
- Demonstrates correct use of custom getters.
- Encourages safe and idiomatic Kotlin code.

**Answer:**
1. null then “I Love you!”

**Further Insights:**
- Overriding open methods safely requires proper initialization.
- Use a `var` with custom getter for delayed value provision.

**Related Topics:**
- Custom getters/setters
- Delegation and lazy patterns
- Object construction pitfalls

**Corrected code:**
```kotlin
class Greeting(val word: String)
open class HerFather() {
    init {
        // Avoid calling open functions in init
    }
    open fun say() = print("Luke! I'm your Father!")
}
class FixedGirl() : HerFather() {
    private var greeting: Greeting? = null
        get() {
            if (field == null) {
                field = Greeting("I Love you!")
            }
            return field
        }
    override fun say() = println(greeting!!.word)
    init {
        say()
    }
}
FixedGirl()
```

[Back to top](#table-of-contents)

---

## 5. Hello Functions (`2_hello.kts`) <a id="5-hello-functions-2_hellokts"></a>

**Code:**
```kotlin
fun hello1() {
    print("1")
}

fun hello2() = print("2")

fun hello3() = {
    print("3")
}

hello1()
hello2()
hello3()
```

**Solution:**  
`hello1()` prints "1", `hello2()` prints "2", but `hello3()` returns a lambda that prints "3" only when invoked, not when called as `hello3()`. So only "12" is printed.

**Why is this a great example?**
- Shows difference between function returning Unit and function returning a lambda.
- Highlights subtlety in function invocation.

**Answer:**
2. 12

**Further Insights:**
- Function returning a lambda requires explicit invocation `()`.
- Distinguish between value and function types.

**Related Topics:**
- Function types
- Lambdas vs function references
- Kotlin typealiases

**Corrected code:**
```kotlin
fun hello1() {
    print("1")
}

fun hello2() = print("2")

fun hello3() = {
    print("3")
}

hello1()
hello2()
hello3()() // Invoke the returned lambda to print "3"
```

[Back to top](#table-of-contents)

---

## 6. Return (`3_return.kts`) <a id="6-return-3_returnkts"></a>

**Code:**
```kotlin
inline fun helloInline(block: () -> Unit) = block()
inline fun helloNoInline(noinline block: () -> Unit) = block
fun test() {
    val hello = fun(){
        print("1")
        return
    }
    helloNoInline {
        print("2")
        return
    }
    helloInline {
        print("3")
        return
    }
}

test()
```

**Solution:**  
Only "1" is printed. The other blocks are not executed due to how returns work in lambdas and inline functions.

**Why is this a great example?**
- Demonstrates return behavior in lambdas and inline functions.
- Highlights differences between inline and noinline.

**Answer:**
1. 1

**Further Insights:**
- Non-local returns are allowed only in inline lambdas.
- Use `return@label` for controlled exits.

**Related Topics:**
- Inline vs noinline
- Labeled returns
- Higher-order functions

**Corrected code:**
```kotlin
inline fun helloInline(block: () -> Unit) = block()
inline fun helloNoInline(noinline block: () -> Unit) = block
fun test() {
    val hello = fun(){
        print("1")
        return
    }
    helloNoInline {
        print("2")
        // return@helloNoInline // Use labeled return for local returns
    }
    helloInline {
        print("3")
        // return@helloInline // Use labeled return for local returns
    }
}

test()
```

[Back to top](#table-of-contents)

---

## 7. Default Value Lambdas (`4_1_defaultval.kts`) <a id="7-default-value-lambdas-4_1_defaultvalkts"></a>

**Code:**
```kotlin
typealias f = (Int) -> Unit

fun startCounting(one: f = {}, two: f = {}) {
    one(1)
    two(2)
}

startCounting { print(it) }
startCounting({ print(it) })
```

**Solution:**  
First call: prints "1" (only `one` is provided, `two` is default).  
Second call: prints "1" (only `one` is provided, `two` is default).

**Why is this a great example?**
- Shows default arguments and lambda invocation.
- Demonstrates how trailing lambda syntax works.

**Answer:**
1. 11

**Further Insights:**
- Trailing lambda syntax binds to the last parameter.
- Both parameters need explicit lambdas to invoke both.

**Related Topics:**
- Default arguments
- Lambda syntax variations
- API design with defaults

**Corrected code:**
```kotlin
typealias f = (Int) -> Unit

fun startCounting(one: f = {}, two: f = {}) {
    one(1)
    two(2)
}

startCounting({ print(it) }, { print(it) }) // Prints "12"
```

[Back to top](#table-of-contents)

---

## 8. Override Default Arguments (`4_2_override.kts`) <a id="8-override-default-arguments-4_2_overridekts"></a>

**Code:**
```kotlin
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
```

**Solution:**  
This code will not compile. Default arguments are not inherited by overrides, so calling `c.sum(a = 0)` is ambiguous and fails to compile.

**Why is this a great example?**
- Highlights Kotlin's default argument rules in inheritance.
- Shows how overriding can break default argument expectations.
- Encourages explicit argument passing in overrides.

**Answer:**
4. Will not compile

**Further Insights:**
- Overrides do not inherit default parameter values.
- Always supply all arguments or duplicate defaults in override signature.

**Related Topics:**
- Default parameters
- Method overriding
- API compatibility

**Corrected code:**
```kotlin
open class Parent {
    open fun sum(a: Int = 1, b: Int = 2) = a + b
}

class Child : Parent() {
    override fun sum(b: Int, a: Int) = super.sum(a, b)
}

val p: Parent = Child()
val c: Child = Child()

println(p.sum(a = 0)) // OK
println(c.sum(b = 2, a = 0)) // Explicitly provide all arguments
```

[Back to top](#table-of-contents)

---

## 9. Java Interop (`5_0_javainterop.kts`) <a id="9-java-interop-5_0_javainteropkts"></a>

**Code:**
```kotlin
println("Start\n\n.....")

fun sayHello(name: String) = print("Hello $name")

val name = System.getProperty("name")
sayHello(name)
```

**Solution:**  
If the system property "name" is not set, `name` is `null`, so prints "Hello null".

**Why is this a great example?**
- Shows Kotlin's interop with Java and handling of nullable values.

**Answer:**
2. Hello null

**Further Insights:**
- Java getters can return `null`; Kotlin treats as nullable.
- Use safe-call or defaulting to handle interop.

**Related Topics:**
- Kotlin ↔ Java interop
- Nullability annotations
- Platform types

**Corrected code:**
```kotlin
println("Start\n\n.....")

fun sayHello(name: String?) = print("Hello ${name ?: "unknown"}")

val name = System.getProperty("name")
sayHello(name)
```

[Back to top](#table-of-contents)

---

## 10. Java Interop with Extension (`5_1_javainterop.kts`) <a id="10-java-interop-with-extension-5_1_javainteropkts"></a>

**Code:**
```kotlin
fun sayHello(name: String) {
    print("Hello $name")
}

val name = System.getProperty("name")
sayHello(name.nonNull())

fun String?.nonNull() = this ?: "nothing"
```

**Solution:**  
If the system property "name" is not set, `name` is `null`, so `nonNull()` returns "nothing". Prints "Hello nothing".

**Why is this a great example?**
- Shows use of extension functions for null safety.
- Demonstrates Kotlin's concise handling of nulls.
- Useful for Java interop scenarios.

**Answer:**
2. Hello nothing

**Further Insights:**
- Extension functions can add null-safety wrappers.
- Leverage Kotlin’s null-coalescing to smooth Java interop.

**Related Topics:**
- Extension functions
- Elvis operator
- Interop utility patterns

**Corrected code:**
```kotlin
fun sayHello(name: String) {
    print("Hello $name")
}

val name = System.getProperty("name")
sayHello(name?.nonNull())

fun String?.nonNull() = this ?: "nothing"
```

[Back to top](#table-of-contents)

---

## 11. Property Reference Name (`5_2_name.kts`) <a id="11-property-reference-name-5_2_namekts"></a>

**Code:**
```kotlin
class Person {
    val name: String? = System.getProperty("name")
}
print("Hello ${Person::name.name}")
```

**Solution:**  
`Person::name.name` returns the property name as a string, so prints "Hello name".

**Why is this a great example?**
- Demonstrates Kotlin's reflection and property references.
- Shows how to get property names programmatically.

**Answer:**
1. Hello name

**Further Insights:**
- `KProperty.name` yields the property’s identifier.
- Useful for reflective and meta-programming tasks.

**Related Topics:**
- Kotlin reflection
- Property references
- DSL authoring

**Corrected code:**
```kotlin
class Person {
    val name: String? = System.getProperty("name")
}
print("Hello ${Person::name.name}") // Prints "Hello name"
```

[Back to top](#table-of-contents)

---

## 12. Generic Variance (`6_generic.kts`) <a id="12-generic-variance-6_generickts"></a>

**Code:**
```kotlin
class Cup<in T>

open class All

val anys: Cup<Any> = Cup<All>()
val nothings: Cup<Nothing> = Cup<All>()
```

**Solution:**  
This code will not compile. `Cup<in T>` means T is contravariant, but you cannot assign `Cup<All>` to `Cup<Any>` or `Cup<Nothing>`.

**Why is this a great example?**
- Highlights variance and type safety in generics.
- Shows how Kotlin enforces generic constraints.

**Answer:**
2. Will not compile

**Further Insights:**
- `in` and `out` control producer/consumer variance.
- Incorrect variance leads to type-safety violations.

**Related Topics:**
- Generics and variance
- Producer-Consumer principle
- Kotlin type system

**Corrected code:**
```kotlin
class Cup<in T>

open class All

val allCup: Cup<All> = Cup<All>()
// val anys: Cup<Any> = Cup<All>() // Not allowed
// val nothings: Cup<Nothing> = Cup<All>() // Not allowed
```

[Back to top](#table-of-contents)

---

## 13. Delegate Map (`7_delegate.kts`) <a id="13-delegate-map-7_delegatekts"></a>

**Code:**
```kotlin
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
```

**Solution:**  
Delegated properties are bound to the original map at construction. Changing `names` does not affect the delegated values. Prints "He is Hanna and She is Jack".

**Why is this a great example?**
- Shows how property delegation works in Kotlin.
- Demonstrates that delegates are bound at construction, not dynamically.

**Answer:**
1. He is Hanna and She is Jack

**Further Insights:**
- Delegates bind at initialization using the original delegate instance.
- Mutable delegate source doesn’t retroactively update delegates.

**Related Topics:**
- Property delegation
- Map-based delegates
- Delegate lifecycle

**Corrected code:**
```kotlin
class Group(var names: Map<String, String>) {
    val he by names
    val she by names
}

val group = Group(mapOf(
    "he" to "Hanna",
    "she" to "Jack"
))

group.names = emptyMap() // Does not affect delegated properties

println("He is ${group.he} and She is ${group.she}") // Prints "He is Hanna and She is Jack"
```

[Back to top](#table-of-contents)

---

## 14. Missing Operator (`8_missing_operator.kts`) <a id="14-missing-operator-8_missing_operatorkts"></a>

**Code:**
```kotlin
val jedi = "Yoda"
val isDark = false

if (jedi != null && isDark) {
    println("What???")
}
jedi?.takeIf{ isDark }.apply{ println("Yoda is Dark!") }
```

**Solution:**  
`isDark` is false, so neither branch prints anything. The lambda in `apply` is called with `null`, so "Yoda is Dark!" is printed once.

**Why is this a great example?**
- Shows how `apply` works even on null receivers.
- Demonstrates subtle differences between control flow and functional style.

**Answer:**
3. Yoda is Dark!

**Further Insights:**
- `apply` executes block on the receiver, even if receiver is `null`.
- Check for null before calling extension functions or use `?.apply`.

**Related Topics:**
- Scope functions (`let`, `apply`, `also`)
- Null-safe call chains
- Functional style vs imperative

**Corrected code:**
```kotlin
val jedi = "Yoda"
val isDark = false

if (jedi != null && isDark) {
    println("What???")
}
jedi?.takeIf{ isDark }?.apply{ println("Yoda is Dark!") } // Only prints if not null
```

[Back to top](#table-of-contents)

---

## 15. Node Creation (`9_node.kts`) <a id="15-node-creation-9_nodekts"></a>

**Code:**
```kotlin
open class Person(val name: String) {
    fun sayName() = println(name)
}

class Father : Person("Father") {
    fun makeChild(name: String): Person? = Person(name)

    val child1 = makeChild("Luke")?.apply { sayName() }
    val child2 = makeChild("Leia").apply { this.sayName() }
}

Father()
```

**Solution:**  
`child1` prints "Luke", `child2` prints "Leia". So output is "Luke\nLeia".

**Why is this a great example?**
- Shows use of `apply` and nullable receivers.
- Demonstrates object creation and method invocation order.

**Answer:**
1. Luke Leia

**Further Insights:**
- `apply` returns the receiver after applying the block.
- Nullable chaining only skips when `?.apply` is used.

**Related Topics:**
- Scope functions
- Nullable chaining
- Object initialization patterns

**Corrected code:**
```kotlin
open class Person(val name: String) {
    fun sayName() = println(name)
}

class Father : Person("Father") {
    fun makeChild(name: String): Person? = Person(name)

    val child1 = makeChild("Luke")?.apply { sayName() }
    val child2 = makeChild("Leia")?.apply { sayName() }
}

Father()
```

[Back to top](#table-of-contents)

---

> **Navigation Index:**  
> [Basics (Part 1)](../basics_ai/solutions.md) | [Initialization (Part 2)](#) | [Concurrency (Part 3)](../concurrency_ai/solutions.md) | [Formatting (Part 4)](../formatting_ai/solutions.md) | [Deprecated Features (Part 5)](../deprecated_ai/solutions.md)
