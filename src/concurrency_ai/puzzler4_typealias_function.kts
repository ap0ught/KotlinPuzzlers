// ...existing code...
typealias Callback = () -> Unit

fun call(cb: Callback?) {
    cb()
}

call(null)

// What happens when you run this code?
// 1. It prints nothing
// 2. It throws NullPointerException
// 3. It prints "null"
// 4. It will not compile
