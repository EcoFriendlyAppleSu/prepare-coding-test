package indie.cotae.bfsdfs.leecotae.dfs

/**
 * Computation Thinking 이란 요구사항을 데이터 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 * 의식적인 연습
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터의 자료구조를 기술하고 생각하기
 * 3. 적절한 알고리즘 결정
 * 4. 2 + 3 을 통해 새로운 데이터 산출
 */
fun main() {
    val startPoint = readln().toInt()
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

    val visitedBoard = MutableList(9) { false } // false로 초기화
    val result = mutableListOf<Int>() // 방문 결과 남기는 리스트

    dfs(nodeList, startPoint, visitedBoard, result)

    println(result.joinToString())
}

fun dfs(nodeList: List<List<Int>>, currentNode: Int, visitedBoard: MutableList<Boolean>, result: MutableList<Int>) {
    visitedBoard[currentNode] = true // 방문 처리
    result.add(currentNode)
    for (node in nodeList[currentNode]) {
        if (!visitedBoard[node]) {
            dfs(nodeList, node, visitedBoard, result)
        }
    }
}