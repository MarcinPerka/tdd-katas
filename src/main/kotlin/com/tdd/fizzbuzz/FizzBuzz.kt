package com.tdd.fizzbuzz

class FizzBuzz {

    companion object {
        const val FIZZ = "Fizz"
        const val BUZZ = "Buzz"
        const val FOO = "Foo"
        const val BOO = "Boo"
        const val SMALL = "Small"
        const val BIG = "Big"
        private const val EMPTY = ""
    }

    private val rangeRules = mapOf(SMALL to (1..15), BIG to (95..100))

    private val modRules =
        mapOf(
            FIZZ to 3,
            BUZZ to 5,
            FOO to 7,
            BOO to 11
        )

    fun fizzBuzz(input: Int): String {
        validateInput(input)
        return findCompliantRangeRule(input) ?: findCompliantModRules(input) ?: input.toString()
    }

    fun fizzBuzzSequence(range: IntRange = 1..100) = range.map { fizzBuzz(it) }

    private fun findCompliantRangeRule(input: Int) =
        rangeRules.entries.firstOrNull { (_, rangeRule) -> rangeRule.contains(input) }?.key

    private fun findCompliantModRules(input: Int) =
        findMatchedModRules(input).joinToString(EMPTY) { it.key }.takeIf { it.isNotBlank() }

    private fun findMatchedModRules(input: Int) =
        modRules.entries.filter { (_, mod) -> isCompliantWithModRule(mod, input) }

    private fun isCompliantWithModRule(mod: Int, input: Int) = input % mod == 0

    private fun validateInput(input: Int) {
        if (input < 1 || input > 100)
            throw InvalidNumberException()
    }
}