fun main() {
    fun parse(input: List<String>): Map<Char, List<Pair<Int, Int>>> {
        val antennas = hashMapOf<Char, MutableList<Pair<Int, Int>>>()

        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] != '.') {
                    antennas.getOrPut(input[i][j]) { arrayListOf() } += i to j
                }
            }
        }

        return antennas
    }

    fun part1(input: List<String>): Int {
        val antennas = parse(input)
        val (n, m) = findSize(input)

        val antinodes = hashSetOf<Pair<Int, Int>>()

        for ((_, coords) in antennas) {
            for (i in 0 until coords.size) {
                for (j in i + 1 until coords.size) {
                    val (i1, j1) = coords[i]
                    val (i2, j2) = coords[j]

                    val antinode1 = 2 * i1 - i2 to 2 * j1 - j2
                    val antinode2 = 2 * i2 - i1 to 2 * j2 - j1

                    antinodes += antinode1
                    antinodes += antinode2
                }
            }
        }

        return antinodes.count { it in n to m }
    }

    fun part2(input: List<String>): Int {
        val antennas = parse(input)
        val (n, m) = findSize(input)

        val antinodes = hashSetOf<Pair<Int, Int>>()

        for ((_, coords) in antennas) {
            for (i in 0 until coords.size) {
                for (j in i + 1 until coords.size) {
                    val (i1, j1) = coords[i]
                    val (i2, j2) = coords[j]

                    var k = 1
                    while (true) {
                        val antinode1 = i2 + (i2 - i1) * k to j2 + (j2 - j1) * k
                        val antinode2 = i1 - (i2 - i1) * k to j1 - (j2 - j1) * k
                        if (antinode1 !in n to m && antinode2 !in n to m) {
                            break
                        }

                        antinodes += antinode1
                        antinodes += antinode2
                        k++
                    }
                }
            }
        }

        antinodes += antennas.values.flatten()

        return antinodes.count { it in n to m }
    }

    val testInput = readInput("Day08_test1")
    test(14, part1(testInput))
    test(34, part2(testInput))

    val input = readInput("Day08")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}
