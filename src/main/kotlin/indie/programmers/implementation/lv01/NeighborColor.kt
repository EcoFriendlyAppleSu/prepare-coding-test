package indie.programmers.implementation.lv01

fun main() {
    val board = arrayOf(
        arrayOf("blue", "red", "orange", "red"),
        arrayOf("red", "red", "blue", "orange"),
        arrayOf("blue", "orange", "red", "red"),
        arrayOf("orange", "orange", "red", "blue"),
    )
    val h = 1
    val w = 1
    println(solution(board, h, w))
}

fun solution(board: Array<Array<String>>, height: Int, width: Int): Int {
    if (height >= board.size) return -1
    if (width >= board[0].size) return -1
    var answer = 0
    val currentColor = board[height][width]
    val dx = listOf(1, 0, 0, -1)
    val dy = listOf(0, 1, -1, 0)
    for(i in 0 until 4) {
        val nx = height + dx[i]
        val ny = width + dy[i]
        if(nx >= 0 && nx < board.size && ny >= 0 && ny < board[0].size && board[nx][ny] == currentColor) {
            answer += 1
        }
    }
    return answer
}

/*
[["blue", "red", "orange", "red"],
 ["red", "red", "blue", "orange"],
  ["blue", "orange", "red", "red"],
   ["orange", "orange", "red", "blue"]]
   h  = 1, w = 1
   */
