package com.tdd.fizzbuzz

class FizzBuzz {

    companion object {
        val FIZZ = "Fizz"
        val BUZZ = "Buzz"
        val FIZZ_BUZZ = "$FIZZ$BUZZ"
    }

    fun fizzBuzz(input: Int): String {
        validateInput(input)
        return when {
            isFizzBuzz(input) -> FIZZ_BUZZ
            isFizz(input) -> FIZZ
            isBuzz(input) -> BUZZ
            else -> input.toString()
        }
    }

    fun fizzBuzzSequence(range: IntRange = 1..100) = range.map { fizzBuzz(it) }

    private fun validateInput(input: Int) {
        if (input < 1 || input > 100)
            throw InvalidNumberException()
    }

    private fun isFizzBuzz(input: Int) = isFizz(input) && isBuzz(input)

    private fun isBuzz(input: Int) = input % 5 == 0

    private fun isFizz(input: Int) = input % 3 == 0
}