# Kotlin Puzzlers: Concurrency & Advanced Features (Part 3) Solutions

## Table of Contents

1. [Smart Cast & Concurrency](#1-smart-cast--concurrency)
2. [Sealed Class Exhaustiveness](#2-sealed-class-exhaustiveness)
3. [Inline & Crossinline](#3-inline--crossinline)
4. [Typealias Function Nullability](#4-typealias-function-nullability)
5. [Coroutine Scope & Structured Concurrency](#5-coroutine-scope--structured-concurrency)
6. [Reified Generics](#6-reified-generics)
7. [Tailrec Stack Overflow](#7-tailrec-stack-overflow)
8. [Destructuring Nulls](#8-destructuring-nulls)
9. [Companion Extension](#9-companion-extension)
10. [Lateinit Val](#10-lateinit-val)
11. [Operator Resolution](#11-operator-resolution)
12. [Annotation Retention](#12-annotation-retention)
13. [Inline Class vs Data Class](#13-inline-class-vs-data-class)
14. [Reflection Invocation](#14-reflection-invocation)
15. [Lazy Thread Safety](#15-lazy-thread-safety)

---

## 1. Smart Cast & Concurrency <a id="1-smart-cast--concurrency"></a>

**Code:**
```kotlin
var obj: Any = "Kotlin"
if (obj is String) {
    Thread {
        obj = 42
    }.start()
    println(obj.length) // What happens here?
}
```

**Solution:**  
Smart cast assumes `obj` is a String, but another thread can change it before `println(obj.length)` executes, leading to a `ClassCastException`.

**Why is this a great example?**
- Shows smart cast is not thread-safe.
- Highlights concurrency pitfalls in Kotlin.
- Encourages defensive programming with shared mutable state.

**Answer:**  
Throws `ClassCastException` (race condition).

**Corrected code:**
```kotlin
val obj: Any = "Kotlin"
if (obj is String) {
    println(obj.length)
}
```

**Further Insights:**
- Smart casts rely on *immutable* local facts; shared mutability breaks this guarantee.
- Always prefer local copies or `val` when using `is` checks in multi-threaded contexts.
- Consider using `@Volatile` or `AtomicReference` for safe cross‐thread visibility.

**Related Topics:**
- Thread safety and shared mutable state
- `@Volatile` and `AtomicReference`
- Smart cast limitations under concurrency

[Back to top](#table-of-contents)

---

## 2. Sealed Class Exhaustiveness <a id="2-sealed-class-exhaustiveness"></a>

**Code:**
```kotlin
sealed class Result
object Success : Result()
object Failure : Result()

fun handle(result: Result) = when (result) {
    Success -> println("Success")
    // Missing Failure branch!
}
// handle(Failure)
```

**Solution:**  
The `when` expression is not exhaustive; if called with `Failure`, it throws an exception at runtime.

**Why is this a great example?**
- Shows the importance of exhaustive `when` for sealed classes.
- Highlights Kotlin's compile-time safety features.

**Answer:**  
Throws `NoWhenBranchMatchedException` if called with `Failure`.

**Corrected code:**
```kotlin
fun handle(result: Result) = when (result) {
    Success -> println("Success")
    Failure -> println("Failure")
}
```

**Further Insights:**
- The compiler enforces exhaustiveness only in `when` used as an expression; add an `else` branch otherwise.
- Sealed classes are ideal for modeling finite state machines and ensuring all cases are handled.
- IDE warnings can help you spot missing branches early.

**Related Topics:**
- Sealed class hierarchies
- Exhaustive `when` expressions
- Modeling state machines in Kotlin

[Back to top](#table-of-contents)

---

## 3. Inline & Crossinline <a id="3-inline--crossinline"></a>

**Code:**
```kotlin
inline fun runTwice(crossinline block: () -> Unit) {
    repeat(2) { block() }
}

fun main() {
    runTwice {
        // return // What happens if you uncomment this?
        println("Hello")
    }
}
```

**Solution:**  
Uncommenting `return` inside a crossinline block is a compile error; only local returns are allowed.

**Why is this a great example?**
- Shows the difference between `inline` and `crossinline`.
- Highlights restrictions on non-local returns.

**Answer:**  
Will not compile if `return` is uncommented.

**Corrected code:**
```kotlin
runTwice {
    println("Hello")
    // return@runTwice // Only local returns allowed
}
```

**Further Insights:**
- `crossinline` prohibits non-local returns, ensuring inlining doesn't violate control flow.
- Use labeled returns (`return@label`) to exit lambdas without breaking surrounding inlined code.
- Overuse of inline can bloat bytecode; restrict inlining to performance-critical paths.

**Related Topics:**
- Inline functions performance
- Labeled returns (`return@label`)
- Crossinline vs noinline

[Back to top](#table-of-contents)

---

## 4. Typealias Function Nullability <a id="4-typealias-function-nullability"></a>

**Code:**
```kotlin
typealias Callback = () -> Unit

fun call(cb: Callback?) {
    cb()
}

call(null)
```

**Solution:**  
Calling `cb()` when `cb` is null throws a `NullPointerException`.

**Why is this a great example?**
- Shows pitfalls of nullable function types.
- Encourages null checks before invocation.

**Answer:**  
Throws `NullPointerException`.

**Corrected code:**
```kotlin
fun call(cb: Callback?) {
    cb?.invoke()
}
```

**Further Insights:**
- Nullable function types need safe calls or eldvis (`?:`) to prevent NPEs.
- Consider wrapping callbacks in `Result` or `Either` for safer APIs.
- Use `requireNotNull(cb)` at function start if `cb` truly must be non-null.

**Related Topics:**
- Nullable function types
- Safe-call (`?.invoke()`)
- Designing robust callback APIs

[Back to top](#table-of-contents)

---

## 5. Coroutine Scope & Structured Concurrency <a id="5-coroutine-scope--structured-concurrency"></a>

**Code:**
```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    val scope = CoroutineScope(Job())
    scope.launch {
        delay(100)
        println("Done")
    }
}
```

**Solution:**  
The launched coroutine may not complete before `runBlocking` exits, so "Done" may not print.

**Why is this a great example?**
- Shows importance of structured concurrency.
- Highlights coroutine lifecycle and scope management.

**Answer:**  
"Done" will likely not print.

**Corrected code:**
```kotlin
fun main() = runBlocking {
    val job = launch {
        delay(100)
        println("Done")
    }
    job.join()
}
```

**Further Insights:**
- Prefer `coroutineScope { }` or `supervisorScope { }` to tie child jobs to the parent scope.
- Avoid creating your own `CoroutineScope` inside `runBlocking`; use the provided scope.
- Structured concurrency simplifies error propagation and resource cleanup.

**Related Topics:**
- `runBlocking` vs `coroutineScope`
- Job hierarchies and supervisors
- Exception handling in coroutines

[Back to top](#table-of-contents)

---

## 6. Reified Generics <a id="6-reified-generics"></a>

**Code:**
```kotlin
inline fun <reified T> isType(value: Any) = value is T
fun main() {
    val result = isType<String>("Kotlin") to isType<Int>("Kotlin")
    println(result)
}
```

**Solution:**  
`isType<String>("Kotlin")` is true; `isType<Int>("Kotlin")` is false ⇒ prints `(true, false)`.

**Why a great example?**  
Highlights how Kotlin preserves type checks at runtime via reified.

**Answer:**
1. (true, false)

**Corrected code:**
```kotlin
fun main() {
    println("Kotlin" is String to ("Kotlin" is Int))
}
```

**Further Insights:**
- `reified` type parameters allow safe casts and reflection on generics without extra boilerplate.
- Only available to `inline` functions; use them sparingly to balance code size.
- Use `inline` + `reified` for DSL builders and type-safe APIs.

**Related Topics:**
- `inline` with `reified`
- Type-safe builders
- Runtime type checks

[Back to top](#table-of-contents)

---

## 7. Tailrec Stack Overflow <a id="7-tailrec-stack-overflow"></a>

**Code:**
```kotlin
tailrec fun countdown(n: Int): Int =
    if (n == 0) 0 else countdown(n - 1) + 0
fun main() = println(countdown(100000))
```

**Solution:**  
Although marked `tailrec`, the `+ 0` prevents proper tail call elimination ⇒ StackOverflowError.

**Why a great example?**  
Shows subtle requirement for tailrec to be last call.

**Answer:**
2. StackOverflowError

**Corrected code:**
```kotlin
tailrec fun countdown(n: Int): Int =
    if (n == 0) 0 else countdown(n - 1)
```

**Further Insights:**
- The recursive call must be the *last* operation for proper tail-call elimination.
- Kotlin’s `tailrec` modifier only works on JVM; other targets may not optimize.
- For complex recursion, consider converting to iterative loops or using sequences.

**Related Topics:**
- Tail-call elimination requirements
- Recursive vs iterative approaches
- Sequence generation

[Back to top](#table-of-contents)

---

## 8. Destructuring Nulls <a id="8-destructuring-nulls"></a>

**Code:**
```kotlin
data class Pair(val a: Int, val b: Int)
fun main() {
    val p: Pair? = null
    val (x, y) = p
    println("$x,$y")
}
```

**Solution:**  
Destructuring a nullable without safe‐call ⇒ NullPointerException.

**Why a great example?**  
Demonstrates unsafe destructuring on null.

**Answer:**
2. Throws NullPointerException

**Corrected code:**
```kotlin
val (x, y) = p ?: Pair(0, 0)
```

**Further Insights:**
- Destructuring declarations can be combined with null-coalescing for safety.
- When dealing with multiple nullable types, prefer `?.let { }` or `run`.
- For larger data models, consider explicit null checks over destructuring.

**Related Topics:**
- Destructuring declarations
- Safe-call with destructuring (`?:`)
- Data classes and component functions

[Back to top](#table-of-contents)

---

## 9. Companion Extension <a id="9-companion-extension"></a>

**Code:**
```kotlin
class MyClass { companion object }
fun MyClass.Companion.foo() = "Hi"
fun main() = println(MyClass.foo())
```

**Solution:**  
Companion extension is valid ⇒ prints `Hi`.

**Why a great example?**  
Shows how to extend companion objects.

**Answer:**
1. Hi

**Further Insights:**
- Companion object extensions effectively extend the *static* API of a class.
- Use `@JvmStatic` on companion members when interacting with Java callers.
- Companion extensions cannot access private members unless in the same file.

**Related Topics:**
- Companion objects as singletons
- Extension functions on objects
- Static API design in Kotlin

[Back to top](#table-of-contents)

---

## 10. Lateinit Val <a id="10-lateinit-val"></a>

**Code:**
```kotlin
class C { lateinit val s: String }
fun main() = println(C().s.length)
```

**Solution:**  
`lateinit` isn’t allowed on `val` ⇒ compilation error.

**Why a great example?**  
Highlights misuse of `lateinit`.

**Answer:**
4. It will not compile

**Corrected code:**
```kotlin
class C {
    lateinit var s: String
}
```

**Further Insights:**
- `lateinit` is limited to `var` properties of non-nullable types.
- Use `Delegates.notNull()` for non-nullable `val` with custom initialization timing.
- Overuse of `lateinit` can mask initialization bugs; prefer constructor injection.

**Related Topics:**
- `lateinit` limitations
- `Delegates.notNull()`
- Constructor vs property injection

[Back to top](#table-of-contents)

---

## 11. Operator Resolution <a id="11-operator-resolution"></a>

**Code:**
```kotlin
data class Point(val x: Int, val y: Int) {
    operator fun plus(p: Point) = Point(x + p.x, y + p.y)
}

fun main() {
    val p1 = Point(1, 2)
    val p2 = Point(3, 4)
    val p3 = p1 + p2
    println(p3)
}
```

**Solution:**  
Invokes `plus` method via operator overloading ⇒ prints `Point(x=4, y=6)`.

**Why a great example?**  
Demonstrates Kotlin's operator overloading.

**Answer:**
1. Point(x=4, y=6)

**Further Insights:**
- Kotlin resolves operator calls to the most specific overload in scope.
- Avoid ambiguous overloads by using distinct function names or explicit calls.
- Operator overloading should mimic natural semantics to avoid confusion.

**Related Topics:**
- Operator overload precedence
- Infix functions
- DSL design with operators

[Back to top](#table-of-contents)

---

## 12. Annotation Retention <a id="12-annotation-retention"></a>

**Code:**
```kotlin
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MyAnnotation

@MyAnnotation
fun annotatedFunction() {}

fun main() {
    val annotations = ::annotatedFunction.annotations
    println(annotations)
}
```

**Solution:**  
`MyAnnotation` is retained at runtime, so it’s accessible via reflection.

**Why a great example?**  
Shows Kotlin's annotation retention policies.

**Answer:**
1. [@MyAnnotation()]

**Further Insights:**
- `SOURCE` retention is stripped after compilation; use `BINARY` or `RUNTIME` for reflection.
- Combine `@Repeatable` with `RUNTIME` retention to support multiple annotations.
- Runtime retention increases metadata size; use judiciously.

**Related Topics:**
- Retention policies (`SOURCE`, `BINARY`, `RUNTIME`)
- Reflection and metadata
- Custom annotation design

[Back to top](#table-of-contents)

---

## 13. Inline Class vs Data Class <a id="13-inline-class-vs-data-class"></a>

**Code:**
```kotlin
inline class Password(val value: String)
data class User(val name: String, val password: Password)

fun main() {
    val user = User("Alice", Password("secret"))
    println(user)
}
```

**Solution:**  
`Password` is an inline class wrapping a String. `User` is a data class with an inline class property.

**Why a great example?**  
Demonstrates use of inline classes for type safety.

**Answer:**
1. User(name=Alice, password=Password(value=secret))

**Further Insights:**
- Inline classes can improve performance by avoiding heap allocations.
- Data classes generate `equals`/`hashCode`/`toString`; inline classes do not auto-generate these.
- Inline classes must have a single `val` parameter; they cannot hold multiple properties.

**Related Topics:**
- Inline classes performance benefits
- Data class auto-generated methods
- Value classes in Kotlin

[Back to top](#table-of-contents)

---

## 14. Reflection Invocation <a id="14-reflection-invocation"></a>

**Code:**
```kotlin
class MyClass {
    fun greet() = "Hello, Kotlin!"
}

fun main() {
    val instance = MyClass()
    val method = instance::class.members.find { it.name == "greet" }
    println(method?.call(instance))
}
```

**Solution:**  
Uses reflection to find and invoke `greet` method ⇒ prints `Hello, Kotlin!`.

**Why a great example?**  
Shows Kotlin's reflection capabilities.

**Answer:**
1. Hello, Kotlin!

**Further Insights:**
- Reflection can bypass visibility but may require `isAccessible = true`.
- Use `kotlin.reflect.full.declaredFunctions` for compile-time checks.
- Reflection calls are slower; cache method references when performance matters.

**Related Topics:**
- Kotlin reflection APIs
- `isAccessible` for private members
- Performance considerations

[Back to top](#table-of-contents)

---

## 15. Lazy Thread Safety <a id="15-lazy-thread-safety"></a>

**Code:**
```kotlin
object O { val x by lazy { Thread.sleep(10); 10 } }
fun main() = println(O.x)
```

**Solution:**  
Default `lazy` is thread-safe ⇒ always prints `10`.

**Why a great example?**  
Highlights default synchronization of `lazy`.

**Answer:**
1. Always 10

**Further Insights:**
- Default `lazy` uses `SYNCHRONIZED` mode; other modes: `PUBLICATION`, `NONE`.
- Choose `Publication` when safe or performance-critical non-blocking is needed.
- Explicitly document lazy behavior in public APIs to avoid surprises.

**Related Topics:**
- `lazy` modes (`SYNCHRONIZED`, `PUBLICATION`, `NONE`)
- Double-checked locking
- Lazy initialization patterns

[Back to top](#table-of-contents)

---

> **Navigation Index:**  
> [Basics (Part 1)](../basics_ai/solutions.md) | [Initialization (Part 2)](../initialization_ai/solutions.md) | [Concurrency (Part 3)](#) | [Formatting (Part 4)](../formatting_ai/solutions.md) | [Deprecated Features (Part 5)](../deprecated_ai/solutions.md)

## Quick Reference: Concurrency Best Practices

* **Smart Casts**: Use `val` or local copies when checking types in concurrent contexts
* **Coroutines**: Prefer structured concurrency with parent-child relationships
* **Thread Safety**: Mark shared mutable properties with `@Volatile` or use `AtomicReference`
* **Function Types**: Always use safe-call (`?.invoke()`) with nullable function types
* **Lazy Properties**: Default `lazy` is thread-safe; choose `LazyThreadSafetyMode` deliberately
