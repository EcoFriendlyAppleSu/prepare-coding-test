package indie.cotae.binarysearch.sample

fun main() {
    val ary = intArrayOf(1, 3, 5, 7, 9, 11, 13)
    val target = 8
    val result = binarySearch(ary, target)

    if (result != -1) {
        println("Element found at index: $result")
    } else {
        println("Element not found in array")
    }
}

fun binarySearch(ary: IntArray, target: Int): Int {
    var left = 0
    var right = ary.size - 1
    while (left <= right) {
        val mid = left + ( right - left ) / 2
        when {
            ary[mid] == target -> return mid
            ary[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }
    return -1
}
