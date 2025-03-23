package indie.programmers.bfsdfs

fun main() {
    val begin = "hit"
    val target = "cog"
    val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")
    println(solution(begin, target, words))
}

fun solution(begin: String, target: String, words: Array<String>): Int {
    if (target !in words) return 0

    val visited = mutableSetOf<String>()
    val queue = ArrayDeque<Pair<String, Int>>()
    queue.addLast(Pair(begin, 0))

    while (!queue.isEmpty()) {
        val (current, count) = queue.removeFirst()
        if (current == target) return count
        for(word in words) {
            if (word !in visited && isOneLetterDifferent(current, word)) {
                visited.add(word)
                queue.add(Pair(word, count + 1))
            }
        }
    }
    return 0
}

fun isOneLetterDifferent(a: String, b: String): Boolean {
    var diffCount = 0
    for (i in a.indices) {
        if (a[i] != b[i]) diffCount++
        if (diffCount > 1) return false
    }
    return diffCount == 1
}
