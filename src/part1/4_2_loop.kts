package part1

fun test() {
    val items = listOf(1.0, 2.0, 3.0)
    items.forEach {
        if (it == 3.0) return
        print("%.0f".format(it))
    }
    print("done")
}

test()

// What will it prints (male or female logic)?
// 1. 123done
// 2. 12done
// 3. 12
// 4. Will not compile