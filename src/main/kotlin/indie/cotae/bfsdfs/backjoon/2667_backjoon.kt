package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque

/**
 * computation thinking 이란 요구사항을 데이터의 시선에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

fun main() {
    val n = readln().toInt()
    val board = mutableListOf<MutableList<Int>>()
    val visited = MutableList(n) { MutableList(n) { false } }
    for (i in 0 until n) {
        val temp = readln().map { it.toString().toInt() }.toMutableList()
        board.add(temp)
    }

    var stateNumber = 1

    for (x in 0 until n) {
        for (y in 0 until n) {
            if (board[x][y] != 0 && visited[x][y] == false) {
                bfs2667(board, visited, x, y, stateNumber, n)
                stateNumber += 1
            }
        }
    }

    val resultMap = mutableMapOf<Int, Int>()
    for (row in board) {
        for (element in row) {
            if (element != 0) {
                resultMap[element] = resultMap.getOrDefault(element, 0) + 1
            }
        }
    }

    val result = resultMap.values.sorted()

    println(stateNumber - 1)
    print(result.joinToString("\n"))
}

fun bfs2667(board: MutableList<MutableList<Int>>, visited: MutableList<MutableList<Boolean>>, currentX: Int, currentY: Int, stateNumber: Int, size: Int) {
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.offer(Pair(currentX, currentY))
    board[currentX][currentY] = stateNumber
    visited[currentX][currentY] = true

    while (queue.isNotEmpty()) {
        val popElement = queue.removeFirst()
        for (i in 0 until 4) {
            val nX = popElement.first + dx[i]
            val nY = popElement.second + dy[i]

            if (nX >= 0 && nX < size && nY >= 0 && nY < size) {
                if (board[nX][nY] != 0 && visited[nX][nY] == false) {
                    queue.offer(Pair(nX, nY))
                    visited[nX][nY] = true
                    board[nX][nY] = stateNumber
                }
            }
        }
    }
}
/*

7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
        */
