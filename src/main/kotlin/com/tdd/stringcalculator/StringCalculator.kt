package com.tdd.stringcalculator

class StringCalculator {

    companion object {
        private val delimiters = arrayOf(",", "\n")
    }

    fun add(input: String): Long {
        return if (input.isBlank()) {
            0
        } else {
            val delimiters = getDelimiters(input)
            val inputWithoutCustomDelimiter = getInputWithoutCustomDelimiter(input)
            val (positiveNumbers, negativeNumbers) = splitToLongs(
                inputWithoutCustomDelimiter,
                delimiters
            ).partition { it >= 0 }
            if (negativeNumbers.isNotEmpty())
                throw RuntimeException("negative numbers not allowed: $negativeNumbers")

            positiveNumbers.filter { it <= 1000 }.sum()
        }
    }

    private fun getDelimiters(input: String): Array<String> {
        val customDelimiter = input.substringAfter("//", "").takeIf { it.isNotBlank() }?.substringBefore("\n")
        return if (customDelimiter != null) arrayOf(*delimiters, customDelimiter) else arrayOf(*delimiters)
    }

    private fun getInputWithoutCustomDelimiter(input: String) = input.substringAfter("//", "")
        .takeIf { it.isNotBlank() }?.substringAfter("\n") ?: input

    private fun splitToLongs(input: String, delimiters: Array<String>) = input.split(*delimiters).map { it.toLong() }
}