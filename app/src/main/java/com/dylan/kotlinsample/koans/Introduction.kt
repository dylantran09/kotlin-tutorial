package com.dylan.kotlinsample.koans

import java.util.*

class Introduction {

    // ------------------------------------------
    // Hello, world!
    // ------------------------------------------
    fun start(): String = "Hello"

    // ------------------------------------------
    // Java to Kolin conversation
    // ------------------------------------------
    fun toJSON(collection: Collection<Int>): String {
        val sb = StringBuilder()
        sb.append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("]")
        return sb.toString()
    }

    // ------------------------------------------
    // Named arguments
    // ------------------------------------------
    fun joinOptions(options: Collection<String>) = options.joinToString(", ", "[", "]")

    // ------------------------------------------
    // Default arguments
    // ------------------------------------------
    fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
            (if (toUpperCase) name.toUpperCase() else name) + number

    fun useFoo() = listOf(
            foo("a"),
            foo("b", number = 1),
            foo("c", toUpperCase = true),
            foo(name = "d", number = 2, toUpperCase = true)
    )

    // ------------------------------------------
    //Lambdas
    // ------------------------------------------
    fun containsEven(collection: Collection<Int>): Boolean = collection.any { x -> x % 2 == 0 }

    // ------------------------------------------
    // Strings
    // ------------------------------------------
    val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

    fun getPattern(): String = """\d{2} ${month} \d{4}"""

    // ------------------------------------------
    // Data classes
    // ------------------------------------------
    data class Person(val name: String, val age: Int)

    fun getPeople(): List<Person> {
        return listOf(Person("Alice", 29), Person("Bob", 31))
    }

    // ------------------------------------------
    // Nullable types
    // ------------------------------------------
    class PersonalInfo(val email: String?)

    class Client(val personalInfo: PersonalInfo?)
    interface Mailer {
        fun sendMessage(email: String, message: String)
    }

    fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
        val email: String? = client?.personalInfo?.email;

        if (email == null || message == null) {
            return
        }

        mailer.sendMessage(email, message)
    }

    // ------------------------------------------
    // Smart casts
    // ------------------------------------------
    interface Expr

    class Num(val value: Int) : Expr
    class Sum(val left: Expr, val right: Expr) : Expr

    fun eval(expr: Expr): Int {
        when (expr) {
            is Num -> return expr.value
            is Sum -> return eval(expr.left) + eval(expr.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }
    }

    // ------------------------------------------
    // Extension functions
    // ------------------------------------------
    data class RationalNumber(val numerator: Int, val denominator: Int)

    fun Int.r(): RationalNumber? {
        return RationalNumber(this, 1)
    }

    fun Pair<Int, Int>.r(): RationalNumber? {
        val a = this.toList().get(0)
        val b = this.toList().get(1)
        return RationalNumber(a, b)
    }

    // ------------------------------------------
    // Object expressions
    // ------------------------------------------
    fun getList(): List<Int> {
        val arrayList = arrayListOf(1, 5, 2)
        Collections.sort(arrayList, object : Comparator<Int> {
            override fun compare(lhs: Int, rhs: Int) : Int {
                if (lhs > rhs) {
                    return -1
                } else if (lhs < rhs) {
                    return 1
                } else {
                    return 0
                }
            }
        })
        return arrayList
    }

    // ------------------------------------------
    // SAM Conversions
    // ------------------------------------------
    fun getListWithSAM(): List<Int> {
        val arrayList = arrayListOf(1, 5, 2)
        Collections.sort(arrayList, { x, y -> if (x > y) -1 else if (x < y) 1 else 0 })
        //Collections.sort(arrayList) { x, y -> if (x > y) 1 else if (x < y) -1 else 0 }
        return arrayList
    }

    // ------------------------------------------
    // Extensions on Collections
    // ------------------------------------------
    fun getListWithExtensions(): List<Int> {
        return arrayListOf(1, 5, 2).sortedDescending()
    }
}