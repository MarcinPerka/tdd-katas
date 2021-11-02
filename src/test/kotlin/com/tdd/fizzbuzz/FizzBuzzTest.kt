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
                Arguments.of(
                    1..20,
                    listOf(
                        "1",
                        "2",
                        "Fizz",
                        "4",
                        "Buzz",
                        "Fizz",
                        "Foo",
                        "8",
                        "Fizz",
                        "Buzz",
                        "Boo",
                        "Fizz",
                        "13",
                        "Foo",
                        "FizzBuzz",
                        "16",
                        "17",
                        "Fizz",
                        "19",
                        "Buzz"
                    )
                ),
                Arguments.of(
                    15..50,
                    listOf(
                        "FizzBuzz",
                        "16",
                        "17",
                        "Fizz",
                        "19",
                        "Buzz",
                        "FizzFoo",
                        "Boo",
                        "23",
                        "Fizz",
                        "Buzz",
                        "26",
                        "Fizz",
                        "Foo",
                        "29",
                        "FizzBuzz",
                        "31",
                        "32",
                        "FizzBoo",
                        "34",
                        "BuzzFoo",
                        "Fizz",
                        "37",
                        "38",
                        "Fizz",
                        "Buzz",
                        "41",
                        "FizzFoo",
                        "43",
                        "Boo",
                        "FizzBuzz",
                        "46",
                        "47",
                        "Fizz",
                        "Foo",
                        "Buzz"
                    )
                )
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
    @ValueSource(ints = [1, 2, 4, 13, 8, 86])
    fun `should return number while input not multiples three or five`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(input.toString(), actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 9, 6, 12, 18, 96])
    fun `should return Fizz while input multiples of three`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FIZZ, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 10, 20, 100, 85])
    fun `should return Buzz while input multiples of five`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BUZZ, actual)
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

    @ParameterizedTest
    @ValueSource(ints = [7, 14, 28, 49])
    fun `should return Foo while input multiples of seven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 22, 44, 88])
    fun `should return Boo while input multiples of eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BOO, actual)
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

    @ParameterizedTest
    @ValueSource(ints = [77])
    fun `should return FooBoo while input multiples of seven and eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FOO_BOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [21, 42, 63, 84])
    fun `should return FizzFoo while input multiples of three and seven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FIZZ_FOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [33, 66, 99])
    fun `should return FizzBoo while input multiples of three and eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FIZZ_BOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [35, 70])
    fun `should return BuzzFoo while input multiples of five and seven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BUZZ_FOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [55])
    fun `should return BuzzBoo while input multiples of five and eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BUZZ_BOO, actual)
    }
}