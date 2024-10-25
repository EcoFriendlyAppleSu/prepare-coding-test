package indie.cotae.bfsdfs.leecotae.dfs

import kotlin.math.sign

/**
 * Computation Thinking 이란 요구사항을 데어터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 * 의식적인 연습
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터의 자료구조를 기술하고 선택하기
 * 3. 적절한 알고리즘 선택
 * 4. 2 + 3 을 통해 새로운 데이터 산출
 */
fun main() {

    val startPoint = readln().toInt()

    val adjacencyMatrix = mutableListOf(
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 1, 1, 0, 0, 0, 0, 1),
        mutableListOf(0, 1, 0, 0, 0, 0, 0, 1, 0),
        mutableListOf(0, 1, 0, 0, 1, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 1, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 1, 0),
        mutableListOf(0, 0, 1, 0, 0, 0, 1, 0, 1),
        mutableListOf(0, 1, 0, 0, 0, 0, 0, 1, 0)
    )

    val visitedBoard = MutableList(9) { false }
    val result = mutableListOf<Int>()

    dfs02(adjacencyMatrix, startPoint, visitedBoard, result)
    println(result.joinToString())

}

fun dfs02(adjacencyMatrix: List<List<Int>>, currentNode: Int, visitedBoard: MutableList<Boolean>, result: MutableList<Int>, ) {
    visitedBoard[currentNode] = true
    result.add(currentNode)
    for (node in adjacencyMatrix[currentNode].indices) {
        if (adjacencyMatrix[currentNode][node] == 1 && !visitedBoard[node]) {
            dfs02(adjacencyMatrix, node, visitedBoard, result)
        }
    }
}