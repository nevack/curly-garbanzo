fun main() {
    fun parse(input: List<String>): List<Long> {
        return input.single().split(" ").map { it.toLong() }
    }

    fun solve(input: List<String>, k: Int): Long {
        val init = parse(input)

        return (1..k).fold(
            init.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        ) { stones, _ ->
            val next = hashMapOf<Long, Long>()
            for ((stone, count) in stones) {
                if (stone == 0L) {
                    next[1L] = count
                } else if (stone.toString().length % 2 == 0) {
                    val raw = stone.toString()

                    val l = raw.substring(0, raw.length / 2).toLong()
                    val r = raw.substring(raw.length / 2).toLong()

                    next.compute(l) { _, v -> (v ?: 0L) + count }
                    next.compute(r) { _, v -> (v ?: 0L) + count }
                } else {
                    next.compute(stone * 2024) { _, v -> (v ?: 0L) + count }
                }
            }
            next
        }.values.sum()
    }

    fun part1(input: List<String>): Long {
        return solve(input, 25)
    }

    fun part2(input: List<String>): Long {
        return solve(input, 75)
    }

    val testInput = readInput("Day11_test1")
    test(55312, part1(testInput))
    test(65601038650482, part2(testInput))

    val input = readInput("Day11")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}
