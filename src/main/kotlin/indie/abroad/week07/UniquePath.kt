package indie.abroad.week07

fun main() {
    println(uniquePaths(3,2))
}


fun uniquePaths(m: Int, n: Int): Int {
    val board = Array(m) { IntArray(n) { 0 } }

    for (i in IntRange(0, m-1)) {
        board[i][0] = 1
    }
    for (i in IntRange(0, n-1)) {
        board[0][i] = 1
    }

    for (i in 1 until m) {
        for (j in 1 until n) {
            board[i][j] = board[i][j-1] + board[i-1][j]
        }
    }

    return board[m-1][n-1]
}


/*
Input: m = 3, n = 2
Output: 3
*/
