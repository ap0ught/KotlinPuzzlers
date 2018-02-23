package part1

fun loop(items: List<Double>) {
    items.forEach {
        if (it == 3) return
        print("%.0f".format(it))
    }
    print("done")
}

loop(listOf(1.0, 2.0, 3.0))

// What will it prints?
// 1. 123done
// 2. 12
// 3. Infinite loop
// 4. Will not compile