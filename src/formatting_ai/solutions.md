# Kotlin Puzzlers: Formatting & Style (Part 4) Solutions

## Table of Contents

1. [var vs val](#1-var-vs-val-puzzler1_var_vs_valkts)
2. [String Templates](#2-string-templates-puzzler2_string_templateskts)
3. [When Expression](#3-when-expression-puzzler3_when_expressionkts)
4. [Scoping Functions](#4-scoping-functions-puzzler4_scoping_functionskts)
5. [Default Args Alignment](#5-default-args-alignment-puzzler5_default_args_alignmentkts)
6. [KDoc Comment](#6-kdoc-comment-puzzler6_kdoc_commentkts)
7. [Extension vs Top](#7-extension-vs-top-puzzler7_extension_vs_topkts)
8. [Data Class Copy](#8-data-class-copy-puzzler8_data_class_copykts)
9. [Backing Property](#9-backing-property-puzzler9_backing_propertykts)
10. [Visibility Modifiers](#10-visibility-modifiers-puzzler10_visibility_modifierskts)
11. [const vs val](#11-const-vs-val-puzzler11_const_vs_valkts)
12. [Sealed Usage](#12-sealed-usage-puzzler12_sealed_usagekts)
13. [Redundant Semicolon](#13-redundant-semicolon-puzzler13_semicolon_redundantkts)
14. [Lambda Format](#14-lambda-format-puzzler14_lambda_formatkts)
15. [Trailing Commas](#15-trailing-commas-puzzler15_trailing_commaskts)

---

## 1. var vs val (puzzler1_var_vs_val.kts) <a id="1-var-vs-val-puzzler1_var_vs_valkts"></a>

**Solution:**  
Use a functional fold and immutable `val`:
```kotlin
val count = listOf(1, 2, 3).fold(0) { acc, i -> acc + i }
println(count)
```

**Why:**
- Eliminates mutable state
- Embraces functional style
- Simplifies reasoning and testing

**Related Topics:**  
fold · immutability · functional collections

**Non-idiomatic alternative (works, but against standards):**
```kotlin
var count = 0
for (i in listOf(1, 2, 3)) {
    count += i
}
println(count)
```

[Back to top](#table-of-contents)

---

## 2. String Templates (puzzler2_string_templates.kts) <a id="2-string-templates-puzzler2_string_templateskts"></a>

**Solution:**  
Prefer string templates:
```kotlin
fun greet(name: String) {
    println("Hello, $name!")
}
```

**Why:**
- Cleaner and more readable
- Avoids concatenation pitfalls

**Related Topics:**  
string templates · readability

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun greet(name: String) {
    println("Hello, " + name + "!")
}
```

[Back to top](#table-of-contents)

---

## 3. When Expression (puzzler3_when_expression.kts) <a id="3-when-expression-puzzler3_when_expressionkts"></a>

**Solution:**  
Use `when` as an expression:
```kotlin
fun grade(score: Int): String = when (score) {
    in 90..100 -> "A"
    in 80..89  -> "B"
    else       -> "F"
}
```

**Why:**
- Returns a value directly
- Simplifies branching logic

**Related Topics:**  
expression vs statement · control flow

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun grade(score: Int): String {
    var result = ""
    when (score) {
        in 90..100 -> result = "A"
        in 80..89  -> result = "B"
        else       -> result = "F"
    }
    return result
}
```

[Back to top](#table-of-contents)

---

## 4. Scoping Functions (puzzler4_scoping_functions.kts) <a id="4-scoping-functions-puzzler4_scoping_functionskts"></a>

**Solution:**  
Use `let` for null-safe scope:
```kotlin
fun printLength(s: String?) {
    s?.let { println(it.length) }
}
```

**Why:**
- Eliminates explicit null-check
- Provides concise scope for `s`

**Related Topics:**  
let · null-safety · scope functions

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun printLength(s: String?) {
    if (s != null) {
        println(s.length)
    }
}
```

[Back to top](#table-of-contents)

---

## 5. Default Args Alignment (puzzler5_default_args_alignment.kts) <a id="5-default-args-alignment-puzzler5_default_args_alignmentkts"></a>

**Solution:**  
Current multi-line formatting is preferred, aligning defaults:
```kotlin
fun buildUrl(
    host: String = "localhost",
    port: Int    = 8080,
    useSSL: Boolean = false
) = "${if (useSSL) "https" else "http"}://$host:$port"
```

**Why:**
- Clear diffs when adding parameters
- Readable default values

**Related Topics:**  
formatting rules · trailing commas

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun buildUrl(host: String = "localhost", port: Int = 8080, useSSL: Boolean = false) =
    "${if (useSSL) "https" else "http"}://$host:$port"
```

[Back to top](#table-of-contents)

---

## 6. KDoc Comment (puzzler6_kdoc_comment.kts) <a id="6-kdoc-comment-puzzler6_kdoc_commentkts"></a>

**Solution:**  
Add detailed KDoc:
```kotlin
/**
 * Calculates the sum of two Ints.
 *
 * @param a first operand
 * @param b second operand
 * @return sum of a and b
 */
fun sum(a: Int, b: Int) = a + b
```

**Why:**
- Generates proper documentation
- IDE support for parameter hints

**Related Topics:**  
KDoc · API documentation

**Non-idiomatic alternative (works, but against standards):**
```kotlin
// Calculates the sum of two Ints.
fun sum(a: Int, b: Int) = a + b
```

[Back to top](#table-of-contents)

---

## 7. Extension vs Top (puzzler7_extension_vs_top.kts) <a id="7-extension-vs-top-puzzler7_extension_vs_topkts"></a>

**Solution:**  
Use a top-level utility to avoid polluting `String`:
```kotlin
fun trimAndLower(s: String) = s.trim().toLowerCase()
```

**Why:**
- Restricts API surface
- Improves discoverability

**Related Topics:**  
extension functions · API design

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun String.trimAndLower() = trim().toLowerCase()
```

[Back to top](#table-of-contents)

---

## 8. Data Class Copy (puzzler8_data_class_copy.kts) <a id="8-data-class-copy-puzzler8_data_class_copykts"></a>

**Solution:**  
`copy` preserves immutability:
```kotlin
val p2 = p1.copy(age = 31)
```

**Why:**
- Prevents state mutation
- Clear, concise updates

**Related Topics:**  
data classes · immutability

**Non-idiomatic alternative (works, but against standards):**
```kotlin
val p2 = Person(p1.name, 31)
```

[Back to top](#table-of-contents)

---

## 9. Backing Property (puzzler9_backing_property.kts) <a id="9-backing-property-puzzler9_backing_propertykts"></a>

**Solution:**  
Prefer `lazy` delegate:
```kotlin
val value: Int by lazy { compute() }
```

**Why:**
- Thread-safe by default
- Reduces boilerplate

**Related Topics:**  
delegation · lazy initialization

**Non-idiomatic alternative (works, but against standards):**
```kotlin
class Example {
    lateinit var value: Int
    fun initialize() { value = compute() }
}
```

[Back to top](#table-of-contents)

---

## 10. Visibility Modifiers (puzzler10_visibility_modifiers.kts) <a id="10-visibility-modifiers-puzzler10_visibility_modifierskts"></a>

**Solution:**  
Use `internal` to restrict module scope:
```kotlin
internal fun internalCall() { /* ... */ }
```

**Why:**
- Enforces encapsulation
- Clear intent

**Related Topics:**  
visibility modifiers · modular design

**Non-idiomatic alternative (works, but against standards):**
```kotlin
public fun internalCall() { /* ... */ }
```

[Back to top](#table-of-contents)

---

## 11. const vs val (puzzler11_const_vs_val.kts) <a id="11-const-vs-val-puzzler11_const_vs_valkts"></a>

**Solution:**  
Mark compile-time constants with `const`:
```kotlin
const val PI = 3.1415
```

**Why:**
- Inlined usage
- Improved performance

**Related Topics:**  
const properties · compile-time constants

**Non-idiomatic alternative (works, but against standards):**
```kotlin
val E = 2.718  // should be const
```

[Back to top](#table-of-contents)

---

## 12. Sealed Usage (puzzler12_sealed_usage.kts) <a id="12-sealed-usage-puzzler12_sealed_usagekts"></a>

**Solution:**  
Use exhaustive `when`:
```kotlin
fun area(shape: Shape) = when(shape) {
    is Circle -> Math.PI * shape.r * shape.r
    is Square -> shape.s * shape.s
}
```

**Why:**
- Compile-time safety
- Clear pattern matching

**Related Topics:**  
sealed classes · pattern matching

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun area(shape: Shape): Double {
    return if (shape is Circle) {
        Math.PI * shape.r * shape.r
    } else {
        (shape as Square).s * (shape as Square).s
    }
}
```

[Back to top](#table-of-contents)

---

## 13. Redundant Semicolon (puzzler13_semicolon_redundant.kts) <a id="13-redundant-semicolon-puzzler13_semicolon_redundantkts"></a>

**Solution:**  
Remove redundant semicolon:
```kotlin
println("Hello")
```

**Why:**
- Cleaner syntax
- Idiomatic Kotlin

**Related Topics:**  
syntax rules · code style

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun main() {
    println("Hello");
}
```

[Back to top](#table-of-contents)

---

## 14. Lambda Format (puzzler14_lambda_format.kts) <a id="14-lambda-format-puzzler14_lambda_formatkts"></a>

**Solution:**  
Break chain for readability:
```kotlin
listOf(1, 2, 3)
    .map { it * 2 }
    .filter { it > 4 }
```

**Why:**
- Improves visual flow
- Easier maintenance

**Related Topics:**  
method chaining · formatting

**Non-idiomatic alternative (works, but against standards):**
```kotlin
listOf(1, 2, 3).map { it * 2 }.filter { it > 4 }
```

[Back to top](#table-of-contents)

---

## 15. Trailing Commas (puzzler15_trailing_commas.kts) <a id="15-trailing-commas-puzzler15_trailing_commaskts"></a>

**Solution:**  
Keep trailing comma:
```kotlin
fun apiCall(
    a: Int,
    b: Int,
) = a + b
```

**Why:**
- Simplifies adding new parameters
- Cleaner diffs

**Related Topics:**  
trailing commas · version control diffs

**Non-idiomatic alternative (works, but against standards):**
```kotlin
fun apiCall(
    a: Int,
    b: Int
) = a + b
```

---

> **Navigation Index:**  
> [Basics (Part 1)](../basics_ai/solutions.md) | [Initialization (Part 2)](../initialization_ai/solutions.md) | [Concurrency (Part 3)](../concurrency_ai/solutions.md) | [Formatting (Part 4)](#) | [Deprecated Features (Part 5)](../deprecated_ai/solutions.md)

## Kotlin Style Quick Reference

| Category | Recommendation |
|----------|---------------|
| **Variables** | Prefer `val` over `var` |
| **Strings** | Use string templates (`$variable`) |
| **Control Flow** | Use `when` as an expression |
| **Nulls** | Use scope functions (`?.let`, `?.run`) |
| **Parameters** | Multi-line with alignment for readability |
| **Documentation** | KDoc for public APIs |
| **Extension Functions** | Avoid polluting common types |
| **Classes** | Use data class `copy()` |
| **Properties** | `lazy` for delayed initialization |
| **Visibility** | `internal` for module-only usage |
| **Constants** | `const val` for compile-time constants |
| **Polymorphism** | Sealed classes with exhaustive `when` |
| **Syntax** | No semicolons |
| **Lambdas** | Break chains for readability |
| **Lists** | Use trailing commas |
