package indie.abroad.week02

fun main() {
    val nums = intArrayOf(-1,0,1,2,-1,-4)
    println(threeSum(nums))
}

// O(n^3) 시간 복잡도를 갖습니다.
fun threeSum(nums: IntArray): List<List<Int>> {
    val processResult = mutableSetOf<List<Int>>()
    val maxNumber = nums.size
    val givenSize = 3
    val indices = IntArray(givenSize)
    for (i in 0 until givenSize) {
        indices[i] = i
    }

    while (indices[givenSize - 1] < maxNumber) {
        processResult.add(indices.map { nums[it] }.sorted())
        var i = givenSize - 1

        while (i >= 0 && indices[i] == i + maxNumber - givenSize) {
            i--
        }

        if (i >= 0) {
            indices[i]++
            for (j in i + 1 until givenSize) {
                indices[j] = indices[j-1] + 1
            }
        } else break
    }

    return processResult.filter { it.sum() == 0 }
}

fun threeSum02(nums: IntArray): List<List<Int>> {
    val processResult = mutableListOf<List<Int>>()
    val sortedNums = nums.sorted()

    for (i in sortedNums.indices) {
        // 중복값을 제거하는 방법
        if (i > 0 && sortedNums[i] == sortedNums[i-1]) continue

        var startIndex = i + 1
        var lastIndex = sortedNums.size - 1

        while (startIndex < lastIndex) {
            val sum = sortedNums[i] + sortedNums[startIndex] + sortedNums[lastIndex]
            when {
                sum == 0 -> {
                    processResult.add(listOf(sortedNums[i], sortedNums[startIndex], sortedNums[lastIndex]))
                    while (startIndex < lastIndex && sortedNums[startIndex] == sortedNums[startIndex + 1]) startIndex++
                    while (startIndex < lastIndex && sortedNums[lastIndex] == sortedNums[lastIndex - 1]) lastIndex--
                    startIndex++
                    lastIndex--
                }
                sum < 0 -> {
                    startIndex++
                }
                else -> {
                    lastIndex--
                }
            }
        }
    }
    return processResult
}

/*
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
*/
