package indie.abroad.week11

import kotlin.math.max

fun main() {
    val intervals = arrayOf(
        intArrayOf(1,3,),
        intArrayOf(2,6),
        intArrayOf(8,10),
        intArrayOf(15,18),
    )

    println(merge(intervals).joinToString { it.joinToString(" ") })
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    val tempSet = mutableSetOf<Int>()
    var maxValue = Int.MIN_VALUE

    for (interval in intervals) {
        val leftValue = interval[0]
        val rightValue = interval[1]
        maxValue = max(maxValue, rightValue)
        for (value in leftValue until  rightValue + 1) {
            tempSet.add(value)
        }
    }

    val checkVisited = BooleanArray(maxValue + 1) { false }
    for (value in tempSet) {
        checkVisited[value] = true
    }

    val result = mutableListOf<IntArray>()
    var start: Int? = null

    for (i in checkVisited.indices) {
        if (checkVisited[i]) {
            if (start == null) start = i // 시작점 저장
            // 연속된 값이 true일 경우엔 넘어감
        } else {
            if (start != null) {
                result.add(intArrayOf(start, i - 1)) // [start, end] 추가
                start = null
            }
        }
    }
    if (start != null) {
        result.add(intArrayOf(start, checkVisited.lastIndex))
    }

    return result.toTypedArray()
}

fun merge02(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.isEmpty()) return intervals

    // 시작점 기준으로 정렬
    val sortedIntervals = intervals.sortedBy { it[0] }

    val result = mutableListOf<IntArray>()
    var currentInterval = sortedIntervals[0]

    for (i in 1 until sortedIntervals.size) {
        val interval = sortedIntervals[i]
        // 겹치는 경우: 현재 구간의 끝이 다음 구간의 시작보다 크거나 같으면 merge
        if (currentInterval[1] >= interval[0]) {
            currentInterval[1] = maxOf(currentInterval[1], interval[1])
        } else {
            // 겹치지 않으면 현재 구간을 결과에 추가하고, 새 구간으로 변경
            result.add(currentInterval)
            currentInterval = interval
        }
    }
    // 마지막 구간 추가
    result.add(currentInterval)

    return result.toTypedArray()
}


/*
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
*/
