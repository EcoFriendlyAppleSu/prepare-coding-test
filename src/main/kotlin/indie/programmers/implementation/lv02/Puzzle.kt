package indie.programmers.implementation.lv02

fun main() {
    val diffs = intArrayOf(1, 5, 3)
    val times = intArrayOf(2, 4, 7)
    val limit = 30L
    println(solution1(diffs, times, limit))
}

/*[1, 5, 3]	[2, 4, 7]	30	3
[1, 4, 4, 2]	[6, 3, 8, 2]	59	2
[1, 328, 467, 209, 54]	[2, 7, 1, 4, 3]	1723	294
[1, 99999, 100000, 99995]	[9999, 9001, 9999, 9001]	3456789012	39354*/

// 시간 초과 발생
fun solution1(diffs: IntArray, times: IntArray, limit: Long): Int {
    var answer = 0
    var level = 1

    while(true) {
        var totalTime: Long = 0 // 각 레벨당 해결할 수 있는 총 시간
        for((index, diff) in diffs.withIndex()) { // 매 문제를 순회하면서 문제를 해결합니다.
            if(diff <= level) { // 만약 문제 난이도가 레벨 보다 작거나 같다면
                totalTime += times[index] // 시간을 추가합니다.
            } else { // 문제 난이도가 레벨보다 크다면
                val prevTime = times[index -1]
                val currentTime = times[index]
                val count = diff - level
                val calTime = (prevTime + currentTime) * count + currentTime
                totalTime += calTime
            }
        }
        if(totalTime > limit.toLong()) {
            level += 1
        } else {
            answer = level
            break
        }
    }
    return answer
}

// 이분 탐색으로 최적의 레벨 찾음 Up Down Game을 생각해보면 처음부터 순회하지 않음
class Solution {
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {

        var left = 1
        var right = 2_000_000_000 // 충분히 큰 값으로 설정

        while (left <= right) {
            val mid = left + (right - left) / 2

            if (isPossible(diffs, times, limit, mid)) {
                right = mid - 1 // 더 작은 레벨을 시도
            } else {
                left = mid + 1 // 더 큰 레벨을 시도
            }
        }

        return left
    }

    // 주어진 레벨로 문제를 모두 풀 수 있는지 확인하는 함수
    private fun isPossible(diffs: IntArray, times: IntArray, limit: Long, level: Int): Boolean {
        var totalTime: Long = 0

        for (i in diffs.indices) {
            if (diffs[i] <= level) {
                // 레벨이 충분히 높으면 기본 시간만 소요
                totalTime += times[i]
            } else {
                // 레벨이 부족하면 추가 시간 계산
                if (i > 0) {
                    val diffLevel = diffs[i] - level
                    totalTime += times[i] + (times[i-1] + times[i]) * diffLevel.toLong()
                } else {
                    // 첫 번째 문제에 대한 처리 (이전 문제가 없는 경우)
                    val diffLevel = diffs[i] - level
                    totalTime += times[i] * (diffLevel.toLong() + 1)
                }
            }

            // 시간 초과 체크를 미리 해서 불필요한 계산 방지
            if (totalTime > limit) {
                return false
            }
        }

        return totalTime <= limit
    }
}
