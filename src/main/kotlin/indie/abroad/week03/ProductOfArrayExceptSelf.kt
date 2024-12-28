package indie.abroad.week03

fun main() {
    val nums = intArrayOf(1,2,3,4)
    println(productExceptSelf01(nums).joinToString())
}

fun productExceptSelf00(nums: IntArray): IntArray {
    val result = mutableListOf<Int>()

    for (i in nums.indices) {
        var temp = 1
        for (j in nums.indices) {
            if (i == j) continue
            temp *= nums[j]
        }
        result.add(temp)
    }
    return result.toIntArray()
}

fun productExceptSelf01(nums: IntArray): IntArray {
    val result = IntArray(nums.size)

    var leftProduct = 1
    for (i in nums.indices) {
        result[i] = leftProduct
        leftProduct = leftProduct * nums[i]
    }

    var rightProduct = 1
    for (i in nums.indices.reversed()) {
        result[i] = result[i] * rightProduct
        rightProduct = rightProduct * nums[i]
    }
    return result
}

/*
* Input: nums = [1,2,3,4]
* Output: [24,12,8,6]
* */
