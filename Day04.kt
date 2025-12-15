fun main() {
    val input = readInput("input.txt")
    val r = input.size
    val c = input[0].length
    
    val adjCount = Array(r) { IntArray(c) }
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (input[i][j] == '@') {
                adjCount[i][j] = ADJ8.count { (dr, dc) ->
                    val nr = i + dr
                    val nc = j + dc
                    nr in 0 until r && nc in 0 until c && input[nr][nc] == '@'
                }
            }
        }
    }
    
    val p1 = (0 until r).sumOf { i ->
        (0 until c).count { j -> input[i][j] == '@' && adjCount[i][j] < 4 }
    }
    
    val isRoll = Array(r) { i -> BooleanArray(c) { j -> input[i][j] == '@' } }
    val queue = ArrayDeque<Pair<Int, Int>>().apply {
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (isRoll[i][j] && adjCount[i][j] < 4) {
                    add(i to j)
                }
            }
        }
    }
    
    var p2 = 0
    while (queue.isNotEmpty()) {
        val (i, j) = queue.removeFirst()
        if (!isRoll[i][j]) {
            continue
        }
        isRoll[i][j] = false
        p2++
        for ((dr, dc) in ADJ8) {
            val ni = i + dr
            val nj = j + dc
            if (ni !in 0 until r || nj !in 0 until c || !isRoll[ni][nj]) {
                continue
            }
            if (--adjCount[ni][nj] < 4) {
                queue.add(ni to nj)
            }
        }
    }
    
    println("$p1\n$p2")
}
