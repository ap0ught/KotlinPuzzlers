class Example {
    private var _value: Int? = null
    val value: Int
        get() {
            if (_value == null) _value = compute()
            return _value!!
        }
    fun compute() = 42
}

// What practice does this illustrate?
// 1. Lazy delegate
// 2. Backing property pattern
// 3. `lateinit` usage
// 4. Null default
// See solutions.md for answer and explanation

