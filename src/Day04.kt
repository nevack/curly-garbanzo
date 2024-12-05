fun main() {
    fun part1(input: List<String>): Int {
        val (n, m) = findSize(input)
        val xmas = "XMAS"
        var count = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                count += AROUND.count { (di, dj) ->
                    xmas.indices.all { k ->
                        val i1 = i + di * k
                        val j1 = j + dj * k
                        i1 to j1 in n to m && input[i1][j1] == xmas[k]
                    }
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val (n, m) = findSize(input)
        val ms = setOf('M', 'S')
        var count = 0

        for (i in 1 until n - 1) {
            for (j in 1 until m - 1) {
                if (input[i][j] != 'A') continue

                val tl = input[i - 1][j - 1]
                val br = input[i + 1][j + 1]
                val bl = input[i + 1][j - 1]
                val ur = input[i - 1][j + 1]
                if (setOf(tl, br) != ms) continue
                if (setOf(bl, ur) != ms) continue

                count++
            }
        }

        return count
    }

    val testInput = readInput("Day04_test1")
    test(part1(testInput), 18)
    test(part2(testInput), 3)

    val input = readInput("Day04")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}
