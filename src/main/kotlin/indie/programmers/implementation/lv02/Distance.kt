package indie.programmers.implementation.lv02

fun main() {
    val places = arrayOf(
        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"),

    )
    println(solution01(places).joinToString(", "))
}

/*[["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"],
["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"],
["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"],
["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"],
["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]*/

fun solution01(places: Array<Array<String>>): IntArray {
    val result = mutableListOf<Int>()
    for(place in places) {
        val value = checkEachCase(place)
        result.add(value)
    }
    return result.toIntArray()
}

fun checkEachCase(eachPlaces: Array<String>): Int {
    val board = mutableListOf<List<String>>()
    for(place in eachPlaces) {
        val temp = place.toCharArray().map { it.toString() }.toList()
        board.add(temp)
    }

    for(i in board.indices) {
        var flag = true
        for(j in board[0].indices) {
            if(board[i][j] == "P") {
                val checkDistance = checkDistance(board, i, j)
                if(checkDistance == false) {
                    flag = false
                    return 0
                }
            }
        }
    }
    return 1
}

fun checkDistance(board: List<List<String>>, row: Int, col: Int): Boolean {
    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)

    // p 위치에서 상하좌우 판단
    for(i in 0 until 4) {
        val nx = row + dx[i]
        val ny = col + dy[i]

        if(nx >= 0 && nx < board.size && ny >= 0 && ny < board[0].size) {
            if(board[nx][ny] == "X") { // 갈 수 없음
                continue
            } else if (board[nx][ny] == "P") { // 거리두기 실패
                return false
            } else { // 빈 책상일 경우
                val check = checkNextDistance(board, nx, ny, dx[i], dy[i])
                if(check) {
                    continue
                } else return false
            }
        }
    }
    return true
}

fun checkNextDistance(board: List<List<String>>, x: Int, y: Int, sameDirX: Int, sameDirY: Int): Boolean {
    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)

    for(i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if((dx[i] == (sameDirX * (-1))) && (dy[i] == (sameDirY * (-1)))) continue // 뒤돌아서 체크하진 않습니다.

        if(nx >= 0 && nx < board.size && ny >= 0 && ny < board[0].size) {
            if(board[nx][ny] == "P") {
                return false
            } else if(board[nx][ny] == "O") {
                continue
            } else {
                continue
            }
        }
    }
    return true
}
