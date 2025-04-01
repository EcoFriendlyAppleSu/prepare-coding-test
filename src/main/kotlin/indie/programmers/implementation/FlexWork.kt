package indie.programmers.implementation

fun main() {
    val schedules = intArrayOf(700, 800, 1100)
    val timelogs = arrayOf(
        intArrayOf(710, 2359, 1050, 700, 650, 631, 659),
        intArrayOf(800, 801, 805, 800, 759, 810, 809),
        intArrayOf(1105, 1001, 1002, 600, 1059, 1001, 1100),
    )
    val startDay = 5

    println(solution(schedules, timelogs, startDay))
}

/*[700, 800, 1100]
	[[710, 2359, 1050, 700, 650, 631, 659],
	 [800, 801, 805, 800, 759, 810, 809],
	  [1105, 1001, 1002, 600, 1059, 1001, 1100]]
	  	5
	  	result = 3*/
fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {
    var answer: Int = 0

    for((index, startTime) in schedules.withIndex()) {
        var currentDay = startday
        val endTime = generateEndTime(startTime)
        var eachResult = 0
        for(timelog in timelogs[index]) {
            // 토요일 혹은 일요일이라면
            if(currentDay % 7 == 6 || currentDay % 7 == 0) {
                currentDay += 1
            } else { // 주중이라면
                if(timelog <= endTime) {
                    eachResult += 1
                    currentDay += 1
                } else {
                    break
                }
            }
        }
        if(eachResult == 5) {
            answer += 1
        }
    }
    return answer
}
fun generateEndTime(startTime: Int): Int {
    var hour = startTime / 100
    var minute = startTime % 100
    minute += 10
    if(minute >= 60) {
        hour ++
        minute -= 60
    }
    return hour * 100 + minute
}
