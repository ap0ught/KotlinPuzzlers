# Kotlin Puzzlers: Basics (Part 1) Solutions

## Table of Contents

1. [Who's Logic?](#1-whos-logic-1_whoslogickts)
2. [Is Empty](#2-is-empty-2_0_isemptykts)
3. [Is Null Or Empty](#3-is-null-or-empty-2_1_isnulloremptykts)
4. [Types and Return](#4-types-and-return-3_1_typeskts)
5. [Loop and Return](#5-loop-and-return-3_2_loopkts)
6. [Types in Ranges](#6-types-in-ranges-3_3_typeskts)
7. [Syntax](#7-syntax-4_syntaxkts)
8. [Long Line](#8-long-line-5_long_linekts)
9. [Operator Overloading](#9-operator-overloading-6_pluskts)

---

## 1. Who's Logic? (`1_whoslogic.kts`) <a id="1-whos-logic-1_whoslogickts"></a>

**Code:**
```kotlin
val yes: String? = "yes"
println( yes == "yes" ? "yes" : "maybe" )
```

**Solution:**  
Kotlin does **not** support the ternary operator (`? :`). This code will not compile and will produce a syntax error.

**Why is this a good example?**
- Highlights a common mistake for developers transitioning from Java to Kotlin.
- Reinforces Kotlin's idiomatic conditional expressions.
- Helps prevent syntax errors and encourages learning Kotlin's control flow.

**Answer:**
3. will not compile

**Further Insights:**
- Kotlin uses `if` expressions instead of the ternary operator.
- Smart casts only work on immutable locals; multi-threaded mutations break the guarantee.

**Related Topics:**
- Kotlin control flow
- Expression vs statement
- Smart casts and type system

---

## 2. Is Empty (`2_0_isempty.kts`) <a id="2-is-empty-2_0_isemptykts"></a>

**Code:**
```kotlin
val list = listOf<String>()
println(if (list.isEmpty()) "empty" else "not empty")
```

**Solution:**  
The code checks if a list is empty using the `isEmpty()` method. If the list is empty, it prints "empty"; otherwise, it prints "not empty".

**Why is this a good example?**
- Demonstrates the use of Kotlin's standard library functions.
- Illustrates idiomatic Kotlin control flow with `if` expressions.
- Reinforces understanding of collection operations in Kotlin.

**Answer:**
2. Nothing

**Further Insights:**
- Safe-call (`?.`) on a `null` receiver yields `null`, not `true`.
- In an `if`, `null` is treated as `false`.

**Related Topics:**
- Nullable Boolean pitfalls
- Safe-call vs Elvis operator
- Kotlin null-safety idioms

---

## 3. Is Null Or Empty (`2_1_isnullorempty.kts`) <a id="3-is-null-or-empty-2_1_isnulloremptykts"></a>

**Code:**
```kotlin
val str: String? = ""
println(str.isNullOrEmpty())
```

**Solution:**  
The `isNullOrEmpty()` extension function checks if a string is either `null` or empty (`""`). It returns `true` for both cases.

**Why is this a good example?**
- Highlights Kotlin's safe call operator (`?.`) and the Elvis operator (`?:`).
- Demonstrates a common idiom for checking nullability and emptiness.
- Reinforces the importance of null safety in Kotlin.

**Answer:**
1. empty

**Further Insights:**
- `isNullOrEmpty()` is a concise null + empty check.
- Prefer standard‐library helpers over manual checks.

**Related Topics:**
- Kotlin standard library
- String extensions
- Null and empty handling

---

## 4. Types and Return (`3_1_types.kts`) <a id="4-types-and-return-3_1_typeskts"></a>

**Code:**
```kotlin
fun foo(): Any {
    return 123
}
println(foo() is Int)
```

**Solution:**  
The function `foo()` returns an `Int`, but its return type is declared as `Any`. The `is` operator checks if the result of `foo()` is an instance of `Int`.

**Why is this a good example?**
- Demonstrates Kotlin's type system and smart casts.
- Illustrates the use of the `is` operator for type checking.
- Reinforces the concept of function return types in Kotlin.

**Answer:**
1. 123done

**Further Insights:**
- Comparing different numeric types always false (`Double` vs `Int`).
- Be explicit with types to avoid logic errors.

**Related Topics:**
- Numeric conversions
- Type inference
- Smart‐cast subtleties

---

## 5. Loop and Return (`3_2_loop.kts`) <a id="5-loop-and-return-3_2_loopkts"></a>

**Code:**
```kotlin
val result = StringBuilder()
for (i in 1..3) {
    result.append(i)
}
println(result)
```

**Solution:**  
The code appends numbers 1 to 3 to a `StringBuilder` instance and then prints the result. The range `1..3` is inclusive.

**Why is this a good example?**
- Demonstrates the use of Kotlin's `for` loop with ranges.
- Illustrates how to build strings efficiently with `StringBuilder`.
- Reinforces the concept of loop control in Kotlin.

**Answer:**
3. 12

**Further Insights:**
- `return` inside a `forEach` exits the outer function, not just the loop.
- Use `break` or labeled returns for finer control.

**Related Topics:**
- Kotlin lambdas vs loop constructs
- Local and non-local returns
- Labeled returns

---

## 6. Types in Ranges (`3_3_types.kts`) <a id="6-types-in-ranges-3_3_typeskts"></a>

**Code:**
```kotlin
val i = 3.5
when(i) {
    in 0..3 -> println("$i in 0..3")
    !in 0..3 -> println("$i not in 0..3")
    else -> println("else")
}
```

**Solution:**  
`0..3` is an `IntRange`, but `i` is a `Double`. `Double` is not in `IntRange`, so `!in 0..3` matches and prints `3.5 not in 0..3`.

**Why is this a good example?**
- Demonstrates type compatibility in range checks.
- Highlights how ranges work with different types in Kotlin.

**Answer:**
2. 3.5 not in 0..3

**Further Insights:**
- Ranges are type‐specific (`IntRange`, `DoubleRange`).
- `in` / `!in` use the range’s `contains` method.

**Related Topics:**
- Range expressions
- Operator functions
- Type compatibility

---

## 7. Syntax (`4_syntax.kts`) <a id="7-syntax-4_syntaxkts"></a>

**Code:**
```kotlin
fun foo(a: Boolean, b: Boolean) = print("a=$a, b=$b")

val a = 1
val b = 2
val c = 3
val d = 4
foo(c < a, b > d)
```

**Solution:**  
`c < a` is `3 < 1` → `false`, `b > d` is `2 > 4` → `false`. So prints: `a=false, b=false`.

**Why is this a good example?**
- Shows how expressions are evaluated and passed as arguments.
- Reinforces Boolean logic in Kotlin.

**Answer:**
2. a=false, b=false

**Further Insights:**
- Boolean expressions are evaluated before being passed.
- No implicit conversions between numbers and booleans.

**Related Topics:**
- Expression evaluation order
- Boolean logic
- Function call semantics

---

## 8. Long Line (`5_long_line.kts`) <a id="8-long-line-5_long_linekts"></a>

**Code:**
```kotlin
val youNameVariablesSuchLongDontYou = 2
val inMostCasesAnyLongNameCanBeShortened = 3

val result =
    1 and youNameVariablesSuchLongDontYou
            +  inMostCasesAnyLongNameCanBeShortened

println(result)
```

**Solution:**  
Operator precedence:  
`1 and youNameVariablesSuchLongDontYou` → `1 and 2` → `0`  
`0 + inMostCasesAnyLongNameCanBeShortened` → `0 + 3` → `3`  
So prints `3`.

**Why is this a good example?**
- Demonstrates operator precedence and line breaks.
- Shows how formatting can affect code interpretation.

**Answer:**
3. 3

**Further Insights:**
- Operator precedence: `and` before `+`.
- Line breaks do not alter precedence rules.

**Related Topics:**
- Kotlin operators
- Precedence and associativity
- Bitwise operations

---

## 9. Operator Overloading (`6_plus.kts`) <a id="9-operator-overloading-6_pluskts"></a>

**Code:**
```kotlin
infix operator fun Int.plus(other: Int) = this - other

println(-1 + 1)
println(-1 plus 1)
println(-1.plus(1))
```

**Solution:**  
Custom `plus` operator subtracts instead of adds:
- `-1 + 1` → `-1.minus(1)` → `-2`
- `-1 plus 1` → `-1.minus(1)` → `-2`
- `-1.plus(1)` → `-2`

**Why is this a good example?**
- Shows operator overloading and how it can change expected behavior.
- Reinforces caution when overriding standard operators.

**Answer:**
1. -2  -2  -2

**Further Insights:**
- Custom `plus` overrides built‐in arithmetic.
- Keep operator semantics intuitive to avoid surprise.

**Related Topics:**
- Operator overloading
- Infix functions
- DSL design patterns

---

> **Navigation Index:**  
> [Basics (Part 1)](#) | [Initialization (Part 2)](../initialization_ai/solutions.md) | [Concurrency (Part 3)](../concurrency_ai/solutions.md) | [Formatting (Part 4)](../formatting_ai/solutions.md) | [Deprecated Features (Part 5)](../deprecated_ai/solutions.md)
