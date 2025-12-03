package aoc.day3

import aoc.util.getResourceAsText

var inputTxt = getResourceAsText("/day3/input.txt")!!


fun main() {
    println(part2())
}

fun part1(): Int {
    return inputToInputList().sumOf(::getJoltage)
}

fun part2(): Long {
    return inputToInputList().sumOf(::getJoltage12)
}

fun getJoltage12(bank: String): Long {
    val targetLength = 12
    var numberSequence = ""

    val searchArea = bank.take(bank.length - targetLength + 1)

    val largestInfo = findLargestDigit(searchArea)
    numberSequence += largestInfo.largestDigit
    var searchPosition = largestInfo.pos
    var gaps = bank.length - (searchPosition + 1) - (targetLength - numberSequence.length)

    while (gaps > 0 && numberSequence.length != targetLength) {
        val searchArea2 = bank.substring(searchPosition + 1, searchPosition + gaps + 1 + 1)
        val info2 = findLargestDigit(searchArea2)
        numberSequence += info2.largestDigit
        searchPosition += 1 + info2.pos
        gaps = bank.length - (searchPosition + 1) - (targetLength - numberSequence.length)
    }

    if (numberSequence.length != targetLength) {
        numberSequence += bank.substring(searchPosition + 1, bank.length)
    }

    return numberSequence.toLong()
}

fun getJoltage(bank: String): Int {
    val largestDigitInfo = findLargestDigit(bank)
    if (!largestDigitInfo.alone) {
        return largestDigitInfo.largestDigit * 10 + largestDigitInfo.largestDigit
    }

    // Find biggest before
    if ((largestDigitInfo.pos + 1) == bank.length) {
        val secondLargestInfo = findLargestDigit(bank.take(largestDigitInfo.pos))
        return secondLargestInfo.largestDigit * 10 + largestDigitInfo.largestDigit
    }
    // Find biggest after
    val secondLargestInfo = findLargestDigit(bank.substring(largestDigitInfo.pos + 1, bank.length))
    return largestDigitInfo.largestDigit * 10 + secondLargestInfo.largestDigit
}

fun findLargestDigit(bank: String): Info {
    var largestDigit = 0
    var multipleLargestDigit = false
    var posLargestDigit = 0

    for (pos in bank.indices) {
        val i = bank[pos].toString().toInt()
        if (i > largestDigit) {
            largestDigit = i
            multipleLargestDigit = false
            posLargestDigit = pos
        } else if (i == largestDigit) {
            multipleLargestDigit = true
        }
    }
    return Info(largestDigit, !multipleLargestDigit, posLargestDigit)
}


fun inputToInputList(): List<String> {
    return inputTxt.split("\n")
        .map(String::trim)
        .filter { it.isNotEmpty() }
}


data class Info(val largestDigit: Int, val alone: Boolean, val pos: Int)