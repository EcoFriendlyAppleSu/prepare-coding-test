package indie.cotae.bfsdfs.leecotae.dfs

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

fun main() {

    val rowSize = readln().toInt()
    val iceBoard = mutableListOf<List<Int>>()
    for (i in 0 until rowSize) {
        val temp = readln().map { it.toString().toInt() } // "01100" -> ["0", "1", "1", "0", "0"] -> [0,1,1,0,0]
        iceBoard.add(temp)
    }

    val colSize = iceBoard[0].size
    var result = 0
    val visitedBoard = MutableList(rowSize) { MutableList(colSize) {false} }

    for (i in iceBoard.indices) {
        for (j in iceBoard[i].indices) {
            if (dfs(iceBoard, visitedBoard, i, j, rowSize, colSize)) {
                result += 1
            }
        }
    }

    println(visitedBoard.joinToString("\n") {index -> index.joinToString()})
    println(result)
}

fun dfs(iceBoard: List<List<Int>>, visitedBoard:MutableList<MutableList<Boolean>>, row: Int, col: Int, rowSize: Int, colSize: Int): Boolean {
    if (row <= -1 || row >= rowSize || col <= -1 || col >= colSize) {
        return false
    }
    if (!visitedBoard[row][col] && iceBoard[row][col] == 0) {
        visitedBoard[row][col] = true
        dfs(iceBoard,visitedBoard, row -1, col, rowSize, colSize)
        dfs(iceBoard,visitedBoard, row, col -1, rowSize, colSize)
        dfs(iceBoard,visitedBoard, row +1, col, rowSize, colSize)
        dfs(iceBoard,visitedBoard, row, col +1, rowSize, colSize)
        return true
    }
    return false
}