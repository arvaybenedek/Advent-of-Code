fun main() {
    val input = readInput("utils/input.txt")
    val sep = input.indexOfFirst { it.isBlank() }
    val ranges = input.take(sep).map { line ->
        val (st, en) = line.split("-").map { it.toLong() }
        st..en
    }
    val ids = input.drop(sep + 1).map { it.toLong() }
    val p1 = ids.count { id -> ranges.any { id in it } }
    val p2 = merge(ranges).sumOf { it.last - it.first + 1 }
    println("$p1\n$p2")
}
