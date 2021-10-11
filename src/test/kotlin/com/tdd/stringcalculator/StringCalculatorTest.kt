package com.tdd.stringcalculator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class StringCalculatorTest {

    private val tested = StringCalculator()

    @Test
    fun `should return zero for empty string`() {
        //given
        val input = ""
        val expected = 0L
        //when
        val actual = tested.add(input)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(value = ["1;1", "2;2", "50;50", "1000;1000"], delimiter = ';')
    fun `should return value for the single number`(input: String, expected: Long) {
        //given

        //when
        val actual = tested.add(input)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(value = ["1,2;3", "2,10;12", "50,100;150", "500,500;1000"], delimiter = ';')
    fun `should return sum for the two numbers comma delimited`(input: String, expected: Long) {
        //given

        //when
        val actual = tested.add(input)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(value = ["1.2;3", "2.10;12", "50.100;150", "900.900;1800"], delimiter = ';')
    fun `should return sum for the two numbers new line delimited`(input: String, expected: Long) {
        //given
        val inputWithNewLine = input.replace('.', '\n')

        //when
        val actual = tested.add(inputWithNewLine)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        value = ["1,2.4;7", "2.100,10;112", "50.1000,100;1150", "500,500.500;1500"],
        delimiter = ';'
    )
    fun `should return sum for the three numbers delimited by new line or comma`(input: String, expected: Long) {
        //given
        val inputWithNewLine = input.replace('.', '\n')

        //when
        val actual = tested.add(inputWithNewLine)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        value = ["-1,-2;negatives not allowed: -1,-2", "-1,2,-3;negatives not allowed: -1,-3", "50,100,-1000;negatives not allowed: -1000"],
        delimiter = ';'
    )
    fun `should throw an exception for the negative numbers`(input: String, expectedMessage: String) {
        //given

        //when

        //then
        assertThrows<RuntimeException>(expectedMessage) { tested.add(input) }
    }

    @ParameterizedTest
    @CsvSource(
        value = ["1000;1000", "1001;0", "20,30,1500;50", "999,2,10001;1001"],
        delimiter = ';'
    )
    fun `should ignore numbers greater than 1000`(input: String, expected: Long) {
        //given

        //when
        val actual = tested.add(input)

        //then
        assertEquals(expected, actual)
    }
}