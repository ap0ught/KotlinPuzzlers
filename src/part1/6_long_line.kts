package part1

val reallyVeryLongAndComplicatedCalculatedValueProcessedInBackgroundThread = 2
val anotherReallyVeryLongAndComplicatedCalculatedValueProcessedInBackgroundThread = 3

val result =
    1
       + reallyVeryLongAndComplicatedCalculatedValueProcessedInBackgroundThread
           + anotherReallyVeryLongAndComplicatedCalculatedValueProcessedInBackgroundThread


println(result)

// What will it prints?
// 1. 1
// 2. 2
// 3. 3
// 4. 5