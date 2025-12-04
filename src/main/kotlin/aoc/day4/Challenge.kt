package aoc.day4

import aoc.util.Point
import aoc.util.basicCleaning
import aoc.util.getResourceAsText

val inputTxt = getResourceAsText("/day4/input.txt")!!

const val EMPTY = '.'
const val PAPER = '@'


fun main() {
    println(part2())
}


fun part1(): Int {
    val board = inputToInputList()
    var count = 0
    for (y in board.indices) {
        for (x in board[y].indices) {
            if (board[y][x] == PAPER && canPaperBeAccessed(board, Point(x, y))) {
                count++
            }
        }
    }
    return count
}

fun part2(): Int {
    val board = inputToInputList()
    var paperWasRemoved: Boolean

    do {
        paperWasRemoved = false

        for (y in board.indices) {
            for (x in board[y].indices) {
                val position = Point(x, y)
                if (isPaper(board, position) && canPaperBeAccessed(board, position)) {
                    REMOVED_PAPERS.add(position)
                    paperWasRemoved = true
                }
            }
        }
    } while (paperWasRemoved)

    return REMOVED_PAPERS.size
}


val REMOVED_PAPERS = mutableSetOf<Point>()

fun isPaper(board: List<String>, position: Point) = board[position.y][position.x] == PAPER && position !in REMOVED_PAPERS


fun canPaperBeAccessed(board: List<String>, position: Point): Boolean {
    var paperNeighborCount = 0

    for (dir in Point.directionsEight) {
        val targetPos = position + dir
        if (withinBoard(board, targetPos) && isPaper(board, targetPos)) {
            paperNeighborCount++
        }
    }
    return paperNeighborCount < 4
}

fun inputToInputList(): List<String> = inputTxt.basicCleaning()


fun withinBoard(board: List<String>, position: Point) = position.y in 0 until board.size && position.x in 0 until board.first().length