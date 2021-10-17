package com.tdd.primefactors

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals


class PrimeFactorsTest {

    private val tested = PrimeFactors()

    companion object {
        @JvmStatic
        fun provideTestData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, emptyList<Int>()),
                Arguments.of(2, listOf(2)),
                Arguments.of(3, listOf(3)),
                Arguments.of(4, listOf(2, 2)),
                Arguments.of(5, listOf(5)),
                Arguments.of(6, listOf(2, 3)),
                Arguments.of(7, listOf(7)),
                Arguments.of(8, listOf(2, 2, 2)),
                Arguments.of(9, listOf(3, 3)),
                Arguments.of(4620, listOf(2, 2, 3, 5, 7, 11))
            )
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun `should generate prime factors for a given integer`(input: Int, expected: List<Int>) {
        //given

        //when
        val actual = tested.generate(input)

        //then
        assertEquals(expected, actual)
    }
}