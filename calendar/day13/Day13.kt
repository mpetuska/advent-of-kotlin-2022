package day13

import Day
import Lines
import day13.Day13.Data.Value
import day13.Day13.Data.Values

@ExperimentalStdlibApi
class Day13 : Day() {
  sealed interface Data : Comparable<Data> {
    @JvmInline
    value class Value(val value: Int) : Data {
      override fun toString(): String = value.toString()

      override fun compareTo(other: Data): Int = when (other) {
        is Value -> value.compareTo(other.value)
        is Values -> Values(listOf(this)).compareTo(other)
      }
    }

    @JvmInline
    value class Values(private val values: List<Data>) : Data, List<Data> by values {
      constructor(value: Value) : this(listOf(value))

      override fun toString(): String = values.toString()

      override fun compareTo(other: Data): Int = when (other) {
        is Value -> compareTo(Values(other))
        is Values -> zip(other).asSequence()
          .map { (a, b) -> a.compareTo(b) }
          .firstOrNull { it != 0 } ?: size.compareTo(other.size)
      }
    }
  }


  private fun parse(line: String): Values = parse(line, 1).first
  private fun parse(line: String, start: Int): Pair<Values, Int> {
    var i = start
    return buildList {
      var int = ""
      while (i < line.length) {
        when (val char = line[i++]) {
          '[' -> parse(line, i).also { (data, ii) ->
            add(data)
            i = ii
          }
          ']' -> {
            int.toIntOrNull()?.let(::Value)?.also(::add)
            break
          }
          ',' -> int.toIntOrNull()?.let(::Value)?.also(::add)?.also { int = "" }
          else -> int += char
        }
      }
    }.let(::Values) to i
  }

  override fun part1(input: Lines): Any {
    return input.asSequence()
      .chunked(3)
      .map { it.take(2) }
      .map { it.map(::parse) }
      .withIndex()
      .filter { (_, pair) -> pair[0].compareTo(pair[1]) < 0 }
      .map(IndexedValue<*>::index)
      .map(Int::inc)
      .sum()
  }

  override fun part2(input: Lines): Any {
    val dividers = listOf("[[2]]", "[[6]]")
    val dividerPackets = dividers.map(::parse)
    return (dividers + "\n" + input).asSequence()
      .chunked(3)
      .map { it.take(2) }
      .flatMap { it.map(::parse) }
      .sorted()
      .withIndex()
      .filter { (_, packet) -> packet in dividerPackets }
      .map(IndexedValue<*>::index)
      .map(Int::inc)
      .reduce { acc, i -> acc * i }
  }
}