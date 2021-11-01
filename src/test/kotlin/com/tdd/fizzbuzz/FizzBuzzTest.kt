package com.tdd.fizzbuzz

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class FizzBuzzTest {

    private val tested = FizzBuzz()

    companion object {
        @JvmStatic
        fun provideTestDataForSequence(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1..20, listOf("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz")),
                Arguments.of(15..50, listOf("FizzBuzz", "16", "17", "Fizz", "19", "Buzz", "Fizz", "22", "23", "Fizz", "Buzz", "26", "Fizz", "28", "29", "FizzBuzz", "31", "32", "Fizz", "34", "Buzz", "Fizz", "37", "38", "Fizz", "Buzz", "41", "Fizz", "43", "44", "FizzBuzz", "46", "47", "Fizz", "49", "Buzz"))
            )
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, 101, -100, 200, -10000])
    fun `should throw when input is less than one or greater than 100`(input: Int) {
        //given

        //when && then
        assertThrows<InvalidNumberException> { tested.fizzBuzz(input) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 4, 7, 8, 86])
    fun `should return number while input not multiples three or five`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(input.toString(), actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 9, 6, 12, 18, 99])
    fun `should return Fizz while input multiples of three`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FIZZ, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 10, 20, 70, 100, 85])
    fun `should return Buzz while input multiples of five`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BUZZ, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [15, 30, 45, 60, 75, 90])
    fun `should return FizzBuzz while input multiples of five and three`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FIZZ_BUZZ, actual)
    }


    @Test
    fun `should generate FizzBuzz sequence from 1 to 100`() {
        //given

        //when
        val actual = tested.fizzBuzzSequence()

        //then
        assertEquals(100, actual.size)
        assertEquals("1", actual.first())
        assertEquals(FizzBuzz.FIZZ, actual[2])
        assertEquals(FizzBuzz.BUZZ, actual[4])
        assertEquals(FizzBuzz.FIZZ_BUZZ, actual[14])
    }

    @ParameterizedTest
    @MethodSource("provideTestDataForSequence")
    fun `should generate FizzBuzz sequence in given range`(range: IntRange, expected: List<String>) {
        //given
        val arraySize = range.last - range.first + 1

        //when
        val actual = tested.fizzBuzzSequence(range)

        //then
        assertEquals(arraySize, actual.size)
        assertEquals(expected, actual)
    }
}