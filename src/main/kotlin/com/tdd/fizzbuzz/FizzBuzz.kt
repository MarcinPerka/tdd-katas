package com.tdd.fizzbuzz

class FizzBuzz {

    companion object {
        const val FIZZ = "Fizz"
        const val BUZZ = "Buzz"
        const val FIZZ_BUZZ = "$FIZZ$BUZZ"
        const val FOO = "Foo"
        const val BOO = "Boo"
        const val FOO_BOO = "$FOO$BOO"
        const val FIZZ_FOO = "$FIZZ$FOO"
        const val FIZZ_BOO = "$FIZZ$BOO"
        const val BUZZ_FOO = "$BUZZ$FOO"
        const val BUZZ_BOO = "$BUZZ$BOO"
    }

    private val rules =
        mapOf(
            FIZZ_BUZZ to listOf(3, 5),
            FIZZ_FOO to listOf(3, 7),
            FIZZ_BOO to listOf(3, 11),
            BUZZ_FOO to listOf(5, 7),
            BUZZ_BOO to listOf(5, 11),
            FOO_BOO to listOf(7, 11),
            FIZZ to listOf(3),
            BUZZ to listOf(5),
            FOO to listOf(7),
            BOO to listOf(11)
        )

    fun fizzBuzz(input: Int): String {
        validateInput(input)
        return rules.entries.firstOrNull { entry -> entry.value.all { input % it == 0 } }?.key ?: input.toString()
    }

    fun fizzBuzzSequence(range: IntRange = 1..100) = range.map { fizzBuzz(it) }

    private fun validateInput(input: Int) {
        if (input < 1 || input > 100)
            throw InvalidNumberException()
    }
}