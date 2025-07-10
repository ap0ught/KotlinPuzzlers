//region suppress
@file:Suppress("NOTHING_TO_INLINE", "UNUSED_VARIABLE")
//endregion
package part2

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

// What will it prints?
// 1. 1
// 2. 2
// 3. 3
// 4. Will not compile

// See the answer and explanation in solutions.md
