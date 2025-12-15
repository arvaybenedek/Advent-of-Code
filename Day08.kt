fun main() {
    val input = readInput("input.txt")
    val points = input.map { line ->
        val (x, y, z) = line.split(",").map { it.toInt() }
        Point(x, y, z)
    }

    val n = points.size
    val pairs = sequence {
        for (i in 0 ..< n) {
            for (j in i + 1 ..< n) {
                yield(Triple(points[i].dist(points[j]), i, j))
            }
        }
    }.sortedBy { it.first }.toList()

    val uf = UnionFind(n)

    var p1 = 0L
    var last = Pair(0, 0)
    pairs.forEachIndexed { index, (_, i, j) ->
        if (uf.union(i, j)) {
            last = i to j
        }
        if (index == 999) {
            p1 = uf.getComponentSizes().take(3).fold(1L) { acc, size -> acc * size }
        }
    }
    val p2 = points[last.first].x.toLong() * points[last.second].x

    println("$p1\n$p2")
}
