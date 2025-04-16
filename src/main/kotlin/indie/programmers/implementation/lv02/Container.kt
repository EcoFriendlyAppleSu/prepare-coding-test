package indie.programmers.implementation.lv02


// need retry

fun main() {
    val storage = arrayOf("HAH", "HBH", "HHH", "HAH", "HBH")
    val requests = arrayOf("C", "B", "B", "B", "B", "H")
    println(solution(storage, requests))
}

/*
["AZWQY", "CAABX", "BBDDA", "ACACA"]	["A", "BB", "A"]	11
["HAH", "HBH", "HHH", "HAH", "HBH"]	["C", "B", "B", "B", "B", "H"]	4
*/

fun solution(storage: Array<String>, requests: Array<String>): Int {
    var answer: Int = 0
    val board = storage.map { str -> str.map { it.toString()}.toMutableList()}
    println(board)

    for(request in requests) {
        putOutContainer(request, board)
    }

    for(i in 0 until board.size) {
        for(j in 0 until board[0].size) {
            if(board[i][j] != "-") {
                answer += 1
            }
        }
    }
    return answer
}

fun putOutContainer(request: String, board: List<MutableList<String>>) {
    if(request.length == 1) { // 박스가 하나일 경우
        ifOneLetter(request, board)
        println("--------------------")
        println(board.joinToString("\n") { it.joinToString(", ")})
        return
    } else if(request.length == 2) { // 두 개일 경우
        ifTwoLetter(request.substring(0, 1), board)
        return
    }
}

fun ifOneLetter(request: String, board: List<MutableList<String>>) {
    for(i in 0 until board.size) {
        for(j in 0 until board[0].size) {
            if(board[i][j] == request) {
                changeBox(board, i, j)
            }
        }
    }
}

fun changeBox(board: List<MutableList<String>>, x: Int, y: Int) {
    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, 1, 0, -1)
    val checkBox = "-"

    for(i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if(nx < 0) {
            board[x][y] = checkBox
        } else if(ny < 0) {
            board[x][y] = checkBox
        } else if(nx >= board.size) {
            board[x][y] = checkBox
        } else if(ny >= board[0].size) {
            board[x][y] = checkBox
        }
    }
}

fun ifTwoLetter(request: String, board: List<MutableList<String>>) {
    val checkBox = "-"

    for(row in 0 until board.size) {
        for(col in 0 until board[0].size) {
            if(board[row][col] == request) {
                board[row][col] = checkBox
            }
        }
    }
}
