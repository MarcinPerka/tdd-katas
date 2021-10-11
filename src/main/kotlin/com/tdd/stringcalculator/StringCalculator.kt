package com.tdd.stringcalculator

class StringCalculator {
    fun add(input: String): Long {
        return if (input.isEmpty()){
            0
        } else {
            val (positiveNumbers, negativeNumbers) = splitToLongs(input).partition { it >= 0 }
            if(negativeNumbers.isNotEmpty())
                throw RuntimeException("negative numbers not allowed: $negativeNumbers")

            positiveNumbers.filter { it <= 1000 }.sum()
        }
    }

    private fun splitToLongs(input: String) = input.split(",", "\n").map { it.toLong() }
}