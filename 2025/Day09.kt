import kotlin.math.abs

fun main() {
    val input = readInput("input.txt")
    val points = input.map { line ->
        val (x, y) = line.split(",").map { it.toLong() }
        x to y
    }
    var part1 = 0L
    for (i in points.indices) {
        for (j in i + 1..<points.size) {
            val (x1, y1) = points[i]
            val (x2, y2) = points[j]
            val w = abs(x1 - x2) + 1L
            val h = abs(y1 - y2) + 1L
            part1 = maxOf(part1, w * h)
        }
    }
    println(part1)
    val perimeter = mutableSetOf<Pair<Long, Long>>()
    for (i in points.indices) {
        val (x1, y1) = points[i]
        val (x2, y2) = points[(i + 1) % points.size]
        if (x1 == x2) {
            val minY = minOf(y1, y2)
            val maxY = maxOf(y1, y2)
            for (y in minY..maxY) {
                perimeter.add(x1 to y)
            }
        } else {
            val minX = minOf(x1, x2)
            val maxX = maxOf(x1, x2)
            for (x in minX..maxX) {
                perimeter.add(x to y1)
            }
        }
    }
    //    println(perimeter.size) // 591234
    fun ck(p: Pair<Long, Long>, q: Pair<Long, Long>): Boolean {
        val (px, py) = p
        val (qx, qy) = q
        return if (px == qx) {
            val minY = minOf(py, qy)
            val maxY = maxOf(py, qy)
            (minY..maxY).any { y -> (px to y) in perimeter }
        } else {
            val minX = minOf(px, qx)
            val maxX = maxOf(px, qx)
            (minX..maxX).any { x -> (x to py) in perimeter }
        }
    }
    var part2 = 0L
    for (i in points.indices) {
        for (j in i + 1..<points.size) {
            val (x1, y1) = points[i]
            val (x2, y2) = points[j]
            val minX = minOf(x1, x2)
            val maxX = maxOf(x1, x2)
            val minY = minOf(y1, y2)
            val maxY = maxOf(y1, y2)
            if (ck(minX + 1 to maxY - 1, maxX - 1 to maxY - 1)) continue
            if (ck(maxX - 1 to maxY - 1, maxX - 1 to minY + 1)) continue
            if (ck(maxX - 1 to minY + 1, minX + 1 to minY + 1)) continue
            if (ck(minX + 1 to minY + 1, minX + 1 to maxY - 1)) continue
            val a = (maxX - minX + 1L) * (maxY - minY + 1L)
            part2 = maxOf(part2, a)
        }
    }
    println(part2)
}
