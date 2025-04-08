package indie.programmers.implementation.lv02

fun main() {
    val m = 4
    val n = 5
    val board = arrayOf("CCBDE", "AAADE", "AAABF", "CCBBF")
    val result =solution03(m,n,board)
    println(result)
}

fun solution03(m: Int, n: Int, board: Array<String>): Int {
    var answer = 0
    val changeBoard = board.map { it.toCharArray() }.toTypedArray()
    var booleanBoard = generateCheckBoard(m, n)


    while(true) {
        // hit을 진행
        hitting(changeBoard, m, n, booleanBoard)
        // 보드에 변경사항이 존재하지 않다면 탈출
        if (checkBoardCheck(booleanBoard)) {
            break
        }
        // 정답에 플러스
        answer += countBoard(booleanBoard)

        // board 변경 작업 수행(내리기)
        downBoard(changeBoard, booleanBoard, m, n)

        // checkboard 초기화
        booleanBoard = generateCheckBoard(m,n)
    }

    return answer
}

fun downBoard(board: Array<CharArray>, check: Array<BooleanArray>, m: Int, n: Int) {
    val uni = '-'
    // 방문한 곳 처리함 특수 문자 '-'를 사용
    for(i in 0 until m){
        for(j in 0 until n) {
            if(check[i][j] == true) {
                board[i][j] = uni
            }
        }
    }

    // 내리는 작업 수행 column 을 순회하면서 작업 진행
    for(i in 0 until n) {
        for(j in 0 until m - 1) {
            for(k in 0 until m - 1 - j) {
                if(board[k+1][i] == uni) {
                    val temp = board[k+1][i]
                    board[k+1][i] = board[k][i]
                    board[k][i] = temp
                }
            }
        }
    }
}

fun hitting(board: Array<CharArray>, m: Int, n: Int, check: Array<BooleanArray>) {
    val uni = '-'
    for(i in 0 until m-1) {
        for(j in 0 until n-1) {
            if(board[i][j] != uni && board[i][j+1] != uni && board[i+1][j] != uni && board[i+1][j+1] != uni) {
                val standard = board[i][j]
                if(board[i][j+1] == standard && board[i+1][j] == standard && board[i+1][j+1] == standard) {
                    check[i][j] = true
                    check[i][j+1] = true
                    check[i+1][j] = true
                    check[i+1][j+1] = true
                }
            }
        }
    }
}

fun countBoard(board: Array<BooleanArray>):Int {
    var count = 0
    for(i in 0 until board.size) {
        for(j in 0 until board[0].size) {
            if(board[i][j] == true) count++
        }
    }
    return count
}

fun generateCheckBoard(m: Int, n: Int): Array<BooleanArray> {
    return Array(m) { BooleanArray(n) } // m by n array board
}

fun checkBoardCheck(board: Array<BooleanArray>): Boolean {
    for(i in 0 until board.size) {
        for(j in 0 until board[0].size) {
            if(board[i][j] == true) return false
        }
    }
    return true
}

/*
6	6	["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]	15
*/
