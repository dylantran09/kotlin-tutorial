package com.dylan.kotlinsample.koans

import org.junit.Assert
import org.junit.Test

class BasicsUnitTest {

    var basic = Basics();

    @Test
    fun testParseIntCorrectly() {
        Assert.assertEquals(1, basic.parseInt("1"))
    }

    @Test
    fun testParseIntNull() {
        Assert.assertEquals(null, basic.parseInt(null))
    }

    @Test
    fun testParseIntEmpty() {
        Assert.assertEquals(null, basic.parseInt(""))
    }

    @Test
    fun testParseIntIncorrectly() {
        Assert.assertEquals(null, basic.parseInt("1ace"))
    }
}

