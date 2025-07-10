import kotlinx.coroutines.*

fun main() = runBlocking {
    val scope = CoroutineScope(Job())
    scope.launch {
        delay(100)
        println("Done")
    }
}
// Will "Done" print? See the answer and explanation in solutions.md

// Will "Done" always print?
// 1. Yes, always
// 2. No, never
// 3. Only sometimes
// 4. It will not compile
