package part1

fun loop(items: List<Int>) {
    items.forEach {
        if (it == 3) return
        print(it)
    }
    print("done")
}

loop(listOf(1, 2, 3))

// What will it prints?
// 1. 123done
// 2. 12done
// 3. 12
// 4. Infinite loop