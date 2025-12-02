package aoc.day2

import aoc.util.getResourceAsText
import kotlin.math.absoluteValue
import kotlin.math.log10

var inputTxt = getResourceAsText("/day2/input.txt")!!


fun main() {
    println(part2())
}

fun part1(): Long {
    val inputList = inputTxtToInputList()

    var sumInvalidIds = 0L
    for (pair in inputList) {
        for (i in pair.first..pair.second) {
            if (i.length % 2 == 0 && i.isMirroredNumberPart1()) {
                sumInvalidIds += i
            }
        }
    }
    return sumInvalidIds
}

fun part2(): Long {
    val inputList = inputTxtToInputList()

    var sumInvalidIds = 0L
    for (pair in inputList) {
        for (i in pair.first..pair.second) {
            if (i.length > 1 && i.isMirroredNumberPart2()) {
                sumInvalidIds += i
            }
        }
    }
    return sumInvalidIds
}

fun Long.isMirroredNumberPart1(): Boolean {
    val length = this.length
    val stringNumber = this.toString()
    return stringNumber.take(length / 2) == stringNumber.substring(length / 2)
}

fun Long.isMirroredNumberPart2(): Boolean {
    val length = this.length

    if (length <= 1) return false

    val halfLength = length / 2
    val stringNumber = this.toString()

    for (i in halfLength downTo 1) {
        if (length % i == 0) {
            if (stringNumber.chunked(i).distinct().size == 1) {
                return true
            }
        }
    }
    return false
}


fun inputTxtToInputList(): List<Pair<Long, Long>> {
    return inputTxt.split(",")
        .map {
            val split = it.split("-")
            Pair(split[0].toLong(), split[1].toLong())
        }
}

val Long.length
    get() = when (this) {
        0L -> 1
        else -> log10(toFloat().absoluteValue).toInt() + 1
    }