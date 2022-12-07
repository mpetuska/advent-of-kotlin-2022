package day07

import Day
import Lines

class Day7 : Day() {
  sealed interface FS {
    val name: String
    val size: Int

    data class Dir(override val name: String, override val size: Int, val children: List<FS>) : FS {
      fun flatten(): List<Dir> =
        listOf(this) + children
          .mapNotNull { if (it is Dir) it.flatten() else null }
          .flatten()
    }

    data class File(override val name: String, override val size: Int) : FS
  }

  private fun listDirectory(name: String, input: Lines): FS.Dir {
    fun ls(name: String, lines: Lines): Pair<FS.Dir, Int> {
      val children = mutableListOf<FS>()
      var i = 0
      var size = 0
      do {
        val line = lines[i++].split(" ")
        when {
          line[0][0] == '$' -> when (line[1]) {
            "cd" -> when (line[2]) {
              "..", "/" -> break
              else -> ls(line[2], lines.drop(i)).let { (dir, ii) ->
                children += dir
                size += dir.size
                i += ii
              }
            }
            "ls" -> continue
          }
          line[0][0].isDigit() -> line[0].toInt().let { s ->
            size += s
            children += FS.File(line[1], s)
          }
          else -> continue
        }
      } while (i < lines.size)
      return FS.Dir(name, size, children) to i
    }
    return ls(name, input).first
  }

  override fun part1(input: Lines): Any {
    return listDirectory("/", input.drop(1))
      .flatten()
      .filter { it.size <= 100_000 }
      .sumOf(FS::size)
  }

  override fun part2(input: Lines): Any {
    val dirs = listDirectory("/", input.drop(1))
      .flatten()
    val missingSpace = 30_000_000 - (70_000_000 - dirs[0].size)
    return dirs.reduce { acc, dir ->
      if (dir.size >= missingSpace && dir.size < acc.size) {
        dir
      } else {
        acc
      }
    }.size
  }
}
