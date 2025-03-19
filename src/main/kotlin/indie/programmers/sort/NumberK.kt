package indie.programmers.sort

/**
 * subList 정렬 시 원본이 정렬됨
 */
fun main() {
    val ary = intArrayOf(1, 5, 2, 6, 3, 7, 4)
    val commands = arrayOf(
        intArrayOf(2, 5, 3),
        intArrayOf(4, 4, 1),
        intArrayOf(1, 7, 3),
    )
    println(solution(ary, commands).joinToString(","))

}
fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
    val answer = mutableListOf<Int>()
    for (command in commands) {
        val (start, end, hit) = command
        val subList = array.slice(start - 1 until end).sorted()
        answer.add(subList[hit-1])
    }
    return answer.toIntArray()
}

/*
[1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]
*/
