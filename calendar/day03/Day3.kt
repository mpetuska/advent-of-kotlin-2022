package day03

import Day
import Lines

class Day3 : Day() {
  override fun part1(input: Lines): Any {
    return input
      .map { it.chunked(it.length / 2) }
      .map { (l, r) -> l.first(r::contains) }
      .sumOf { if (it.isLowerCase()) it - 'a' + 1 else it - 'A' + 27 }
  }

  override fun part2(input: Lines): Any {
    return input
      .chunked(3)
      .map { (a, b, c) -> a.first { it in b && it in c } }
      .sumOf { if (it.isLowerCase()) it - 'a' + 1 else it - 'A' + 27 }
  }
}