fun main() {
    val input = readInput("input.txt")
    var (p1, p2, pos) = listOf(0, 0, 50)
    input.forEach { line ->
        val steps = line.drop(1).toInt()
        when (line[0]) {
            'R' -> {
                pos += steps
                p2 += pos / 100
            }
            'L' -> {
                if (pos == 0) p2--
                pos -= steps
                p2 += (-pos + 100) / 100
            }
        }
        pos = pos.mod(100)
        if (pos == 0) p1++
    }
    println("$p1\n$p2")
}
