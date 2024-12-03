fun main() {
    fun parse(input: List<String>): String {
        return input.joinToString("")
    }

    fun part1(input: List<String>): Int {
        val sequence = parse(input)

        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

        return regex.findAll(sequence).sumOf { match ->
            val (a, b) = match.destructured

            a.toInt() * b.toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val sequence = parse(input)

        val regex = """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex()

        var enabled = true

        return regex.findAll(sequence).sumOf { match ->
            when (match.value) {
                "do()" -> {
                    enabled = true
                    0
                }
                "don't()" -> {
                    enabled = false
                    0
                }
                else -> {
                    if (enabled) {
                        val (a, b) = match.destructured
                        a.toInt() * b.toInt()
                    } else {
                        0
                    }
                }
            }
        }
    }

    val testInput = readInput("Day03_test1")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("Day03")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}
