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
        false -> input.splitAndParse().assertNoNegatives().filterLessThanOrEqual1000().sum()
    }

    private fun List<Long>.filterLessThanOrEqual1000() = filter(isLessThanOrEqual1000)

    private fun List<Long>.assertNoNegatives() = partition(isNotNegative).throwIfFoundNegatives().first

    private fun Pair<NoNegatives, Negatives>.throwIfFoundNegatives() =
        if (second.isNotEmpty()) throw RuntimeException("negative numbers not allowed: $second") else this

    private fun String.splitAndParse(): List<Long> {
        val delimiters = extractCustomDelimiter().combineDelimiters()
        return extractChainOfNumbers().split(*delimiters).map { it.toLong() }
    }

    private fun String.extractCustomDelimiter() =
        substringAfter(DECLARATION_OF_CUSTOM_DELIMITER, EMPTY_STRING)
            .takeIf { it.isNotBlank() }
            ?.substringBefore(NEW_LINE)

    private fun String?.combineDelimiters() = if (this == null) standardDelimiters else standardDelimiters + this

    private fun String.extractChainOfNumbers() =
        substringAfter(DECLARATION_OF_CUSTOM_DELIMITER, EMPTY_STRING)
            .takeIf { it.isNotBlank() }?.substringAfter(NEW_LINE) ?: this
}