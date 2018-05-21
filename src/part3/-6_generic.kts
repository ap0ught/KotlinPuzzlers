package part3

class Cup<in T>

open class All

val anys: Cup<Any> = Cup<All>()
val nothings: Cup<Nothing> = Cup<All>()

// What will it prints?
// 1. Will compile
// 2. Will not compile