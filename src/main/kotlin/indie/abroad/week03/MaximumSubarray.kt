package indie.abroad.week03

fun main() {
//     val nums = intArrayOf(-2,1,-3,4,-1,2,1,-5,4)
    val nums = intArrayOf(-2,1)
    println(maxSubArray(nums))
}

fun maxSubArray(nums: IntArray): Int {
    val dp = IntArray(nums.size)
    dp[0] = nums[0]

    for (i in 1 until nums.size) {
        if (dp[i - 1] + nums[i] >= 0 && dp[i-1] >= 0) {
            dp[i] = dp[i - 1] + nums[i]
        } else {
            dp[i] = nums[i]
        }
    }
    return dp.max()
}

/*
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
*/
