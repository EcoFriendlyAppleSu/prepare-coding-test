package indie.cotae.binarysearch.backjoon

fun main() {
    val n = readln().toInt()
    val board = readln().split(" ").map { it.toInt() }.toMutableList()
    val m = readln().toInt()
    val searchNumberList = readln().split(" ").map { it.toInt() }.toList()
    board.sort()

    for (number in searchNumberList) {
        val result = search(board, number)
        println(result)
    }
}

fun search(board: MutableList<Int>, number: Int): Int {
    var left = 0
    var right = board.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            board[mid] == number -> {
                return 1
            }
            board[mid] < number -> left = mid + 1
            else -> right = mid - 1
        }
    }
    return 0
}
