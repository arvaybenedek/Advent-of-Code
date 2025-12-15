fun main() {
    val graph = readInput("input.txt").associate { line ->
        val (from, to) = line.split(": ")
        from to to.split(" ")
    }

    val memo = mutableMapOf<Triple<String, String, Set<String>>, Long>()
    fun paths(cur: String, to: String, req: Set<String>): Long =
        memo.getOrPut(Triple(cur, to, req)) {
            if (cur == to) if (req.isEmpty()) 1L else 0L
            else graph[cur]!!.sumOf {
                paths(it, to, req - cur)
            }
        }

    println(paths("you", "out", setOf()))
    memo.clear()
    println(paths("svr", "out", setOf("dac", "fft")))
}
