package day05

import Day
import Lines

class Day5 : Day() {
    override fun part1(input: Lines): Any {
        val (rows, stackIds) = input.takeWhile(String::isNotBlank).let {
            it.dropLast(1)
                .map { row ->
                    row.chunked(4).map(String::trim).map { item -> item.removeSurrounding("[", "]") }
                } to it.last().split(" +".toRegex()).filter(String::isNotBlank).map(String::toInt)
        }
        val stacks = buildMap {
            stackIds.forEach { id ->
                val stack = rows.indices.reversed().mapNotNull { i ->
                    val r = rows[i]
                    r.getOrNull(id - 1)
                }.filter(String::isNotBlank).toMutableList()
                put(id, stack)
            }
        }

        input.drop(rows.size + 2)
            .takeWhile(String::isNotBlank)
            .map { it.split(" ").drop(1) }
            .map { (count, _, from, _, to) -> Triple(count.toInt(), from.toInt(), to.toInt()) }
            .forEach { (count, from, to) ->
                repeat(count) { stacks[from]!!.removeLast().let(stacks[to]!!::add) }
            }
        return stackIds.mapNotNull(stacks::get).joinToString("", transform = List<String>::last)
    }

    override fun part2(input: Lines): Any {

        val (rows, stackIds) = input.takeWhile(String::isNotBlank).let {
            it.dropLast(1)
                .map { row ->
                    row.chunked(4).map(String::trim).map { item -> item.removeSurrounding("[", "]") }
                } to it.last().split(" +".toRegex()).filter(String::isNotBlank).map(String::toInt)
        }
        val stacks = buildMap {
            stackIds.forEach { id ->
                val stack = rows.indices.reversed().mapNotNull { i ->
                    val r = rows[i]
                    r.getOrNull(id - 1)
                }.filter(String::isNotBlank).toMutableList()
                put(id, stack)
            }
        }

        input.drop(rows.size + 2)
            .takeWhile(String::isNotBlank)
            .map { it.split(" ").drop(1) }
            .map { (count, _, from, _, to) -> Triple(count.toInt(), from.toInt(), to.toInt()) }
            .forEach { (count, from, to) ->
                val target = stacks[to]!!
                val idx = target.size
                repeat(count) { stacks[from]!!.removeLast().let { target.add(idx, it) } }
            }
        return stackIds.mapNotNull(stacks::get).joinToString("", transform = List<String>::last)
    }
}