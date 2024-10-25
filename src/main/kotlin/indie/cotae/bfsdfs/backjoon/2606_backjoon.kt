package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수를 사용해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 * 문제 이름 : 바이러스 2606 번
 */

fun main() {
    val amountOfComputer = readln().toInt()
    val amountOfNode = readln().toInt()

    val computerMatrix = MutableList(amountOfComputer +1) { mutableListOf<Int>() }
    val visitedBoard = MutableList(amountOfComputer +1) { false }

    for (i in 0 until amountOfNode) {
        val (row, col) = readln().split(" ").map { it.toInt() }
        computerMatrix[row].add(col)
        computerMatrix[col].add(row)
    }

    println(bfs(computerMatrix, visitedBoard, 1))
}

fun bfs(computerMatrix: List<List<Int>>, visitedBoard: MutableList<Boolean>, currentPosition: Int): Int {
    var result = 0

    val queue = ArrayDeque<Int>()
    queue.offer(currentPosition)


    while (queue.isNotEmpty()) {
        val popElement = queue.removeFirst()
        result += 1
        for (node in computerMatrix[popElement]) {
            if (!visitedBoard[node]) {
                queue.offer(node)
                visitedBoard[node] = true
            }
        }
    }

    if (result == 1) {
        return 0
    }
    return result - 2
}