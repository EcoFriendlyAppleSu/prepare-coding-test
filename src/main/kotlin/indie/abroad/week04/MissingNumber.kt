package indie.abroad.week04

fun main() {
    val nums = listOf(9,6,4,2,3,5,7,0,1)
    println(missingNumber(nums.toIntArray()))
}
fun missingNumber(nums: IntArray): Int {
    val size = nums.size
    val board = IntArray(size+1)
    for (index in nums.indices) {
        board[nums[index]] = 1
    }

    for (i in board.indices) {
        if (board[i] == 0) {
            return i
        }
    }
    return 0
}


/*
* Input: nums = [9,6,4,2,3,5,7,0,1]
* Output: 8
* */
