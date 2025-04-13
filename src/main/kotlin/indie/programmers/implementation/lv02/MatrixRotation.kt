package indie.programmers.implementation.lv02

fun main() {
    val row = 6
    val col = 6
    val queries = arrayOf(
        intArrayOf(2,2,5,4),
        intArrayOf(3,3,6,6),
        intArrayOf(5,1,6,3),
    )
    println(solution(row, col, queries))
}
/*
6	6	[[2,2,5,4],[3,3,6,6],[5,1,6,3]]	[8, 10, 25]
3	3	[[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]]	[1, 1, 5, 3]
100	97	[[1,1,100,97]]	[1]
*/
fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
    val result = mutableListOf<Int>()
    val board = makeBoard(rows, columns)

    for(query in queries) {
        val temp = changeBoardGetTargetList(board, query)
        result.add(temp.minOrNull() ?: 0)
        val lastValue = temp.removeLast()
        temp.addFirst(lastValue)
        settingBoard(board, query, temp)
    }
    return result.toIntArray()
}

fun makeBoard(row: Int, col: Int): Array<IntArray> {
    val board = Array(row) { IntArray(col) { 0 }}
    var value  = 1
    for(i in 0 until row) {
        for(j in 0 until col) {
            board[i][j] = value
            value++
        }
    }
    return board
}

fun changeBoardGetTargetList(board: Array<IntArray>, query: IntArray): ArrayDeque<Int> {
    var (x1, y1, x2, y2) = query
    x1--
    y1--
    x2--
    y2--
    val result = ArrayDeque<Int>()

    // x1 쪽 채워넣기
    for(i in y1..y2) {
        result.addLast(board[x1][i])
    }
    // y2 쪽 채워넣기
    for(i in x1+1 .. x2) {
        result.addLast(board[i][y2])
    }
    // x2 쪽 채워넣기
    for(i in y2-1 downTo y1) {
        result.addLast(board[x2][i])
    }
    // y1 쪽 채워넣기
    for(i in x2-1 downTo x1+1) {
        result.addLast(board[i][y1])
    }
    return result
}

fun settingBoard(board: Array<IntArray>, query: IntArray, values: ArrayDeque<Int>) {
    var (x1, y1, x2, y2) = query
    x1--
    y1--
    x2--
    y2--

    var time = 0
    for(i in y1..y2) {
        board[x1][i] = values[time]
        time++
    }
    for(i in x1+1 .. x2) {
        board[i][y2] = values[time]
        time++
    }
    for(i in y2-1 downTo y1) {
        board[x2][i] = values[time]
        time++
    }
    for(i in x2-1 downTo x1+1) {
        board[i][y1] = values[time]
        time++
    }
}
