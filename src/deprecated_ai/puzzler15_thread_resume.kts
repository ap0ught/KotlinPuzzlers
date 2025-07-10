fun main() {
    val t = Thread { println("Running") }
    t.start()
    t.resume()
}

// Which Thread method is deprecated here?
// 1. resume()
// 2. start()
// 3. interrupt()
// 4. wait()
