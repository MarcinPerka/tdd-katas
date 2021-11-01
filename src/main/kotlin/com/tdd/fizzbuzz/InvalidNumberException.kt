package com.tdd.fizzbuzz

class InvalidNumberException(message: String = "Not valid input number. Number should be from range 1..100") : RuntimeException(message)