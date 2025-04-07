package indie.cotae.greedy.backjoon

/**
 * 모든 경우의 수를 구해두고 계산한다면 시간초과 발생
 */
fun main() {
    val time = readln().toInt()
    val list = mutableListOf<Pair<Int, Int>>()
    for(i in 0 until time) {
        val (first, second) = readln().split(" ").map { it.toInt() }
        list.add(Pair(first, second))
    }
    println(solution2(list))
}

fun solution2(list: List<Pair<Int, Int>>): Int {
    val sortedList = list.sortedWith(
        compareBy<Pair<Int,Int>> { it.second }
            .thenBy { it.first }
    )

    var count =0
    var endTime = 0
    for(meeting in sortedList) {
        val(start, end) = meeting
        if(start >= endTime) {
            count++
            endTime = end
        }
    }

    return count
}

/**
 * brute force 를 사용한 풀이
 */
fun solution(list: List<Pair<Int, Int>>): Int {
    // 시작 시간으로 정렬
    val sortedList = list.sortedBy { it.first }

    // 최대 회의 수를 저장할 변수
    var maxMeetings = 0

    // 모든 회의를 시작점으로 고려
    for (startIdx in sortedList.indices) {
        // 현재 회의부터 시작하는 최대 회의 수 계산
        val count = findMaxMeetings(sortedList, startIdx, sortedList[startIdx].second)
        maxMeetings = maxOf(maxMeetings, count)
    }

    return maxMeetings
}

// 현재 회의 이후에 가능한 최대 회의 수를 계산하는 재귀 함수
fun findMaxMeetings(meetings: List<Pair<Int, Int>>, currentIdx: Int, currentEndTime: Int): Int {
    if (currentIdx >= meetings.size) {
        return 0
    }
    // 현재 회의를 포함
    var count = 1

    // 다음 가능한 회의를 찾기
    var nextMeetingIdx = -1
    var maxNextMeetings = 0

    // 현재 회의 이후부터 탐색
    for (i in currentIdx + 1 until meetings.size) {
        val (nextStart, nextEnd) = meetings[i]

        // 다음 회의가 현재 회의 종료 시간 이후에 시작하는 경우
        if (nextStart >= currentEndTime) {
            // 이 회의를 선택했을 때 추가로 가능한 최대 회의 수 계산
            val nextCount = findMaxMeetings(meetings, i, nextEnd)

            // 더 많은 회의를 할 수 있는 경로가 있다면 업데이트
            if (nextCount > maxNextMeetings) {
                maxNextMeetings = nextCount
                nextMeetingIdx = i
            }
        }
    }

    // 다음에 선택할 회의가 있다면 카운트에 추가
    if (nextMeetingIdx != -1) {
        count += maxNextMeetings
    }

    return count
}


/*
11
1 4
3 5
0 6
5 7
3 8
5 9
6 10
8 11
8 12
2 13
12 14
*/
