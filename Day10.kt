fun main() {
    val input = readInput("input.txt")
    var p1 = 0L
    input.forEach { line ->
        val target = line.substring(1, line.indexOf(']')).mapIndexed { i, ch ->
            if (ch == '#') 1 shl i else 0
        }.sum()
        val buttons = line.substring(line.indexOf(']') + 2).substringBefore(" {").split(" ").map {
            it.substring(1, it.lastIndex).split(",").map { it.toInt() }.sumOf { 1 shl it }
        }

        fun dfs(idx: Int, mask: Int): Int {
            if (idx >= buttons.size) {
                return if (mask == target) 0 else 1e9.toInt()
            }
            return minOf(dfs(idx + 1, mask), dfs(idx + 1, mask xor buttons[idx]) + 1)
        }
        p1 += dfs(0, 0)
    }
    println(p1)

    // Part 2 with z3
    // maybe later :)
}
