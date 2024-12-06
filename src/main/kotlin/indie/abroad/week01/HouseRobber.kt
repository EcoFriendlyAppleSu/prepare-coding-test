package indie.abroad.week01

import kotlin.math.max

fun main() {
    val nums = intArrayOf(2,7,9,3,1)
    println(rob(nums))
}

fun rob(nums: IntArray): Int {
    val dp = IntArray(nums.size)

    if (nums.size == 1) {
        return nums[0]
    }

    if (nums.size == 2) {
        return max(nums[0], nums[1])
    }

    dp[0] = nums[0]
    dp[1] = nums[1]
    dp[2] = nums[2] + dp[0]

    for (i in 3 until nums.size) {
        dp[i] = max(dp[i-3], dp[i-2]) + nums[i]
    }
    return dp.max()
}


/*
Input: nums = [2,7,9,3,1]
Output: 12
*/
