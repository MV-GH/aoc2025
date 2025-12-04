package aoc.util

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()


fun String.basicCleaning(): List<String> =
    this.split("\n")
        .map(String::trim)
        .filter { it.isNotEmpty() }

