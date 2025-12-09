import kotlin.math.abs

data class Rect(val p: Pair<Long, Long>, val q: Pair<Long, Long>) {
    val minX: Long = minOf(p.first, q.first)
    val maxX: Long = maxOf(p.first, q.first)
    val minY: Long = minOf(p.second, q.second)
    val maxY: Long = maxOf(p.second, q.second)

    fun area(): Long = (abs(q.first - p.first) + 1L) * (abs(q.second - p.second) + 1L)

    fun isSegmentInside(st: Pair<Long, Long>, en: Pair<Long, Long>): Boolean {
        val (x1, y1) = st
        val (x2, y2) = en
        if (x1 == x2) {
            if (x1 !in (minX + 1)..<maxX) {
                return false
            }
            return minOf(y1, y2) < maxY && maxOf(y1, y2) > minY
        } else if (y1 == y2) {
            if (y1 !in (minY + 1)..<maxY) {
                return false
            }
            return minOf(x1, x2) < maxX && maxOf(x1, x2) > minX
        } else {
            return false
        }
    }
}

fun main() {
    val input = readInput("input.txt")
    val points = input.map { line ->
        val (x, y) = line.split(",").map { it.toLong() }
        x to y
    }
    val segments = mutableListOf<Pair<Pair<Long, Long>, Pair<Long, Long>>>()
    for (i in points.indices) {
        val (x1, y1) = points[i]
        val (x2, y2) = points[(i + 1) % points.size]
        segments.add((x1 to y1) to (x2 to y2))
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
    var part2 = 0L
    for (i in points.indices) {
        for (j in i + 1..<points.size) {
            val cur = Rect(points[i], points[j])
            if (segments.any { (st, en) -> cur.isSegmentInside(st, en) }) continue
            part2 = maxOf(part2, cur.area())
        }
    }
    println(part2)
}
