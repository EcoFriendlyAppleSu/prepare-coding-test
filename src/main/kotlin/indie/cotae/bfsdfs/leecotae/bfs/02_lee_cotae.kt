package indie.cotae.bfsdfs.leecotae.bfs

import java.time.Year
import java.util.ArrayDeque

/**
 * computation thinking이란 요구사항을 데이터의 관점으로 바라보고 변수, 타입, 조건, 반복, 함수 등을 활용해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

fun main() {
    val row = readln().toInt()
    var col = 0
    val iceBoard = mutableListOf<List<Int>>()
    for (i in 0 until row) {
        val temp = readln().map { it.toString().toInt() } // "01100" -> ["0", "1", "1", "0", "0"] -> [0,1,1,0,0]
        iceBoard.add(temp)
    }

    col = iceBoard[0].size

    val visitedBoard = MutableList<MutableList<Boolean>>(row) { MutableList(col) {false} }
    var result = 0
    for (i in iceBoard.indices) {
        for (j in iceBoard[i].indices) {
            if (iceBoard[i][j] == 0 && !visitedBoard[i][j]) {
                bfs02(iceBoard, visitedBoard, i, j)
                result += 1
            }
        }
    }
    println(visitedBoard.joinToString("\n") {index -> index.joinToString()})
    println(result)
}

fun bfs02(iceBoard: List<List<Int>>, visitedBoard: MutableList<MutableList<Boolean>>, currentRow: Int, currentCol: Int, ) {
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)

    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.offer(Pair(currentRow, currentCol))
    visitedBoard[currentRow][currentCol] = true

    while (queue.isNotEmpty()) {
        val popPairElement = queue.removeFirst()
        for (i in 0 until 4) {
            val nextX = popPairElement.first + dx[i]
            val nextY = popPairElement.second + dy[i]

            if (nextX >= 0 && nextX < iceBoard.size && nextY < iceBoard[0].size && nextY >= 0) {
                if (iceBoard[nextX][nextY] == 0 && !visitedBoard[nextX][nextY]) {
                    queue.offer(Pair(nextX, nextY))
                    visitedBoard[nextX][nextY] = true
                }
            }
        }
    }
}

/*

4
00110
00011
11111
00000
        */
