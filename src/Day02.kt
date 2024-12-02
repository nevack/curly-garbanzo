import kotlin.math.abs

fun main() {
    fun parse(input: List<String>): List<List<Int>> {
        return input.map { line -> line.split(" ").map { x -> x.toInt() } }
    }

    fun checkReport(report: List<Int>): Boolean {
        val diffs = report.windowed(size = 2, step = 1).map { (a, b) -> b - a }

        return (diffs.all { it > 0 } || diffs.all { it < 0 }) && (diffs.all { abs(it) <= 3 })
    }

    fun skipElement(l: List<Int>, pos: Int): List<Int> {
        return l.subList(0, pos) + l.subList(pos + 1, l.size)
    }

    fun part1(input: List<String>): Int {
        val reports = parse(input)

        return reports.count { report -> checkReport(report) }
    }

    fun part2(input: List<String>): Int {
        val reports = parse(input)

        return reports.count { report ->
            checkReport(report) || report.indices.any { checkReport(skipElement(report, it))}
        }
    }

    val testInput = readInput("Day02_test1")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}
