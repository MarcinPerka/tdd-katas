package com.tdd.fizzbuzz

class FizzBuzz(
    private val rangeRules: Map<String, IntRange> = mapOf(SMALL to (1..15), BIG to (95..100)),
    private val modRules: Map<String, Int> = mapOf(FIZZ to 3, BUZZ to 5, FOO to 7, BOO to 11),
    private val extendedModsEnabled: Boolean = false
) {

    companion object {
        const val FIZZ = "Fizz"
        const val BUZZ = "Buzz"
        const val FOO = "Foo"
        const val BOO = "Boo"
        const val SMALL = "Small"
        const val BIG = "Big"
        const val FTW = "FTW"
        const val GG = "GG"
        private const val EMPTY = ""
    }

    //input multiples all number from list
    private val andExtendedModRules = mapOf(FTW to listOf(3, 5))

    //input multiples any number from list
    private val orExtendedModRules = mapOf(GG to listOf(3, 5))

    fun fizzBuzz(input: Int) = if (extendedModsEnabled) extendedFizzBuzz(input) else normalFizzBuzz(input)

    private fun extendedFizzBuzz(input: Int) =
        findCompliantRangeRule(input) ?: findCompliantExtendedRule(input) ?: findConcatenatedCompliantModRules(input)
        ?: input.toString()

    private fun normalFizzBuzz(input: Int) =
        findCompliantRangeRule(input) ?: findConcatenatedCompliantModRules(input) ?: input.toString()

    private fun findCompliantExtendedRule(input: Int) =
        andExtendedModRules.entries.firstOrNull { (_, list) -> list.all { input % it == 0 } }?.key
            ?: orExtendedModRules.entries.firstOrNull { (_, list) -> list.any { input % it == 0 } }?.key

    fun fizzBuzzSequence(range: IntRange = 1..100) = range.map { fizzBuzz(it) }

    private fun findCompliantRangeRule(input: Int) =
        rangeRules.entries.firstOrNull { (_, rangeRule) -> rangeRule.contains(input) }?.key

    private fun findConcatenatedCompliantModRules(input: Int) =
        findMatchedModRules(input).joinToString(EMPTY) { it.key }.takeIf { it.isNotBlank() }

    private fun findMatchedModRules(input: Int) =
        modRules.entries.filter { (_, mod) -> isCompliantWithModRule(mod, input) }

    private fun isCompliantWithModRule(mod: Int, input: Int) = input % mod == 0
}