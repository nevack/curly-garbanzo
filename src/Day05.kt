fun main() {
    fun parse(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val rules = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<List<Int>>()

        input.forEach { line ->
            if ("|" in line) {
                rules += line.substringBefore("|").toInt() to line.substringAfterLast("|").toInt()
            }
            if ("," in line) {
                updates += line.split(",").map { it.toInt() }
            }
        }

        return rules to updates
    }

    fun part1(input: List<String>): Int {
        val (rulesRaw, updates) = parse(input)

        val rules = hashMapOf<Int, MutableSet<Int>>()

        rulesRaw.forEach { (before, after) ->
            rules.getOrPut(before) { hashSetOf() }.add(after)
        }

        return updates.sumOf { update ->
            var sorted = true
            for (i in 0 until update.size) {
                for (j in i + 1 until update.size) {
                    val rule = rules[update[j]] ?: continue
                    if (update[i] in rule) sorted = false
                }
            }

            if (sorted) update[update.size / 2] else 0
        }
    }

    fun part2(input: List<String>): Int {
        val (rulesRaw, updates) = parse(input)

        val rules = hashMapOf<Int, MutableSet<Int>>()

        rulesRaw.forEach { (before, after) ->
            rules.getOrPut(before) { hashSetOf() }.add(after)
        }

        return updates.sumOf { update ->
            var isSorted = true
            for (i in 0 until update.size) {
                for (j in i + 1 until update.size) {
                    val rule = rules[update[j]] ?: continue
                    if (update[i] in rule) isSorted = false
                }
            }

            if (isSorted) 0 else {
                val sorted = update.sortedWith { a, b ->
                    val rule = rules[b] ?: return@sortedWith -1
                    if (a !in rule) -1 else 1
                }

                sorted[sorted.size / 2]
            }
        }
    }

    val testInput = readInput("Day05_test1")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("Day05")
    timed("Part1 answer") { part1(input) }
    timed("Part2 answer") { part2(input) }
}
