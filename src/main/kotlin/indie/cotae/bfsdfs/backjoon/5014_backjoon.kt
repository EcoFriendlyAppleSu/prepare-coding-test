package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque
import kotlin.system.exitProcess

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

/**
 * 문제 푸는데 디테일을 잘 보자. 문제를 잘 읽고 경계값을 정할 때, 주의 필요!
 */
fun main() {
    val (f, s, g, u, d) = readln().split(" ").map { it.toInt() }
    val visitedBoard = BooleanArray(f + 1) { false }
    val board = IntArray(f + 1) { 0 }

    val queue = ArrayDeque<Int>()
    queue.offerLast(s) // 현재 위치를 넣습니다.
    visitedBoard[s] = true

    if (g > f || s > f) {
        println("use the stairs")
    }


    while (queue.isNotEmpty()) {
        val element = queue.removeFirst()
        if (element == g) {
            println(board[element])
            exitProcess(0)
        }

        for (next in listOf(element - d, element + u)) {
            if (next > 0 && next < f + 1 && !visitedBoard[next]) {
                queue.offerLast(next)
                visitedBoard[next] = true
                board[next] = board[element] + 1
            }
        }
    }

    println("use the stairs")
}

