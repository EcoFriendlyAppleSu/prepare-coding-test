package indie.cotae.bfsdfs.leecotae.bfs

import java.util.ArrayDeque
import java.util.Deque

/**
 * Computation Thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 데이터를 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

fun main() {
    val startNode = readln().toInt()
    val nodeList = listOf(
        emptyList(),
        listOf(2, 3, 8),
        listOf(1, 7),
        listOf(1, 4, 5),
        listOf(3, 5),
        listOf(3, 4),
        listOf(7),
        listOf(2, 6, 8),
        listOf(1, 7),
    )
    val visitedBoard = MutableList<Boolean>(9) { false }
    val result = mutableListOf<Int>()

    bfs01(nodeList, startNode, visitedBoard, result)
    println(result.joinToString())
}
// input : 1, output : 1, 2, 3, 8, 7, 4, 5, 6
// input : 2, output : 2, 1, 7, 3, 8, 6, 4, 5

fun bfs01(nodeList: List<List<Int>>, currentNode: Int, visitedBoard: MutableList<Boolean>, result: MutableList<Int>) {
    val queue = ArrayDeque<Int>()
    queue.offerLast(currentNode)
    visitedBoard[currentNode] = true
    while (queue.isNotEmpty()) {
        val popElement = queue.removeFirst()
        visitedBoard[popElement] = true
        result.add(popElement)
        for (node in nodeList[popElement]) {
            if (!visitedBoard[node]) {
                queue.offerLast(node)

            }
        }
    }
}