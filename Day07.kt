fun main() {
    val grid = readInput("input.txt")
    val h = grid.size
    val w = grid.first().length

    var p1 = 0L
    val dp = Array(h) { LongArray(w) }

    grid.forEachIndexed { y, row ->
        row.forEachIndexed { x, cell ->
            when (cell) {
                'S' -> dp[y][x] = 1L

                '.' -> if (y > 0) {
                    dp[y][x] += dp[y - 1][x]
                }

                '^' -> {
                    val above = dp.getOrNull(y - 1)?.getOrNull(x) ?: 0L
                    if (above != 0L) {
                        p1++

                        if (x > 0 && grid[y][x - 1] != '^') {
                            dp[y][x - 1] += above
                        }

                        if (x < w - 1 && grid[y][x + 1] != '^') {
                            dp[y][x + 1] += above
                        }
                    }
                }
            }
        }
    }

    println(p1)
    println(dp.last().sum())
}
