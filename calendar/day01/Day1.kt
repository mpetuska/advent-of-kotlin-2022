package day01

import Day
import Lines

class Day1 : Day() {
    override fun part1(input: Lines): Any {
        var localMax = 0
        var globalMax = 0
        for (line in input) {
            if(line.isBlank()) {
                localMax = 0
            } else {
                localMax += line.toInt()
                if(localMax > globalMax) globalMax = localMax
            }
        }
        return globalMax
    }

    override fun part2(input: Lines): Any {
        val elves = mutableListOf(0)
        input.forEach { line ->
            if(line.isBlank()) elves += 0 else elves[elves.lastIndex] += line.toInt()
        }
        return elves.sorted().takeLast(3).sum()
    }
}