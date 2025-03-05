package indie.abroad.week13

fun main() {
    val list: MutableList<Interval> = mutableListOf()
    list.add(Interval(51, 52))
    list.add(Interval(0, 50))
    list.add(Interval(99, 101))
    println(canAttendMeetings(list))
}

fun canAttendMeetings(intervals: List<Interval?>): Boolean {
    if (intervals.isEmpty()) return true
    val sortedByList = intervals.filterNotNull().sortedBy { it.start }
    for (i in 1 until sortedByList.size) {
        if (sortedByList[i - 1].end > sortedByList[i].start) {
            return false
        }
    }
    return true
}

data class Interval(
    var start: Int = 0,
    var end: Int = 0
)

/*
* intervals = [(0,30),(5,10),(15,20)]
* */
