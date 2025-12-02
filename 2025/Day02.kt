fun main() {
    val ranges = readInput("input.txt")[0]
        .split(",")
        .map { it.split("-").map { num -> num.toLong() } }
        .map { (start, end) -> start..end }

    val r1 = Regex("""(\d+)\1""")
    val r2 = Regex("""(\d+)\1+""")

    val p1 = ranges
        .flatMap { it }
        .filter { r1.matches(it.toString()) }
        .sum()
    val p2 = ranges
        .flatMap { it }
        .filter { r2.matches(it.toString()) }
        .sum()

    println("$p1\n$p2")
}
