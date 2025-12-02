fun main() {
    val ranges = readInput("input.txt")[0]
        .split(",")
        .map { it -> it.split("-").map{it.toLong()}  }
        .map { (start, end) -> start..end }

    val p1 = ranges
        .flatMap { it }
        .filter { Regex("""(\d+)\1""").matches(it.toString()) }
        .sum()
    val p2 = ranges
        .flatMap { it }
        .filter { Regex("""(\d+)\1+""").matches(it.toString()) }
        .sum()

    println("$p1\n$p2")
}
