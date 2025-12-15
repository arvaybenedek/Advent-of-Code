fun main() {
    fun maxSubsequenceOfLength(line: String, k: Int): Long {
        val stack = mutableListOf<Char>()
        line.forEachIndexed { i, ch ->
            while (stack.isNotEmpty() && stack.last() < ch && stack.size + line.length - i > k) {
                stack.removeLast()
            }
            if (stack.size < k) stack.add(ch)
        }
        return stack.joinToString("").toLong()
    }

    val (p1, p2) = readInput("input.txt").fold(0L to 0L) { (sum1, sum2), line ->
        sum1 + maxSubsequenceOfLength(line, 2) to sum2 + maxSubsequenceOfLength(line, 12)
    }

    println("$p1\n$p2")
}
