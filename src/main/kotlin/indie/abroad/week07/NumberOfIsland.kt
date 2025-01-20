package indie.abroad.week07

val dx = listOf(0, 1, -1, 0)
val dy = listOf(1, 0, 0, -1)

fun main() {
    val grid = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1'),
    )
    println(numIslands(grid))
}

fun numIslands(grid: Array<CharArray>): Int {
    val board = Array(grid.size) { BooleanArray(grid[0].size) { false } }
    var result = 0
    for (i in grid.indices) {
        for (j in grid[0].indices) {
            if (grid[i][j] == '1' && board[i][j] == false) {
                bfs(grid, board, i ,j)
                result += 1
            }
        }
    }
    return result
}

fun bfs(grid: Array<CharArray>, board: Array<BooleanArray>, x: Int, y: Int) {
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    board[x][y] = true

    while (queue.isNotEmpty()) {
        val (row, col) = queue.removeFirst()
        for (i in IntRange(0, 3)) {
            val nx = row + dx[i]
            val ny = col + dy[i]
            if (nx >= 0 && nx < grid.size && ny >= 0 && ny < grid[0].size && !board[nx][ny] && grid[nx][ny] == '1') {
                queue.add(Pair(nx, ny))
                board[nx][ny] = true
            }
        }
    }
}

/*
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/
