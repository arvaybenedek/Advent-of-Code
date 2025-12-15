fun main() {
    val input = readInput("input.txt")
    val sep = Regex("\\s+")
    val nums = input.take(4).map { line ->
        line.split(sep).filterNot { it.isBlank() }.map { it.toLong() }
    }
    val ops = input[4].split(sep)
    val p1 = nums.first().indices.sumOf { i ->
        val v = nums.map { it[i] }
        when (ops[i]) {
            "+" -> v.sum()
            "*" -> v.reduce { acc, v -> acc * v }
            else -> 0L
        }
    }
    val p2 = buildList {
        var op = '+'
        val colNums = mutableListOf<Long>()
        for (i in input.first().indices) {
            val cur = buildString {
                for (row in 0..3) {
                    val char = input[row][i]
                    if (char.isDigit()) append(char)
                }
            }
            when (input[4][i]) {
                '+', '*' -> op = input[4][i]
            }
            if (cur.isNotEmpty()) {
                colNums.add(cur.toLong())
            } else if (colNums.isNotEmpty()) {
                val res = when (op) {
                    '+' -> colNums.sum()
                    '*' -> colNums.reduce { acc, v -> acc * v }
                    else -> 0L
                }
                add(res)
                colNums.clear()
            }
        }
        if (colNums.isNotEmpty()) {
            val res = when (op) {
                '+' -> colNums.sum()
                '*' -> colNums.reduce { acc, v -> acc * v }
                else -> 0L
            }
            add(res)
        }
    }.sum()
    println("$p1\n$p2")
}
