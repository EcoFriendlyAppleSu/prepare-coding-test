package indie.abroad.week04

val dx = listOf(0, 1, -1, 0)
val dy = listOf(1, 0, 0, -1)
var row = 0
var col = 0
var solWord = ""
var wordIndex = 0

fun main() {
    /*val board = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E'),
    )
    val word = "ABCCED"
    println(exist(board, word))*/

    val board2 = arrayOf(
        charArrayOf('a', 'b'),
        charArrayOf('c', 'd'),
    )
    val word2 = "abcd"
    println(exist01(board2, word2))
}

fun exist(board: Array<CharArray>, word: String): Boolean {
    val rowSize = board.size
    val colSize = board[0].size
    row = rowSize
    col = colSize
    solWord = word

    val visited = Array(rowSize) { BooleanArray(colSize) }
    val firstLetter = word.first()
    val firstLetterList = firstLetterFinder(firstLetter, board)
    if (firstLetterList.isEmpty()) return false

    for (point in firstLetterList) {
        val x = point.first
        val y = point.second
        wordIndex = 0
        visited[x][y] = true

        if(dp(board, x, y, visited)) return true
        refreshVisited(visited)
    }
    return false
}

fun dp(board: Array<CharArray>, x: Int, y: Int, visited: Array<BooleanArray>): Boolean {
    wordIndex += 1
    if (visited[x][y]) return false
    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in 0 until row && ny in 0 until col && !visited[nx][ny]) {
            if (wordIndex < solWord.length) {
                if (board[nx][ny] == solWord[wordIndex]) {
                    visited[nx][ny] = true
                    dp(board, nx, ny, visited)
                }
            }
        }
    }
    return wordIndex == solWord.length
}

fun refreshVisited(visited: Array<BooleanArray>) {
    for (i in visited.indices) {
        for (j in visited[0].indices) {
            visited[i][j] = false
        }
    }
}

fun firstLetterFinder(firstLetter: Char, board: Array<CharArray>): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    for (i in board.indices) {
        for (j in board[0].indices) {
            if (board[i][j] == firstLetter) {
                result.add(Pair(i, j))
            }
        }
    }
    return result
}




fun exist01(board: Array<CharArray>, word: String): Boolean {
    val rowSize = board.size
    val colSize = board[0].size
    val visited = Array(rowSize) { BooleanArray(colSize) }

    // 모든 위치에서 DFS 탐색 시작
    for (i in board.indices) {
        for (j in board[0].indices) {
            if (board[i][j] == word[0] && dfs(board, word, i, j, 0, visited)) {
                return true
            }
        }
    }
    return false
}

fun dfs(board: Array<CharArray>, word: String, x: Int, y: Int, index: Int, visited: Array<BooleanArray>): Boolean {
    // 탐색 종료 조건
    if (index == word.length) return true
    if (x !in board.indices || y !in board[0].indices || visited[x][y] || board[x][y] != word[index]) return false

    // 현재 위치 방문 처리
    visited[x][y] = true

    // 4방향으로 탐색
    for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (dfs(board, word, nx, ny, index + 1, visited)) {
            return true
        }
    }

    // 방문 복구
    visited[x][y] = false
    return false
}


/*
* Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCCED'
* Output: true
* */
