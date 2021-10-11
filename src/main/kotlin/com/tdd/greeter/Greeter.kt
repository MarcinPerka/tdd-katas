package com.tdd.greeter

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Clock
import java.time.LocalTime

class Greeter(
    private val clock: Clock = Clock.systemDefaultZone(),
    private val logger: Logger = LoggerFactory.getLogger(Greeter::class.java)
) {

    companion object {
        private val DAWN = LocalTime.of(6, 0)
        private val MID_DAY = LocalTime.of(11, 59)
        private val EVENING = LocalTime.of(18, 0)
        private val NIGHT = LocalTime.of(21, 59)
    }

    fun greet(name: String): String {
        logger.info("greet execution for name $name")
        val trimmedAndCapitalizedName = name.trimmedAndCapitalized()
        return when {
            isMorning() -> "Good morning $trimmedAndCapitalizedName"
            isEvening() -> "Good evening $trimmedAndCapitalizedName"
            isNight() -> "Good night $trimmedAndCapitalizedName"
            else -> "Hello $trimmedAndCapitalizedName"
        }
    }

    private fun isMorning() = LocalTime.now(clock) in DAWN..MID_DAY

    private fun isEvening() = LocalTime.now(clock) in EVENING..NIGHT

    private fun isNight() = LocalTime.now(clock) > NIGHT || LocalTime.now(clock) < DAWN

    private fun String.trimmedAndCapitalized() = trim().replaceFirstChar(Char::titlecase)
}

