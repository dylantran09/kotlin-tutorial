package com.dylan.kotlinsample.koans

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import com.dylan.kotlinsample.koans.Introduction.*

class IntroductionUnitTest {

    val introduction: Introduction = Introduction()

    @Before
    fun init() {
        //
    }

    // ------------------------------------------
    // Hello, world!
    // ------------------------------------------
    @Test
    fun testStart() {
        Assert.assertEquals("Start", "Hello", introduction.start())
    }

    // ------------------------------------------
    // Java to Kolin conversation
    // ------------------------------------------
    @Test
    fun collection() {
        Assert.assertEquals("toJSON", "[1, 2, 3, 42, 555]", introduction.toJSON(listOf(1, 2, 3, 42, 555)))
    }

    // ------------------------------------------
    // Named arguments
    // ------------------------------------------
    @Test
    fun testJoinToString() {
        Assert.assertEquals("joinOptions", "[yes, no, may be]", introduction.joinOptions(listOf("yes", "no", "may be")))
    }

    // ------------------------------------------
    // Default arguments
    // ------------------------------------------
    @Test
    fun testDefaultAndNamedParams() {
        Assert.assertEquals(listOf("a42", "b1", "C42", "D2"), introduction.useFoo())
    }

    // ------------------------------------------
    //Lambdas
    // ------------------------------------------
    @Test
    fun contains() {
        Assert.assertTrue("The result should be true if the collection contains an even number", introduction.containsEven(listOf(1, 2, 3, 126, 555)))
    }

    @Test
    fun notContains() {
        Assert.assertFalse("The result should be false if the collection doesn't contain an even number", introduction.containsEven(listOf(43, 33)))
    }

    // ------------------------------------------
    // Strings
    // ------------------------------------------
    private fun testMatch(date: String) = Assert.assertTrue("The pattern should match $date", date.matches(introduction.getPattern().toRegex()))
    private fun testMismatch(date: String) = Assert.assertFalse("The pattern shouldn't match $date", date.matches(introduction.getPattern().toRegex()))

    @Test
    fun match() {
        testMatch("11 MAR 1952")
    }

    @Test
    fun match1() {
        testMatch("24 AUG 1957")
    }

    @Test
    fun doNotMatch() {
        testMismatch("24 RRR 1957")
    }

    // ------------------------------------------
    // Data classes
    // ------------------------------------------
    @Test
    fun testListOfPeople() {
        Assert.assertEquals("[Person(name=Alice, age=29), Person(name=Bob, age=31)]", introduction.getPeople().toString())
    }

    // ------------------------------------------
    // Nullable types
    // ------------------------------------------
    fun testSendMessageToClient(
            client: Client?,
            message: String?,
            expectedEmail: String? = null,
            shouldBeInvoked: Boolean = false
    ) {
        var invoked = false
        val expectedMessage = message
        introduction.sendMessageToClient(client, message, object : Mailer {
            public override fun sendMessage(email: String, message: String) {
                invoked = true
                Assert.assertEquals("The message is not as expected:",
                        expectedMessage, message)
                Assert.assertEquals("The email is not as expected:",
                        expectedEmail, email)
            }
        })
        Assert.assertEquals("The function 'sendMessage' should${if (shouldBeInvoked) "" else "n't"} be invoked",
                shouldBeInvoked, invoked)
    }

    @Test
    fun everythingIsOk() {
        testSendMessageToClient(Client(PersonalInfo("bob@gmail.com")),
                "Hi Bob! We have an awesome proposition for you...",
                "bob@gmail.com",
                true)
    }

    @Test
    fun noMessage() {
        testSendMessageToClient(Client(PersonalInfo("bob@gmail.com")), null)
    }

    @Test
    fun noEmail() {
        testSendMessageToClient(Client(PersonalInfo(null)), "Hi Bob! We have an awesome proposition for you...")
    }

    @Test
    fun noPersonalInfo() {
        testSendMessageToClient(Client(null), "Hi Bob! We have an awesome proposition for you...")
    }

    @Test
    fun noClient() {
        testSendMessageToClient(null, "Hi Bob! We have an awesome proposition for you...")
    }

    // ------------------------------------------
    // Smart casts
    // ------------------------------------------
    @Test
    fun testNum() {
        Assert.assertEquals("'eval' on Num should work:", 2, introduction.eval(Num(2)))
    }

    @Test
    fun testSum() {
        Assert.assertEquals("'eval' on Sum should work:", 3, introduction.eval(Sum(Num(2), Num(1))))
    }

    @Test
    fun testRecursion() {
        Assert.assertEquals("'eval' should work:", 6, introduction.eval(Sum(Sum(Num(1), Num(2)), Num(3))))
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

    @Test
    fun testIntExtension() {
        Assert.assertEquals("Rational number creation error: ", RationalNumber(4, 1), 4.r())
    }

    @Test
    fun testPairExtension() {
        Assert.assertEquals("Rational number creation error: ", RationalNumber(2, 3), Pair(2, 3).r())
    }

    // ------------------------------------------
    // Object expressions
    // ------------------------------------------
    @Test
    fun testSort() {
        Assert.assertEquals("getList", listOf(5, 2, 1), introduction.getList())
    }

    // ------------------------------------------
    // SAM Conversions
    // ------------------------------------------
    @Test
    fun testSortWithSAM() {
        Assert.assertEquals("getListWithSAM", listOf(5, 2, 1), introduction.getListWithSAM())
    }

    // ------------------------------------------
    // Extensions on Collections
    // ------------------------------------------
    @Test fun testSortWithExtension() {
        Assert.assertEquals(listOf(5, 2, 1), introduction.getListWithExtensions())
    }
}
