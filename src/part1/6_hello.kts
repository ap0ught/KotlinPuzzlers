package part1

fun hello1() {
    print("Hello1")
}

fun hello2() = print("Hello2")

fun hello3() = {
    print("Hello3")
}

hello1()
hello2()
hello3()

// What will it prints?
// 1. Hello1
// 2. Hello1Hello2
// 3. Hello1Hello2Hello3
// 4. Will not compile