fun main() {
    val t = Thread { println("Running") }
    t.start()
    t.suspend()
}

// Which Thread method is deprecated here?
// 1. suspend()
// 2. start()
// 3. interrupt()
// 4. isAlive()
