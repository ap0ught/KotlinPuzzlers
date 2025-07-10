var obj: Any = "Kotlin"
if (obj is String) {
    Thread {
        obj = 42
    }.start()
    println(obj.length) // What happens here?
}

// What will happen when running this code?
// 1. It prints 6
// 2. It throws ClassCastException
// 3. It prints 42
// 4. It will not compile

// See the answer and explanation in solutions.md
