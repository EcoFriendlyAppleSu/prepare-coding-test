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
    // ArrayDeque는 내부에서 배열을 사용하지만 설계 자체가 삽입, 삭제, 탐색 등에 최적화 되어 있기 때문에 index를 통해 접근하는 방법은 제공하지 않습니다.
    val queue = mutableListOf<Pair<Int, Int>>()
    val result = mutableListOf<Int>()
    var nextIdx = 0

    for (i in progresses.indices) {
        val pair = Pair(progresses[i], speeds[i])
        queue.add(pair)
    }

    // 작업이 없어질 때까지 반복합니다.
    while (nextIdx != queue.size) {
        var tempAnswer = 0


        for (i in 0 until queue.size) {
            var (progress, speed) = queue[i]
            progress += speed
            val pair = Pair(progress, speed)
            queue[i] = pair
        }

        for (i in nextIdx until queue.size) {
            var progress = queue[i].first
            if (progress >= 100) {
                tempAnswer += 1
                nextIdx += 1
            } else {
                break
            }
        }
        if (tempAnswer != 0) {
            result.add(tempAnswer)

        }
    }
    return result.toIntArray()
}
/*
progress : [93, 30, 55]
speeds : [1, 30, 5]
return : [2, 1]
*/
