package part2

fun hello1() {
    print("1")
}

fun hello2() = print("2")

fun hello3() = {
    print("3")
}

hello1()
hello2()
hello3()

// What will it prints?
// 1. 1
// 2. 12
// 3. 123
// 4. Will not compile

// See the answer and explanation in solutions.md
