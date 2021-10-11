package com.tdd.stringcalculator

class StringCalculator {

    companion object {
        private val delimiters = arrayOf(",", "\n")
        private const val DECLARATION_OF_CUSTOM_DELIMITER = "//"
        private const val EMPTY_STRING = ""
        private val isPositiveOrZero: (Long) -> Boolean = { it >= 0 }
        private val isLessThanOrEqualThousand: (Long) -> Boolean = { it <= 1000 }
    }

    fun add(input: String): Long {
        return if (input.isBlank()) {
            0
        } else {
            val delimiters = getDelimiters(input)
            val chainOfNumbers = getChainOfNumbers(input)
            val (positiveOrZeroNumbers, negativeNumbers) = splitChainOfNumbers(
                chainOfNumbers,
                delimiters
            ).partition(isPositiveOrZero)
            if (negativeNumbers.isNotEmpty())
                throw RuntimeException("negative numbers not allowed: $negativeNumbers")

            positiveOrZeroNumbers.filter(isLessThanOrEqualThousand).sum()
        }
    }

    private fun getDelimiters(input: String): Array<String> {
        val customDelimiter =
            input.substringAfter(DECLARATION_OF_CUSTOM_DELIMITER, EMPTY_STRING).takeIf { it.isNotBlank() }
                ?.substringBefore(System.lineSeparator())
        return if (customDelimiter != null) arrayOf(*delimiters, customDelimiter) else arrayOf(*delimiters)
    }

    private fun getChainOfNumbers(input: String) = input.substringAfter(DECLARATION_OF_CUSTOM_DELIMITER, EMPTY_STRING)
        .takeIf { it.isNotBlank() }?.substringAfter(System.lineSeparator()) ?: input

    private fun splitChainOfNumbers(chainOfNumbers: String, delimiters: Array<String>) =
        chainOfNumbers.split(*delimiters).map { it.toLong() }
}