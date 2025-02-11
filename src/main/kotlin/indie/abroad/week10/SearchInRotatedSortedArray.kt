package indie.abroad.week10

fun main() {
    val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
    val target = 0

    println(search(nums, target))
}

fun search(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.size - 1

    while (start <= end) {
        val mid = start + (end - start) / 2

        if (nums[mid] == target) return mid

        // 왼쪽 부분이 정렬된 경우
        if (nums[start] <= nums[mid]) {
            if (nums[start] <= target && target < nums[mid]) {
                end = mid - 1  // 왼쪽에서 탐색
            } else {
                start = mid + 1 // 오른쪽에서 탐색
            }
        }
        // 오른쪽 부분이 정렬된 경우
        else {
            if (nums[mid] < target && target <= nums[end]) {
                start = mid + 1 // 오른쪽에서 탐색
            } else {
                end = mid - 1  // 왼쪽에서 탐색
            }
        }
    }

    return -1
}

/*
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
*/
