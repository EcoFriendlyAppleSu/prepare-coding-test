package indie.cotae.binarysearch.backjoon

fun main() {
    val n = readln().toInt()
    val board = readln().split(" ").map { it.toInt() }.toIntArray()
    val m = readln().toInt()
    val numberList = readln().split(" ").map { it.toInt() }.toIntArray()

    board.sort()
    val resultScore = mutableListOf<Int>()

    for (number in numberList) {
        resultScore.add(binarySearchUpper(board, number) - binarySearchLower(board, number))
    }

    println(resultScore.joinToString(" "))
}

fun binarySearchLower(board: IntArray, number: Int): Int {
    var left = 0
    var right = board.size

    while (left < right) {
        val mid = left + (right - left) / 2
        if (number <= board[mid]) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}

fun binarySearchUpper(board: IntArray, number: Int): Int {
    var left = 0
    var right = board.size

    while (left < right) {
        val mid = left + (right - left) / 2
        if (number < board[mid]) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}
/*
10
6 3 2 10 10 10 -10 -10 7 3
8
10 9 -5 2 3 4 5 -10

5
1 2 3 4 5
3
1 2 1
*/

// 3 0 0 1 1 0 0 1
// 1 0 0 1 2 0 0 2

// -10, -10, 2, 3, 3, 6, 7, 10, 10, 10