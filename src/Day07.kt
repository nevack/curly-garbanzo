fun main() {
    fun parse(input: List<String>): List<Pair<Long, List<Long>>> {
        return input.map { line ->
            val (x, l) = line.split(": ")
            x.toLong() to l.split(" ").map { it.toLong() }
        }
    }

    fun check1(a: List<Long>, acc: Long, i: Int, x: Long): Boolean {
        if (i == a.size) return acc == x

        if (check1(a, acc + a[i], i + 1, x)) return true
        if (check1(a, acc * a[i], i + 1, x)) return true

        return false
    }

    fun part1(input: List<String>): Long {
        val a = parse(input)

        return a.sumOf { (x, l) ->
            if (check1(l, l[0], 1, x)) x else 0
        }
    }

    fun concat2(a: Long, b: Long): Long {
        return (a.toString() + b.toString()).toLong()
    }

    fun check2(a: List<Long>, acc: Long, i: Int, x: Long): Boolean {
        if (i == a.size) return acc == x

        if (check2(a, acc + a[i], i + 1, x)) return true
        if (check2(a, acc * a[i], i + 1, x)) return true
        if (check2(a, concat2(acc, a[i]), i + 1, x)) return true

        return false
    }

    fun part2(input: List<String>): Long {
        val a = parse(input)

        return a.sumOf { (x, l) ->
            if (check2(l, l[0], 1, x)) x else 0
        }
    }

    val testInput = readInput("Day07_test1")
    check(part1(testInput) == 3749L)
    check(part2(testInput) == 11387L)

    val input = readInput("Day07")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}
