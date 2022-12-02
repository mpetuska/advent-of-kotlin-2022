package day02

import Day
import Lines

class Day2 : Day() {
    override fun part1(input: Lines): Any {
        val rules = mapOf(
            "A" to mapOf(
                "X" to 4,
                "Y" to 8,
                "Z" to 3,
            ),
            "B" to mapOf(
                "X" to 1,
                "Y" to 5,
                "Z" to 9,
            ),
            "C" to mapOf(
                "X" to 7,
                "Y" to 2,
                "Z" to 6,
            ),
        )
        return input.map { it.split(" ") }.sumOf { (a, x) -> rules[a]!![x]!! }
    }

    override fun part2(input: Lines): Any {
        val rules = mapOf(
            "A" to mapOf(
                "A" to 4,
                "B" to 8,
                "C" to 3,
            ),
            "B" to mapOf(
                "A" to 1,
                "B" to 5,
                "C" to 9,
            ),
            "C" to mapOf(
                "A" to 7,
                "B" to 2,
                "C" to 6,
            ),
        )
        val dx = mapOf(
            "X" to -1,
            "Y" to 0,
            "Z" to 1,
        )
        val options = listOf("A", "B", "C")
        return input.map { it.split(" ") }.sumOf { (a, x) ->
            val i = options.indexOf(a) + dx[x]!!
            val n = options.size
            val b = options[(i % n + n) % n]
            rules[a]!![b]!!
        }
    }
}