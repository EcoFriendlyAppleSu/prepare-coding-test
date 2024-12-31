package indie.abroad.week04

import java.util.ArrayDeque

fun main() {

    val coins = intArrayOf(1, 2, 5)
//    val coins = intArrayOf(2)

    val amount = 11
//    val amount = 1
    println(coinChange(coins, amount))
}

fun coinChange(coins: IntArray, amount: Int): Int {
    if (amount == 0) return 0
    if (coins.isEmpty() || coins.any { it <= 0 }) return -1 // 예외 처리

    val board = IntArray(amount + 1) { -1 } // -1로 초기화 (구성 불가능한 상태)
    val queue = ArrayDeque<Int>()

    // 초기 상태 설정
    for (coin in coins) {
        if (coin <= amount) {
            queue.add(coin)
            board[coin] = 1 // 동전 하나로 구성 가능
        }
    }

    // BFS 탐색
    while (queue.isNotEmpty()) {
        val currentPosition = queue.pollFirst()
        for (coin in coins) {
            val nextPosition = currentPosition + coin
            if (nextPosition in 1..amount) {
                // 아직 방문하지 않았거나 더 적은 동전으로 구성 가능하면 업데이트
                if (board[nextPosition] == -1 || board[nextPosition] > board[currentPosition] + 1) {
                    queue.add(nextPosition)
                    board[nextPosition] = board[currentPosition] + 1
                }
            }
        }
    }

    return board[amount] // 구성 가능 여부 확인
}


/*
* Input: coins = [1,2,5], amount = 11
* Output: 3
* */
