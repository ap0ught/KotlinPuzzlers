fun main() {
    val t = Thread { println("Running") }
    t.start()
    t.stop()
}

// Which Thread method is deprecated?
// 1. start()
// 2. run()
// 3. stop()
// 4. interrupt()

