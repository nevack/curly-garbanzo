
enum class Direction(val di: Int, val dj: Int) {
    RIGHT(0, 1), // 0
    DOWN(1, 0),  // 1
    LEFT(0, -1), // 2
    UP(-1, 0);   // 3

    infix fun xor(x: Int): Direction {
        require(x <= entries.size)
        return entries[ordinal xor x]
    }

    operator fun plus(rotation: Int): Direction {
        return entries[(ordinal + rotation + entries.size) % entries.size]
    }

    operator fun minus(rotation: Int): Direction {
        return entries[(ordinal - rotation + entries.size) % entries.size]
    }

    operator fun not(): Direction {
        return xor(0b10)
    }
}

infix fun Pair<Int, Int>.moveTo(direction: Direction): Pair<Int, Int> {
    return first + direction.di to second + direction.dj
}

val URDL = listOf(
    -1 to 0,
    0 to 1,
    1 to 0,
    0, -1,
)

val AROUND = listOf(
    -1 to -1,
    -1 to 0,
    -1 to 1,
    0 to 1,
    1 to 1,
    1 to 0,
    1 to -1,
    0 to -1,
)

val CORNERS = listOf(
    -1 to -1,
    -1 to 1,
    1 to 1,
    1 to -1,
)
