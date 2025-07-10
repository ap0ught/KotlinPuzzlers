# Kotlin Puzzlers: Deprecated Features (Part 5) Solutions

## Table of Contents
1. [String casing](#1-string-casing-puzzler1_deprecated_featureskts)  
2. [Date.year](#2-dateyear-puzzler2_date_yearkts)  
3. [Date.month](#3-datemonth-puzzler3_date_monthkts)  
4. [Date.day](#4-dateday-puzzler4_date_daykts)  
5. [Date.hours](#5-datehours-puzzler5_date_hourskts)  
6. [Date.minutes](#5-dateminutes-puzzler6_date_minuteskts)  
7. [Date.seconds](#7-dateseconds-puzzler7_date_secondskts)  
8. [Thread.stop](#8-threadstop-puzzler8_thread_stopkts)  
9. [DataInputStream.readLine](#9-datainputstreamreadline-puzzler9_data_input_stream_readlinekts)  
10. [String.capitalize/decapitalize](#10-stringcapitalizedecapitalize-puzzler10_string_capitalizekts)  
11. [Char.toLowerCase/toUpperCase](#11-chartolowercasetouppercase-puzzler11_char_tolowercasekts)  
12. [Date.setYear](#12-datesetyear-puzzler12_date_setyearkts)  
13. [Date.setMonth](#13-datesetmonth-puzzler13_date_setmonthkts)  
14. [Thread.suspend](#14-threadsuspend-puzzler14_thread_suspendkts)  
15. [Thread.resume](#15-threadresume-puzzler15_thread_resumekts)  
16. [System.runFinalizersOnExit](#16-systemrunfinalizersonexit-puzzler16_system_run_finalizers_on_exitkts)

---

## 1. String casing (`puzzler1_deprecated_features.kts`) <a id="1-string-casing-puzzler1_deprecated_featureskts"></a>

**Answer:**  
3. Both toLowerCase() and toUpperCase()

**Explanation:**  
Kotlin stdlib deprecated these in favor of locale-aware `lowercase()` and `uppercase()`.

**Best Practice:**  
```kotlin
println(greeting.lowercase())
println(greeting.uppercase())
```

**Further Insights:**  
- `lowercase()`/`uppercase()` respect the default locale or you can pass a `Locale`.  
- Deprecation helps migrate code to more predictable Unicode handling.

**Non-idiomatic alternative (still works):**  
```kotlin
println(greeting.toLowerCase())
println(greeting.toUpperCase())
```

**Related Topics:**  
String API · Locale handling · Unicode standards

[Back to top](#table-of-contents)

---

## 2. Date.year (`puzzler2_date_year.kts`) <a id="2-dateyear-puzzler2_date_yearkts"></a>

**Answer:**  
4. time

**Explanation:**  
All Date getters (year, month, etc.) except `time` are deprecated. Use `java.time` instead.

**Best Practice:**  
```kotlin
val year = java.time.LocalDate.now().year
```

**Further Insights:**  
- `java.time` (JSR-310) offers immutable, thread-safe types.  
- Deprecated Date API methods are from 1990s Java, replaced to avoid timezone bugs.

**Non-idiomatic alternative (still works):**  
```kotlin
println(d.year)
```

**Related Topics:**  
java.time API · LocalDate · migration guides

[Back to top](#table-of-contents)

---

## 3. Date.month (`puzzler3_date_month.kts`) <a id="3-datemonth-puzzler3_date_monthkts"></a>

**Answer:**  
4. time

**Explanation:**  
`Date.month` is deprecated; use `LocalDate.monthValue`.

**Best Practice:**  
```kotlin
val month = java.time.LocalDate.now().monthValue
```

**Further Insights:**  
- Deprecated getters are zero-based or locale-sensitive, leading to off-by-one errors.  
- `java.time.Month` enum provides clear semantics.

**Non-idiomatic alternative (still works):**  
```kotlin
println(d.month)
```

**Related Topics:**  
ChronoField · Month enum · Date-time parsing

[Back to top](#table-of-contents)

---

## 4. Date.day (`puzzler4_date_day.kts`) <a id="4-dateday-puzzler4_date_daykts"></a>

**Answer:**  
2. time

**Explanation:**  
`Date.day` is deprecated. Use `LocalDate.dayOfMonth`.

**Best Practice:**  
```kotlin
val day = java.time.LocalDate.now().dayOfMonth
```

**Further Insights:**  
- Deprecated `day` returns day of week (0–6), not day of month.  
- Modern API eliminates ambiguity.

**Non-idiomatic alternative (still works):**  
```kotlin
println(d.day)
```

**Related Topics:**  
DayOfWeek · LocalDate · date/time pitfalls

[Back to top](#table-of-contents)

---

## 5. Date.hours (`puzzler5_date_hours.kts`) <a id="5-datehours-puzzler5_date_hourskts"></a>

**Answer:**  
4. time

**Explanation:**  
`Date.hours` is deprecated; use `LocalTime.hour`.

**Best Practice:**  
```kotlin
val hour = java.time.LocalTime.now().hour
```

**Further Insights:**  
- Deprecated hours always in local timezone, confusing in global apps.  
- `OffsetTime`/`ZonedDateTime` handle offsets explicitly.

**Non-idiomatic alternative (still works):**  
```kotlin
println(d.hours)
```

**Related Topics:**  
LocalTime · timezone conversion · legacy date APIs

[Back to top](#table-of-contents)

---

## 6. Date.minutes (`puzzler6_date_minutes.kts`) <a id="6-dateminutes-puzzler6_date_minuteskts"></a>

**Answer:**  
4. time

**Explanation:**  
`Date.minutes` is deprecated; use `LocalTime.minute`.

**Best Practice:**  
```kotlin
val minute = java.time.LocalTime.now().minute
```

**Further Insights:**  
- Mixing deprecated Date with new java.time types can cause conversion boilerplate.  
- Use `Instant` when working with timestamps.

**Non-idiomatic alternative (still works):**  
```kotlin
println(d.minutes)
```

**Related Topics:**  
Instant · Duration · legacy interop

[Back to top](#table-of-contents)

---

## 7. Date.seconds (`puzzler7_date_seconds.kts`) <a id="7-dateseconds-puzzler7_date_secondskts"></a>

**Answer:**  
4. time

**Explanation:**  
`Date.seconds` is deprecated; use `LocalTime.second`.

**Best Practice:**  
```kotlin
val second = java.time.LocalTime.now().second
```

**Further Insights:**  
- Deprecated seconds return 0–61 (leap seconds!), new API handles these edge cases.  
- For high-precision timing, use `Instant`/`Duration`.

**Non-idiomatic alternative (still works):**  
```kotlin
println(d.seconds)
```

**Related Topics:**  
Duration · ChronoUnit · high-precision timing

[Back to top](#table-of-contents)

---

## 8. Thread.stop (`puzzler8_thread_stop.kts`) <a id="8-threadstop-puzzler8_thread_stopkts"></a>

**Answer:**  
3. stop()

**Explanation:**  
`Thread.stop()` is unsafe and deprecated since Java 1.2; interrupts are preferred.

**Best Practice:**  
```kotlin
t.interrupt()
```

**Further Insights:**  
- `stop()` can leave locks held and corrupt object state.  
- Use cooperative cancellation with interrupts and checks.

**Non-idiomatic alternative (still works):**  
```kotlin
t.stop()
```

**Related Topics:**  
Thread interruption · concurrency best practices · structured concurrency

[Back to top](#table-of-contents)

---

## 9. DataInputStream.readLine (`puzzler9_data_input_stream_readLine.kts`) <a id="9-datainputstreamreadline-puzzler9_data_input_stream_readlinekts"></a>

**Answer:**  
2. readLine()

**Explanation:**  
`readLine()` on `DataInputStream` is deprecated due to encoding issues. Use buffered readers.

**Best Practice:**  
```kotlin
System.`in`.bufferedReader().readLine()
```

**Further Insights:**  
- Deprecated method uses modified UTF-8, which can misinterpret data.  
- Kotlin I/O extensions provide concise wrappers.

**Non-idiomatic alternative (still works):**  
```kotlin
dis.readLine()
```

**Related Topics:**  
I/O streams · Charset handling · Kotlin I/O extensions

[Back to top](#table-of-contents)

---

## 10. String.capitalize/decapitalize (`puzzler10_string_capitalize.kts`) <a id="10-stringcapitalizedecapitalize-puzzler10_string_capitalizekts"></a>

**Answer:**  
3. Both capitalize() and decapitalize()

**Explanation:**  
Deprecated in stdlib 1.5+, replaced by `replaceFirstChar { it.uppercaseChar() }` / `lowercaseChar()`.

**Best Practice:**  
```kotlin
println(s.replaceFirstChar { it.uppercaseChar() })
println(s.replaceFirstChar { it.lowercaseChar() })
```

**Further Insights:**  
- New approach handles empty strings safely.  
- Unicode rules for casing vary per locale.

**Non-idiomatic alternative (still works):**  
```kotlin
s.capitalize()
s.decapitalize()
```

**Related Topics:**  
String case conversions · Unicode · locale-sensitive formatting

[Back to top](#table-of-contents)

---

## 11. Char.toLowerCase/toUpperCase (`puzzler11_char_toLowerCase.kts`) <a id="11-chartolowercasetouppercase-puzzler11_char_tolowercasekts"></a>

**Answer:**  
3. Both toLowerCase() and toUpperCase()

**Explanation:**  
These are deprecated in favor of `lowercaseChar()` and `uppercaseChar()`.

**Best Practice:**  
```kotlin
println(c.lowercaseChar())
println(c.uppercaseChar())
```

**Further Insights:**  
- Char methods now align with String API naming consistency.  
- Use `titlecaseChar()` for proper Unicode titlecasing.

**Non-idiomatic alternative (still works):**  
```kotlin
c.toLowerCase()
c.toUpperCase()
```

**Related Topics:**  
Char extensions · Unicode casing · Kotlin 1.5 migration

[Back to top](#table-of-contents)

---

## 12. Date.setYear (`puzzler12_date_setYear.kts`) <a id="12-datesetyear-puzzler12_date_setyearkts"></a>

**Answer:**  
1. setYear()

**Explanation:**  
`Date.setYear(int)` has been deprecated for decades. Use `java.time` classes.

**Best Practice:**  
```kotlin
val year = java.time.LocalDate.now().year
```

**Further Insights:**  
- Deprecated setters mutate legacy `Date` objects unpredictably.  
- Modern API provides immutable date/time.

**Non-idiomatic alternative (works, but against standards):**  
```kotlin
d.setYear(120)
```

**Related Topics:**  
java.time API · immutability · migration guides

[Back to top](#table-of-contents)

---

## 13. Date.setMonth (`puzzler13_date_setMonth.kts`) <a id="13-datesetmonth-puzzler13_date_setmonthkts"></a>

**Answer:**  
1. setMonth()

**Explanation:**  
`Date.setMonth(int)` is deprecated; use `LocalDate`.

**Best Practice:**  
```kotlin
val month = java.time.LocalDate.now().monthValue
```

**Further Insights:**  
- Setter is zero-based and ambiguous.  
- `Month` enum eliminates confusion.

**Non-idiomatic alternative (works, but against standards):**  
```kotlin
d.setMonth(5)
```

**Related Topics:**  
java.time · Month enum · date formatting

[Back to top](#table-of-contents)

---

## 14. Thread.suspend (`puzzler14_thread_suspend.kts`) <a id="14-threadsuspend-puzzler14_thread_suspendkts"></a>

**Answer:**  
1. suspend()

**Explanation:**  
`Thread.suspend()` is inherently deadlock-prone and deprecated.

**Best Practice:**  
Use a flag or interruption:
```kotlin
@Volatile var running = true
while (running) { /* work */ }
running = false
```

**Further Insights:**  
- Suspending a thread stops it while holding locks.  
- Prefer cooperative interruption.

**Non-idiomatic alternative (works, but against standards):**  
```kotlin
t.suspend()
```

**Related Topics:**  
thread safety · interruption · concurrency

[Back to top](#table-of-contents)

---

## 15. Thread.resume (`puzzler15_thread_resume.kts`) <a id="15-threadresume-puzzler15_thread_resumekts"></a>

**Answer:**  
1. resume()

**Explanation:**  
`Thread.resume()` is deprecated alongside `suspend()`.

**Best Practice:**  
Use higher‐level concurrency tools (coroutines, executors).

**Further Insights:**  
- Resuming threads manually can corrupt state.  
- Structured concurrency abstracts these issues.

**Non-idiomatic alternative (works, but against standards):**  
```kotlin
t.resume()
```

**Related Topics:**  
coroutines · executors · thread pools

[Back to top](#table-of-contents)

---

## 16. System.runFinalizersOnExit (`puzzler16_system_run_finalizers_on_exit.kts`) <a id="16-systemrunfinalizersonexit-puzzler16_system_run_finalizers_on_exitkts"></a>

**Answer:**  
1. runFinalizersOnExit()

**Explanation:**  
Deprecated due to unpredictable finalizer execution order.

**Best Practice:**  
Avoid finalizers; use `try-with-resources` or `AutoCloseable`.

**Further Insights:**  
- Finalizers are unreliable and may run on VM exit only.  
- Use explicit resource management (`use` in Kotlin).

**Non-idiomatic alternative (works, but against standards):**  
```kotlin
System.runFinalizersOnExit(true)
```

**Related Topics:**  
resource management · AutoCloseable · Kotlin `use`

---

> **Navigation Index:**  
> [Basics (Part 1)](../basics_ai/solutions.md) | [Initialization (Part 2)](../initialization_ai/solutions.md) | [Concurrency (Part 3)](../concurrency_ai/solutions.md) | [Formatting (Part 4)](../formatting_ai/solutions.md) | [Deprecated Features (Part 5)](#)

## Deprecated Features Migration Chart

| Deprecated Feature | Modern Replacement |
|-------------------|-------------------|
| `toLowerCase()` | `lowercase()` |
| `toUpperCase()` | `uppercase()` |
| `Date.year` | `LocalDate.now().year` |
| `Date.month` | `LocalDate.now().monthValue` |
| `Date.day` | `LocalDate.now().dayOfMonth` |
| `Date.hours` | `LocalTime.now().hour` |
| `Date.minutes` | `LocalTime.now().minute` |
| `Date.seconds` | `LocalTime.now().second` |
| `Thread.stop()` | Cooperative cancellation |
| `DataInputStream.readLine()` | `BufferedReader.readLine()` |
| `capitalize()` | `replaceFirstChar { it.uppercaseChar() }` |
| `decapitalize()` | `replaceFirstChar { it.lowercaseChar() }` |
| `Char.toLowerCase()` | `Char.lowercaseChar()` |
| `Char.toUpperCase()` | `Char.uppercaseChar()` |
