package com.dylan.kotlinsample.koans.gettingstarted

class Idioms {

    data class Customer(val name: String, var email: String)

    // Default values for function parameters
    fun foo(a: Int = 0, b: String = "") {
        println("a = $a and b = '$b'")
    }

    // Filtering a list

    // String Interpolation

    // Instance Checks

    // Traversing a map/list of pairs

    // Using ranges
    fun usingRanges() {
        for (i in 1..100) {
            // closed range: includes 100
        }
        for (i in 1 until 100) {
            // half-open range: does not include 100
        }
        for (x in 2..10 step 2) {

        }
        for (x in 10 downTo 1) {

        }
        val x = 5
        if (x in 1..10) {

        }
    }

    // Read-only list
    val list = listOf("a", "b", "c")

    // Read-only map
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)

    //Extension Functions
    fun String.spaceToCamelCase() {
        // "Convert this to camelcase".spaceToCamelCase()
    }

    // Creating a singleton
    object Resource {
        val name = "Name"
    }

    fun conversion() {
        val a: Int = 2
        val b: Long = a.toLong()
        val l: Int = (1L + 3).toInt()
    }

    fun expression(a: Int?, b: Int?) {
        val a1 = a ?: 1
    }

    fun label() {
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (i > 50 && j > 50) break@loop
            }
        }
    }

    fun lambda() {
    }
}
