package com.tdd.stringcalculator

class StringCalculator {

    companion object {
        private const val DECLARATION_OF_CUSTOM_DELIMITER = "//"
        private const val COMMA = ","
        private const val NEW_LINE = "\n"
        private const val EMPTY_STRING = ""
        private val standardDelimiters = arrayOf(COMMA, NEW_LINE)
        private val isNotNegative: (Long) -> Boolean = { it >= 0 }
        private val isLessThanOrEqual1000: (Long) -> Boolean = { it <= 1000 }
    }

    fun add(input: String) = when (input.isBlank()) {
        true -> 0
        false -> sum(filterLessThanOrEqual1000(assertNoNegatives(splitAndParse(input))))
    }

    private fun sum(numbers: List<Long>) = numbers.sum()

    private fun filterLessThanOrEqual1000(numbers: List<Long>) = numbers.filter(isLessThanOrEqual1000)

    private fun assertNoNegatives(numbers: List<Long>): List<Long> {
        val (noNegatives, negatives) = numbers.partition(isNotNegative)
        if (negatives.isNotEmpty()) {
            throw RuntimeException("negative numbers not allowed: $negatives")
        }

        return noNegatives
    }

    private fun splitAndParse(input: String): List<Long> {
        val delimiters = combineDelimiters(extractCustomDelimiter(input))
        return extractChainOfNumbers(input).split(*delimiters).map { it.toLong() }
    }

    private fun extractCustomDelimiter(input: String) =
        input.substringAfter(DECLARATION_OF_CUSTOM_DELIMITER, EMPTY_STRING)
            .takeIf { it.isNotBlank() }
            ?.substringBefore(NEW_LINE)

    private fun combineDelimiters(customDelimiter: String?) =
        customDelimiter?.let { standardDelimiters + it } ?: standardDelimiters

    private fun extractChainOfNumbers(input: String) =
        input.substringAfter(DECLARATION_OF_CUSTOM_DELIMITER, EMPTY_STRING)
            .takeIf { it.isNotBlank() }?.substringAfter(NEW_LINE) ?: input
}