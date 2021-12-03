package scope

class Window {
    var width: Int = 0
    var height: Int = 0
    var isVisible: Boolean = false
}

fun main() {

    val builder = StringBuilder()
    with(builder) {
        for (letter in 'a'..'z')
            append(letter)
    }

    println(builder)

    builder.apply {
        append("123")
    }


    var mystring: String = "123"
    mystring?.let {
        println(it)
    }

    val map = mutableMapOf(1 to Window())
    val window1 = map[2]?.run {
        width = 100
        isVisible = true
    }

    val string = "String"

    string.also {
        println("$it has a lenght of ${it.length}")
    }

    listOf("one", "two", "three")
        .filter { it.length > 3 }
        .also { println("$it has a lenght of ${it.size}") }

    run { println("Hello!") }
}