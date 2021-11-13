package com.tdd.fizzbuzz

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class FizzBuzzTest {

    private val tested = FizzBuzz()

    companion object {
        private const val FIZZ_BUZZ = "${FizzBuzz.FIZZ}${FizzBuzz.BUZZ}"
        private const val FOO_BOO = "${FizzBuzz.FOO}${FizzBuzz.BOO}"
        private const val FIZZ_FOO = "${FizzBuzz.FIZZ}${FizzBuzz.FOO}"
        private const val FIZZ_BOO = "${FizzBuzz.FIZZ}${FizzBuzz.BOO}"
        private const val BUZZ_FOO = "${FizzBuzz.BUZZ}${FizzBuzz.FOO}"
        private const val BUZZ_BOO = "${FizzBuzz.BUZZ}${FizzBuzz.BOO}"
        private const val XD = "XD"

        @JvmStatic
        fun provideTestDataForSequence(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    1..20,
                    listOf(
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        FizzBuzz.SMALL,
                        "16",
                        "17",
                        FizzBuzz.FIZZ,
                        "19",
                        FizzBuzz.BUZZ
                    )
                ),
                Arguments.of(
                    15..50,
                    listOf(
                        FizzBuzz.SMALL,
                        "16",
                        "17",
                        FizzBuzz.FIZZ,
                        "19",
                        FizzBuzz.BUZZ,
                        FIZZ_FOO,
                        FizzBuzz.BOO,
                        "23",
                        FizzBuzz.FIZZ,
                        FizzBuzz.BUZZ,
                        "26",
                        FizzBuzz.FIZZ,
                        FizzBuzz.FOO,
                        "29",
                        FIZZ_BUZZ,
                        "31",
                        "32",
                        FIZZ_BOO,
                        "34",
                        BUZZ_FOO,
                        FizzBuzz.FIZZ,
                        "37",
                        "38",
                        FizzBuzz.FIZZ,
                        FizzBuzz.BUZZ,
                        "41",
                        FIZZ_FOO,
                        "43",
                        FizzBuzz.BOO,
                        FIZZ_BUZZ,
                        "46",
                        "47",
                        "Fizz",
                        FizzBuzz.FOO,
                        FizzBuzz.BUZZ
                    )
                )
            )
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [41, 43, 46, 47, 26, 37])
    fun `should return number while input not multiples three or five`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(input.toString(), actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [24, 27, 36, 39, 18])
    fun `should return Fizz while input multiples of three`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FIZZ, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [25, 40, 20, 85])
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
    @ValueSource(ints = [91, 28, 49])
    fun `should return Foo while input multiples of seven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [22, 44, 88])
    fun `should return Boo while input multiples of eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BOO, actual)
    }


    @ParameterizedTest
    @ValueSource(ints = [30, 45, 60, 75, 90])
    fun `should return FizzBuzz while input multiples of five and three`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FIZZ_BUZZ, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [77])
    fun `should return FooBoo while input multiples of seven and eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FOO_BOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [21, 42, 63, 84])
    fun `should return FizzFoo while input multiples of three and seven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FIZZ_FOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [33, 66])
    fun `should return FizzBoo while input multiples of three and eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FIZZ_BOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [35, 70])
    fun `should return BuzzFoo while input multiples of five and seven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(BUZZ_FOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [55])
    fun `should return BuzzBoo while input multiples of five and eleven`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(BUZZ_BOO, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 5, 10, 15])
    fun `should return Small while input is smaller than 16`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.SMALL, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [96, 97, 98, 99, 100])
    fun `should return Big while input is more than 95`(input: Int) {
        //given

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BIG, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [25, 40, 20, 85])
    fun `should return Fizz while input multiples of five - inverted initial rules`(input: Int) {
        //given
        val tested = FizzBuzz(modRules = mapOf(FizzBuzz.FIZZ to 5, FizzBuzz.BUZZ to 3))

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.FIZZ, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [24, 27, 36, 39, 18])
    fun `should return Buzz while input multiples of five - inverted initial rules`(input: Int) {
        //given
        val tested = FizzBuzz(modRules = mapOf(FizzBuzz.FIZZ to 5, FizzBuzz.BUZZ to 3))

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(FizzBuzz.BUZZ, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [9, 18, 81, 90])
    fun `should return XD while input multiples 9 - new initial mod rules and empty range rules`(input: Int) {
        //given
        val tested = FizzBuzz(rangeRules = emptyMap(), modRules = mapOf(XD to 9))

        //when
        val actual = tested.fizzBuzz(input)

        //then
        assertEquals(XD, actual)
    }
}