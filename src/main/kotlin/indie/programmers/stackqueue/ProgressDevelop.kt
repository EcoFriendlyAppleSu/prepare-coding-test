package indie.programmers.stackqueue

import java.util.ArrayDeque

/**
 * programmers 기능 개발 문제
 * 시간 복잡도
 * 공간 복잡도
 */
fun main() {
    val progresses = intArrayOf(93, 30, 55)
    val speeds = intArrayOf(1, 30, 5)
    println(solution(progresses, speeds).joinToString())
}

fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    var answer = mutableListOf<Int>()

    val progressQueue = progresses.toMutableList()
    val speedsQueue = speeds.toMutableList()

    while (progressQueue.isNotEmpty()) {
        var tempResult = 0
        for (index in progressQueue.indices) {
            progressQueue[index] += speedsQueue[index]
        }
        while (progressQueue.isNotEmpty()) {
            if (progressQueue.first() >= 100) {
                progressQueue.removeFirst()
                speedsQueue.removeFirst()
                tempResult += 1
            } else {
                break
            }
        }
        if (tempResult != 0) {
            answer.add(tempResult)
        }
    }
    return answer.toIntArray()
}
