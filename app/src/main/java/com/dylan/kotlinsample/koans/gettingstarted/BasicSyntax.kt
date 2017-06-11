package com.dylan.kotlinsample.koans.gettingstarted

class BasicSyntax {

    var a: Int = 1
    var b = 2
    val c: Int = 0

    fun sum(a: Int, b: Int): Int = a + b

    fun printSum(a: Int, b: Int) {
        println("sum of $a and $b is ${sum(a, b)}")
    }
}
