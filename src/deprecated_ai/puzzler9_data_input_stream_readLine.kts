import java.io.DataInputStream

fun main() {
    val dis = DataInputStream(System.`in`)
    val line = dis.readLine()
    println(line)
}

// Which DataInputStream method is deprecated?
// 1. readByte()
// 2. readLine()
// 3. readUTF()
// 4. skipBytes()

