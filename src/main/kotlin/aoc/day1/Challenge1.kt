package aoc.day1

import aoc.util.getResourceAsText

var inputTxt = getResourceAsText("/day1/input.txt")!!

fun main() {
    part2()
}


fun part1() {
    val listInput = inputTxtToInputList()
    var dial = 50
    var zeroCount = 0
    val dialSize = 99

    for (n in listInput) {
        dial += n
        dial %= dialSize + 1
        if (dial == 0) {
            zeroCount++
        }
    }
    println(zeroCount)
}

fun part2() {
    val listInput = inputTxtToInputList()
    var dial = 50
    var zeroCount = 0
    val dialSize = 99
    val modSize = 99 + 1
    var wasZero = false

    for (n in listInput) {
        dial += n
        if (dial > dialSize) {
            zeroCount += dial / modSize
        } else if (dial <= 0) {
            zeroCount += ((-dial) + modSize) / modSize
        }

        dial %= modSize
        if (dial < 0) {
            dial += modSize
        }
        // UGLY HACK to prevent double counting when zero and going LEFT
        if (wasZero && n < 0) {
            zeroCount -= 1
        }
        wasZero = dial == 0
    }
    println(zeroCount)
}

fun inputTxtToInputList(): List<Int> {
    return inputTxt.split("\n")
        .map(String::trim)
        .filter { it.isNotEmpty() }
        .map { line ->
            val forward = line.startsWith("R")
            val turns = line.substring(1).toInt()
            if (forward)
                turns
            else
                -turns
        }
}