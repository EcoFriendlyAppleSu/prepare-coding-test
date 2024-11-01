package indie.cotae.dp.backjoon

import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val board = mutableListOf<MutableList<Int>>()
    val score = Array(n+1) { IntArray(3) { 0 } }
    board.add(mutableListOf(0, 0, 0))
    for (i in 1..n) {
        board.add(readln().split(" ").map { it.toInt() }.toMutableList())
    }

    if (n == 0) {
        println(0)
        return
    }

    score[1][0] = board[1][0]
    score[1][1] = board[1][1]
    score[1][2] = board[1][2]


    for (i in 2..n) {
        score[i][0] = min(score[i-1][1] + board[i][0], score[i-1][2] + board[i][0])
        score[i][1] = min(score[i-1][0] + board[i][1], score[i-1][2] + board[i][1])
        score[i][2] = min(score[i-1][0] + board[i][2], score[i-1][1] + board[i][2])
    }

    println(score[n].toList().min())
}

/*
3
26 40 83
49 60 57
13 89 99

3
1 100 100
100 1 100
100 100 1
*/
