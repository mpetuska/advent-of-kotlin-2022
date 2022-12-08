package day08

import Day
import Lines

class Day8 : Day() {
  override fun part1(input: Lines): Any {
    val grid = input.map { it.split("").filter(String::isNotBlank).map(String::toInt) }
    val height = grid.size
    val width = grid[0].size
    return (1 until height - 1).sumOf { y ->
      (1 until width - 1).count { x ->
        val target = grid[y][x]
        (0 until y).all { yy -> grid[yy][x] < target }
          || (y + 1 until height).all { yy -> grid[yy][x] < target }
          || (0 until x).all { xx -> grid[y][xx] < target }
          || (x + 1 until width).all { xx -> grid[y][xx] < target }
      }
    } + width * 2 + height * 2 - 4
  }

  override fun part2(input: Lines): Any {
    val grid = input.map { it.split("").filter(String::isNotBlank).map(String::toInt) }
    val height = grid.size
    val width = grid[0].size
    return (1 until height - 1).maxOf { y ->
      (1 until width - 1).maxOf { x ->
        val target = grid[y][x]
        val up = (y - 1 downTo 0).indexOfFirst { grid[it][x] >= target }
          .takeUnless { it < 0 }?.inc() ?: y
        val left = (x - 1 downTo 0).indexOfFirst { grid[y][it] >= target }
          .takeUnless { it < 0 }?.inc() ?: x
        val down = (y + 1 until height).indexOfFirst { grid[it][x] >= target }
          .takeUnless { it < 0 }?.inc() ?: (height - y - 1)
        val right = (x + 1 until width).indexOfFirst { grid[y][it] >= target }
          .takeUnless { it < 0 }?.inc() ?: (width - x - 1)
        up * down * left * right
      }
    }
  }
}