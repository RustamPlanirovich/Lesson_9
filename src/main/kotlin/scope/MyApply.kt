package scope


//fun <T> T.myApply(f: T.() -> Unit): T {
//    this.f()
//    return this
//}

fun <T> T.myApply(f: T.() -> Unit): T = this.apply(f)

fun createMap(): Map<Int, String> {
    return hashMapOf<Int, String>().myApply {
        put(0, "zero")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}

fun main() {
    println(createMap())
}