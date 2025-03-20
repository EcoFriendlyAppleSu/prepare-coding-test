package indie.programmers.sort

fun main() {
    val citations = intArrayOf(3, 0, 6, 1, 5)
    println(solutions(citations))
}

fun solutions(citations: IntArray): Int {
    citations.sortDescending()
    for (i in citations.indices) {
        if (citations[i] <= i) return i
    }
    return citations.size
}
/*
[3, 0, 6, 1, 5]
*/
