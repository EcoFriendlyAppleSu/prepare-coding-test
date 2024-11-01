package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque
import kotlin.system.exitProcess

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨터가 이해할 수 있는 형태로 만느는 과정
 */

var globalX = 0
var globalY = 0

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = mutableListOf<MutableList<Int>>() // 빙산의 사이즈는 매 년마다 변경되기 때문에 mutable list 로 선언
    var result = 0
    val visited = Array(n) { BooleanArray(m) { false } }

    globalX = n
    globalY = m

    for (time in 0 until n) {
        board.add(readln().split(" ").map { it.toInt() }.toMutableList())
    }

    // 빙산이 얼마동안 남아있는지 판단하지 못하기 때문에 while을 사용해 반복 진행
    while (true) {
        var iceCount = 0
        val countBoard = Array(n) { IntArray(m) { 0 } }
        clearVisited(visited)

        // Zero Count Board
        for (i in board.indices) {
            for (j in board[i].indices) {
                zeroCount(i, j, countBoard, board)
            }
        }

        // 계산 수행
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == 0) {
                    continue
                } else {
                    val temp = board[i][j] - countBoard[i][j]
                    if (temp < 0) {
                        board[i][j] = 0
                    } else {
                        board[i][j] = temp
                    }
                }
            }
        }

        // 얼음 조각 Count
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (!visited[i][j] && board[i][j] != 0) {
                    bfs2573(i, j, board, visited)
                    iceCount += 1
                }
            }
        }

        result += 1
        if (iceCount >= 2) {
            println(result)
            break
        }
        if (judgeBoard(board) || iceCount == 0) {
            println(0)
            exitProcess(0)
        }
    }
}

fun judgeBoard(board: MutableList<MutableList<Int>>): Boolean {
    for (row in board.indices) {
        for (value in board[row].indices) {
            if (value != 0) return false
        }
    }
    return true
}

fun bfs2573(row: Int, col: Int, board: MutableList<MutableList<Int>>, visited: Array<BooleanArray>) {
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)
    val queue = ArrayDeque<Pair<Int,Int>>()
    queue.offerLast(Pair(row,col))
    visited[row][col] = true

    while (queue.isNotEmpty()) {
        val (currentX, currentY) = queue.removeFirst()
        for (i in 0 until 4) {
            val nx = currentX + dx[i]
            val ny = currentY + dy[i]
            if (nx >= 0 && nx < globalX && ny >= 0 && ny < globalY && !visited[nx][ny]) {
                if (board[nx][ny] != 0) {
                    queue.offerLast(Pair(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }
    }
}

fun zeroCount(row: Int, col: Int, countBoard: Array<IntArray>, board: MutableList<MutableList<Int>>) {
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)
    for (i in 0 until 4) {
        val nx = row + dx[i]
        val ny = col + dy[i]
        if (nx >= 0 && nx < globalX && ny >= 0 && ny < globalY) {
            if (board[nx][ny] == 0) {
                countBoard[row][col] += 1
            }
        }
    }
}

fun clearVisited(visited: Array<BooleanArray>) {
    for (i in visited.indices) {
        for (j in visited[i].indices) {
            visited[i][j] = false
        }
    }
}

/*
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0

3 6
0 0 0 0 0 0
0 2 2 2 2 0
0 0 0 0 0 0
*/
