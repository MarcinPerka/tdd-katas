package com.tdd.bowlinggame

class BowlingGame {

    private val rolls = IntArray(21)
    private var roll = 0

    companion object {
        private const val MAX_POINTS = 10
    }

    fun roll(pins: Int) {
        validatePins(pins)
        rolls[roll++] = pins
    }

    fun getScore(): Int {
        var frameIndex = 0
        return (0..9).sumOf {
            val frameScore: Int
            when {
                isStrike(frameIndex) -> {
                    frameScore = obtainStrikePoints(frameIndex)
                    frameIndex++
                }
                isSpare(frameIndex) -> {
                    frameScore = obtainSparePoints(frameIndex)
                    frameIndex += 2
                }
                else -> {
                    frameScore = obtainNormalFrameScore(frameIndex)
                    frameIndex += 2
                }
            }
            frameScore
        }
    }

    private fun validatePins(pins: Int) {
        if (pins < 0 || pins > MAX_POINTS) throw RollException("Invalid pins number: $pins")
    }

    private fun isStrike(frameIdx: Int) = rolls[frameIdx] == MAX_POINTS

    private fun obtainStrikePoints(frameIndex: Int) = MAX_POINTS + strikeBonus(frameIndex)

    private fun strikeBonus(frameIdx: Int) = rolls[frameIdx + 1] + rolls[frameIdx + 2]

    private fun isSpare(frameIdx: Int) = rolls[frameIdx] + rolls[frameIdx + 1] == MAX_POINTS

    private fun obtainSparePoints(frameIndex: Int) = MAX_POINTS + spareBonus(frameIndex)

    private fun spareBonus(frameIdx: Int) = rolls[frameIdx + 2]

    private fun obtainNormalFrameScore(frameIdx: Int) = rolls[frameIdx] + rolls[frameIdx + 1]
}