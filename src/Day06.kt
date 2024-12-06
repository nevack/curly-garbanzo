fun main() {
    fun parse(input: List<String>): Pair<Array<IntArray>, Pair<Int, Int>> {
        val (n, m) = findSize(input)
        var cur = 0 to 0
        return Array(n) { i ->
            IntArray(m) { j ->
                when (input[i][j]) {
                    '.' -> 0
                    '#' -> -1
                    '^' -> {
                        cur = i to j
                        0
                    }
                    else -> throw IllegalStateException()
                }
            }
        } to cur
    }

    fun part1(input: List<String>): Int {
        val (field, start) = parse(input)
        val n = field.size
        val m = field.first().size

        var cur = start
        var direction = Direction.UP

        do {
            field[cur.first][cur.second] = direction.ordinal + 1
            val next = cur moveTo direction
            if (next !in n to m) break
            if (field[next.first][next.second] < 0) {
                direction += 1
            }
            cur = cur moveTo direction
        } while (cur in n to m)

        return field.sumOf { row -> row.count { cell -> cell > 0 } }
    }

    fun part2(input: List<String>): Int {
        val (field, start) = parse(input)
        val n = field.size
        val m = field.first().size

        fun check(): Boolean {
            var cur = start
            var direction = Direction.UP

            while (true) {
                field[cur.first][cur.second] = direction.ordinal + 1
                var next = cur moveTo direction
                if (next !in n to m) return false
                if (field[next.first][next.second] == direction.ordinal + 1) return true
                while (field[next.first][next.second] < 0) {
                    direction += 1
                    next = cur moveTo direction
                }
                cur = next
            }
        }

        fun clean() {
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (field[i][j] > 0) field[i][j] = 0
                }
            }
        }

        var count = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                clean()
                if (field[i][j] != 0) {
                    continue
                }
                field[i][j] = -1
                if (check()) count++
                field[i][j] = 0
            }
        }

        return count
    }

    val testInput = readInput("Day06_test1")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    val input = readInput("Day06")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}