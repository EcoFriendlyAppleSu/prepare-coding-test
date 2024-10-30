package indie.cotae.bfsdfs.backjoon

import kotlin.math.max
import java.util.ArrayDeque

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨터가 이해할 수 있는 형태로 만드는 과정
 */

fun main() {

    val n = readln().toInt()
    val board = mutableListOf<MutableList<Int>>()
    val visited = Array(n) { BooleanArray(n) { false } }
    var time = -1
    val answerList = mutableListOf<Int>()

    for (i in 0 until n) {
        val temp = readln().split(" ").map { it.toInt() }.toMutableList()
        board.add(temp)
    }

    for (i in board) {
        time = max(time, i.max())
    }



    for (height in 0..time) {
        resetVisited(visited)
        var answer = 0

        for (row in 0 until n) {
            for (col in 0 until n) {
                if (board[row][col] > height && !visited[row][col]) {
                    bfs2468(row, col, height, board, visited)
                    answer +=1
                }
            }
        }
        answerList.add(answer)
    }

    println(answerList.max())
}

fun bfs2468(row: Int, col: Int, height: Int, board: MutableList<MutableList<Int>>, visited: Array<BooleanArray>) {
    val dx = listOf(0, -1, 0, 1)
    val dy = listOf(1, 0, -1, 0)
    val queue = ArrayDeque<Pair<Int,Int>>()
    val size = visited.size
    queue.offerLast(Pair(row,col))
    visited[row][col] = true

    while (queue.isNotEmpty()) {
        val (currentX, currentY) = queue.removeFirst()

        for (i in 0 until 4) {
            val nx = currentX + dx[i]
            val ny = currentY + dy[i]
            if (nx in 0..<size && ny in 0..<size && !visited[nx][ny] && board[nx][ny] > height) {
                queue.offerLast(Pair(nx, ny))
                visited[nx][ny] = true

            }
        }
    }

}

fun resetVisited(visited: Array<BooleanArray>) {
    for (i in visited.indices) {
        for (j in visited[i].indices) {
            visited[i][j] = false
        }
    }
}

/*
5
6 8 2 6 2
3 2 3 4 6
6 7 3 3 2
7 2 5 3 6
8 9 5 2 7
*/
