package com.dylan.kotlinsample.koans

class Basics {

    fun basic(): Unit {

    }

    fun parseInt(str: String?) : Int? {
        try {
            return str?.toInt()
        } catch (e: NumberFormatException) {
            return null
        }
    }
}
