package indie.cotae.dp.backjoon

fun main() {
    val n = readln().toInt()
    if (n == 1 || n == 2) {
        println(1)
        return
    }

    var zero: Long = 1
    var one: Long = 1

    for (i in 3 until n) {
        val temp = zero
        zero += one
        one = temp
    }
    println(zero + one)


    /*
    문자열의 마지막을 따져가며 해결한다면 시간초과 발생
    val board = mutableMapOf<Int, MutableList<String>>()
    board.putIfAbsent(0, mutableListOf("1"))

    for (i in 1 until n) {
        val values = board[i-1]!!
        board[i] = mutableListOf()
        for (eachString in values) {
            if (eachString[eachString.lastIndex] == '0') {
                board[i]!!.add(eachString + "0")
                board[i]!!.add(eachString + "1")
            } else if (eachString[eachString.lastIndex] == '1') {
                board[i]!!.add(eachString + "0")
            }
        }
    }
1820529360
    println(board[n-1]!!.size)*/
}