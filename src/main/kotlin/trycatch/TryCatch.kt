package trycatch

fun main() {
    try {
        for (i in -10..10) {
            println(45 / i)
        }
    } catch (e: Exception) {
        println("caught exception ${e.message}")
    } finally {
        println("Finally")
    }

}