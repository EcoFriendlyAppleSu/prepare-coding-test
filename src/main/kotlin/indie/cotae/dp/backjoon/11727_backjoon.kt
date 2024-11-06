package indie.cotae.dp.backjoon

fun main() {
    val n = readln().toInt()
    if (n == 1) {
        println(1)
        return
    }
    val board = IntArray(n) { 0 }
    board[0] = 1
    board[1] = 3

    for (i in 2 until n) {
        board[i] = (board[i-1] + board[i-2] * 2) % 10007
    }

    println(board[n-1])
}