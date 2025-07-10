fun buildUrl(
    host: String = "localhost",
    port: Int = 8080,
    useSSL: Boolean = false
) = "${if (useSSL) "https" else "http"}://$host:$port"
// What’s the best formatting rule?
// 1. All parameters on one line
// 2. Align default values vertically
// 3. Place each on its own line (current)
// 4. Use trailing commas
// See solutions.md for answer and explanation

