sealed class Result
object Success : Result()
object Failure : Result()

fun handle(result: Result) = when (result) {
    Success -> println("Success")
    // Missing Failure branch!
}
// handle(Failure)
// See the answer and explanation in solutions.md

// What happens if you call handle(Failure)?
// 1. It prints "Failure"
// 2. It prints "Success"
// 3. It throws NoWhenBranchMatchedException
// 4. It will not compile
