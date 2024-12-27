package indie.abroad.week03

import kotlin.math.max

fun main() {
    val nums = intArrayOf(3,3)
    val startIdx = 6
    println(twoSum(nums, startIdx).joinToString())
}

/*
* 조합으로 문제 풀이
* */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val result = IntArray(2)
    val k = 2
    val maxSize = nums.size
    val indices = IntArray(k)
    for (i in 0 until k ) {
        indices[i] = i
    }

    while (indices[k-1] < maxSize) {
        if (nums[indices[0]] + nums[indices[1]] == target) {
            result[0] = indices[0]
            result[1] = indices[1]
            return result
        }

        var i = k - 1
        while (i >= 0 && indices[i] == i + maxSize - k) {
            i--
        }

        if (i >= 0) {
            indices[i]++
            for (j in i + 1 until k) {
                indices[j] = indices[j - 1] + 1
            }
        } else {
            break
        }
    }
    return result
}


/*
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
*/
