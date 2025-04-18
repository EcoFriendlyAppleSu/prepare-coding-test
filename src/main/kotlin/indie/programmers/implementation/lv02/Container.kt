package indie.programmers.implementation.lv02

import kotlin.BooleanArray

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
    val rows = storage.size
    val cols = storage[0].length
    val board = Array(rows + 2) { Array(cols + 2) { "-" } }

    // 보드 초기화: 가장자리는 "-"로 둘러싸고, 내부에 storage 값 채우기
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            board[i + 1][j + 1] = storage[i][j].toString()
        }
    }

    // 요청 처리
    for (request in requests) {
        // 각 요청마다 새로운 visited 배열 생성
        val visited = Array(rows + 2) { BooleanArray(cols + 2) }

        // BFS로 외부와 연결된 영역 찾기
        bfs(visited, board)

        // 컨테이너 꺼내기
        putOutContainer(request, board, visited)
    }

    // 남은 컨테이너 수 계산
    var answer = 0
    for (i in 1..rows) {
        for (j in 1..cols) {
            if (board[i][j] != "-") {
                answer++
            }
        }
    }

    return answer
}

fun bfs(visited: Array<BooleanArray>, board: Array<Array<String>>) {
    val dx = intArrayOf(0, 1, 0, -1)
    val dy = intArrayOf(1, 0, -1, 0)
    val queue = ArrayDeque<Pair<Int, Int>>()

    // 시작점: 창고 외부(0,0)
    queue.add(Pair(0, 0))
    visited[0][0] = true

    while (queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            // 범위 체크
            if (nx < 0 || nx >= board.size || ny < 0 || ny >= board[0].size) {
                continue
            }

            // 이미 방문했거나 컨테이너인 경우 스킵
            if (visited[nx][ny] || board[nx][ny] != "-") {
                continue
            }

            visited[nx][ny] = true
            queue.add(Pair(nx, ny))
        }
    }
}

fun putOutContainer(request: String, board: Array<Array<String>>, visited: Array<BooleanArray>) {
    if (request.length == 1) {
        // 지게차 사용 (접근 가능한 컨테이너만 꺼냄)
        ifOneLetter(request, board, visited)
    } else if (request.length == 2) {
        // 크레인 사용 (모든 해당 컨테이너 꺼냄)
        ifTwoLetter(request[0].toString(), board)
    }
}

fun ifOneLetter(request: String, board: Array<Array<String>>, visited: Array<BooleanArray>) {
    for (i in 1 until board.size - 1) {
        for (j in 1 until board[0].size - 1) {
            if (board[i][j] == request && isAccessible(i, j, visited)) {
                board[i][j] = "-"
            }
        }
    }
}

// 컨테이너가 접근 가능한지 확인 (4방향 중 하나라도 외부와 연결되어 있는지)
fun isAccessible(x: Int, y: Int, visited: Array<BooleanArray>): Boolean {
    val dx = intArrayOf(0, 1, 0, -1)
    val dy = intArrayOf(1, 0, -1, 0)

    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (visited[nx][ny]) {
            return true
        }
    }

    return false
}

fun ifTwoLetter(request: String, board: Array<Array<String>>) {
    for (i in 1 until board.size - 1) {
        for (j in 1 until board[0].size - 1) {
            if (board[i][j] == request) {
                board[i][j] = "-"
            }
        }
    }
}
