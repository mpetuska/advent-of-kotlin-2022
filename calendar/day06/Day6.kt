package day06

import Day
import Lines

class Day6 : Day() {
    override fun part1(input: Lines): Any {
        return input.joinToString("")
            .windowed(size = 4, step = 1, partialWindows = true)
            .indexOfFirst { it.toSet().size == it.length } + 4
    }

    override fun part2(input: Lines): Any {
        return input.joinToString("")
            .windowed(size = 14, step = 1, partialWindows = true)
            .indexOfFirst { it.toSet().size == it.length } + 14
    }
}