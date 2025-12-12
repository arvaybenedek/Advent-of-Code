fun main() = println(readInput("utils/input.txt").count {
    it.split(": ").takeIf { it.size == 2 }?.let { (d, a) ->
        d.split("x").map { it.toInt() }.let { (x, y) ->
            x * y > a.split(" ").map { it.toInt() }.zip(listOf(7, 7, 7, 5, 7, 6)).sumOf { (a, b) -> a * b }
        }
    } ?: false
})
