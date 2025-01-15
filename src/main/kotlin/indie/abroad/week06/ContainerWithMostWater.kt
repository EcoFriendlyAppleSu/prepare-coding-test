package indie.abroad.week06

fun main() {
    val height = intArrayOf(1,8,6,2,5,4,8,3,7) // 9 개
    println(maxArea02(height))
}

/*
* 잘못된 풀이 면적의 넓이를 구하는 문제인데 사이에 존재하는 최대값을 구하는 문제로 이해
* */
fun maxAreaWrongAnswer(height: IntArray): Int {
    var maxValue = Int.MIN_VALUE

    for (i in 0 until height.size) {
        var eachContainer = 0
        for (j in i+1 until height.size) {
            var temp = Math.min(height[i], height[j])
            for (k in j - i ..  j) {
                if (height[k] >= temp) {
                    eachContainer += temp
                } else {
                    eachContainer += height[k]
                }
            }
        }
        println("each container: $eachContainer")
        if (eachContainer >= maxValue) {
            maxValue = eachContainer
        }
    }
    return maxValue
}

fun maxArea01(height: IntArray): Int {
    var maxValue = 0
    for (i in 0 until height.size) {
        for (j in i + 1 until height.size) {
            // 너비는 두 선분 사이의 거리
            val width = j - i
            // 높이는 두 선분 중 작은 값
            val containerHeight = Math.min(height[i], height[j])
            // 면적 계산
            val area = width * containerHeight
            // 최대값 갱신
            maxValue = Math.max(maxValue, area)
        }
    }
    return maxValue
}

fun maxArea02(height: IntArray): Int {
    var maxValue = 0
    var left = 0
    var right = height.size - 1
    while (left <= right) {
        val width = right - left
        val containerHeight = Math.min(height[left], height[right])
        val area = width * containerHeight
        maxValue = Math.max(maxValue, area)
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }
    return maxValue
}

/*
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
*/
