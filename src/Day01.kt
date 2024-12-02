import kotlin.math.abs

fun main() {
    fun parse(input: List<String>): Pair<List<Int>, List<Int>> {
        return input.map { line ->
            line.substringBefore(" ").toInt() to line.substringAfterLast(" ").toInt()
        }.unzip()
    }

    fun part1(input: List<String>): Int {
        val (l, r) = parse(input)

        return l.sorted().zip(r.sorted()).sumOf { (a, b) -> abs(a - b) }
    }

    fun part2(input: List<String>): Int {
        val (l, r) = parse(input)

        val frequencies = r.groupingBy { it }.eachCount()

        return l.sumOf { a -> a * frequencies.getOrDefault(a, 0) }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
