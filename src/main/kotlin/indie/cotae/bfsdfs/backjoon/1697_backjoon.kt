package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

/**
 * 갈 수 있는 방향도 데이터로 인식해야함
 * 시간 값을 사용해서 한 번 해결해보자
 */
fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val visited = MutableList(100000 + 1) { false }
    val queue = ArrayDeque<Pair<Int,Int>>()
    queue.offerLast(Pair(n, 0))

    while (queue.isNotEmpty()) {
        val (current, time) = queue.removeFirst()
        if (current == k) {
            println(time)
            break
        }

        for (next in listOf(current - 1, current + 1, current * 2)) {
            if (next in 0..100000 && !visited[next]) {
                queue.offerLast(Pair(next, time + 1))
                visited[next] = true
            }
        }
    }
}

/**
 * Board에 가중치를 넣고 해결한 풀이
 *
 *     val board = MutableList(100 + 1) { 0 }
 *     val visited = MutableList(100 + 1) { false }
 *
 *
 *     val queue = ArrayDeque<Int>()
 *     board[n] = 1
 *     queue.offerLast(n)
 *     visited[n] = true
 *
 *     while (queue.isNotEmpty()) {
 *         val popElement = queue.removeFirst()
 *         if (popElement == k) {
 *             println(board[popElement] -1)
 *             break
 *         }
 *         for (i in listOf(popElement - 1, popElement + 1, popElement * 2)) {
 *             if (i in 0..100 && !visited[i]) {
 *                 queue.offerLast(i)
 *                 board[i] = board[popElement] + 1
 *                 visited[i] = true
 *             }
 *         }
 *     }
 */