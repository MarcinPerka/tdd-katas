package com.tdd.primefactors

class PrimeFactors {

    fun generate(input: Int): List<Int> {
        val primes = mutableListOf<Int>()
        var n = input
        var candidateFactor = 2
        while (n > 1) {
            while (n % candidateFactor == 0) {
                primes.add(candidateFactor)
                n /= candidateFactor
            }
            candidateFactor++
        }
        return primes
    }
}