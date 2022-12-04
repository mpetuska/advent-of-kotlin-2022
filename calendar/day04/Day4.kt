package day04

import Day
import Lines
import kotlin.math.max
import kotlin.math.min

class Day4 : Day() {
    override fun part1(input: Lines): Any {
        return input.map { it.split(",") }
            .map { (a, b) -> a.split("-") to b.split("-") }
            .map { (a, b) -> a.let { (s, f) -> s.toInt()..f.toInt() } to b.let { (s, f) -> s.toInt()..f.toInt() } }
            .count { (a, b) -> (a.first <= b.first && a.last >= b.last) || (b.first <= a.first && b.last >= a.last) }
    }

    override fun part2(input: Lines): Any {
        return input.map { it.split(",") }
            .map { (a, b) -> a.split("-") to b.split("-") }
            .map { (a, b) -> a.let { (s, f) -> s.toInt()..f.toInt() } to b.let { (s, f) -> s.toInt()..f.toInt() } }
            .count { (a, b) -> max(a.first, b.first) <= min(a.last, b.last) }
    }
}