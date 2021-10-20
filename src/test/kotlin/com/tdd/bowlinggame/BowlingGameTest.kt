package com.tdd.bowlinggame

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class BowlingGameTest {

    @Test
    fun `should return zero when all rolls are missed`() {
        //given
        val expected = 0
        val tested = BowlingGame()

        //when
        repeat(20) { tested.roll(0) }

        //then
        assertEquals(expected, tested.getScore())
    }

    @Test
    fun `should return twenty when overthrow one pin per roll`() {
        //given
        val expected = 20
        val tested = BowlingGame()

        //when
        repeat(20) { tested.roll(1) }

        //then
        assertEquals(expected, tested.getScore())
    }

    @ParameterizedTest
    @CsvSource(value = ["-10", "11"])
    fun `should throw an exception when a negative or greater than ten number of pins has been entered`(input: Int) {
        //given
        val expected = "Invalid pins number: $input"
        val tested = BowlingGame()

        //when

        //then
        val exception = assertThrows<RollException> { tested.roll(input) }
        assertEquals(expected, exception.message)
    }

    @Test
    fun `should return sixteen when spare occurred`() {
        //given
        val expected = 16
        val tested = BowlingGame()

        //when
        tested.roll(6)
        tested.roll(4)
        tested.roll(3)
        repeat(17) { tested.roll(0) }


        //then
        assertEquals(expected, tested.getScore())
    }

    @Test
    fun `should return twenty four when strike occurred`() {
        //given
        val expected = 24
        val tested = BowlingGame()

        //when
        tested.roll(10)
        tested.roll(3)
        tested.roll(4)
        repeat(16) { tested.roll(0) }

        //then
        assertEquals(expected, tested.getScore())
    }
}