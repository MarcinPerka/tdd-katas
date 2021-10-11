package com.tdd.greeter

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito.*
import org.slf4j.Logger
import java.time.*
import kotlin.test.assertEquals

class GreeterTest {

    @ParameterizedTest
    @CsvSource(value = ["Marcin,Hello Marcin", "Adam, Hello Adam", "Leszek, Hello Leszek"])
    fun `should say Hello for the provided names`(name: String, expected: String) {
        //given
        val testedTime = LocalTime.of(15, 0)
        val clock = getClockForGivenTime(testedTime)
        val tested = Greeter(clock)

        //when
        val actual = tested.greet(name)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(value = [" Michał,Hello Michał", "Konrad , Hello Konrad", " Tomek , Hello Tomek"])
    fun `should say Hello for the provided trimmed names`(name: String, expected: String) {
        //given
        val testedTime = LocalTime.of(15, 0)
        val clock = getClockForGivenTime(testedTime)
        val tested = Greeter(clock)

        //when
        val actual = tested.greet(name)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(value = [" jacek,Hello Jacek", "katarzyna , Hello Katarzyna", " tomek , Hello Tomek"])
    fun `should say Hello for the provided capitalized names`(name: String, expected: String) {
        //given
        val testedTime = LocalTime.of(15, 0)
        val clock = getClockForGivenTime(testedTime)
        val tested = Greeter(clock)

        //when
        val actual = tested.greet(name)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        value = [" jacek,6,0,Good morning Jacek", "katarzyna ,11,59, Good morning Katarzyna", " tomek ,11,59, Good morning Tomek"]
    )
    fun `should say Good morning for the provided names when the time is in range 06-12`(
        name: String,
        hour: Int,
        minute: Int,
        expected: String
    ) {
        //given
        val testedTime = LocalTime.of(hour, minute)
        val clock = getClockForGivenTime(testedTime)
        val tested = Greeter(clock)

        //when
        val actual = tested.greet(name)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        value = [" jacek,18,0,Good evening Jacek", "katarzyna ,20,30, Good evening Katarzyna", " tomek ,21,59, Good evening Tomek"]
    )
    fun `should say Good evening for the provided names when the time is in range 18-22`(
        name: String,
        hour: Int,
        minute: Int,
        expected: String
    ) {
        //given
        val testedTime = LocalTime.of(hour, minute)
        val clock = getClockForGivenTime(testedTime)
        val tested = Greeter(clock)

        //when
        val actual = tested.greet(name)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        value = [" jacek,22,0,Good night Jacek", "katarzyna ,0,0, Good night Katarzyna", " tomek ,5,59, Good night Tomek"]
    )
    fun `should say Good night for the provided names when the time is in range 22-06`(
        name: String,
        hour: Int,
        minute: Int,
        expected: String
    ) {
        //given
        val testedTime = LocalTime.of(hour, minute)
        val clock = getClockForGivenTime(testedTime)
        val tested = Greeter(clock)

        //when
        val actual = tested.greet(name)

        //then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        value = [" jacek,22,0,Good night Jacek", "katarzyna ,0,0, Good night Katarzyna", " tomek ,5,59, Good night Tomek"]
    )
    fun `should greet the provided names and run logger one time`(
        name: String,
        hour: Int,
        minute: Int,
        expected: String
    ) {
        //given
        val logger = mock(Logger::class.java)
        val testedTime = LocalTime.of(hour, minute)
        val clock = getClockForGivenTime(testedTime)
        val tested = Greeter(clock, logger)

        //when
        doNothing().`when`(logger).info(anyString())
        val actual = tested.greet(name)

        //then
        assertEquals(expected, actual)
        verify(logger, times(1)).info(anyString())
    }

    private fun getClockForGivenTime(localTime: LocalTime) = Clock.fixed(
        LocalDateTime.of(LocalDate.now(), localTime).toInstant(OffsetDateTime.now().offset),
        ZoneId.systemDefault()
    )
}