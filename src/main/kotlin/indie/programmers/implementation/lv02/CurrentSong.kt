package indie.programmers.implementation.lv02

fun main() {
    val m = "ABC"
    val musicinfos = arrayOf("12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF")
    println(solution(m, musicinfos))
}
// "ABC"	["12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"]	"WORLD"
fun solution(m: String, musicinfos: Array<String>): String {
    // 멜로디와 재생 시간을 저장할 클래스
    data class Music(val title: String, val playTime: Int, val index: Int)
    val result = mutableListOf<Music>()

    // #을 포함한 음표를 처리하기 위해 모든 #음을 소문자로 치환
    fun preprocessMelody(melody: String): String {
        return melody.replace("C#", "c")
            .replace("D#", "d")
            .replace("F#", "f")
            .replace("G#", "g")
            .replace("A#", "a")
            .replace("B#", "b")
    }

    // 전처리된 기억 멜로디
    val preprocessedM = preprocessMelody(m)

    for ((index, musicinfo) in musicinfos.withIndex()) {
        val parts = musicinfo.split(",")
        val startTime = parts[0]
        val endTime = parts[1]
        val title = parts[2]
        val ackBo = preprocessMelody(parts[3]) // 악보도 전처리

        val playTime = getTime(startTime, endTime)
        var totalAckBo = ""

        // 재생 시간에 맞게 악보 생성
        if (playTime > ackBo.length) {
            val repeat = playTime / ackBo.length
            val spare = playTime % ackBo.length
            totalAckBo = ackBo.repeat(repeat) + ackBo.substring(0, spare)
        } else {
            totalAckBo = ackBo.substring(0, playTime)
        }

        // 멜로디가 악보에 포함되어 있는지 확인
        if (totalAckBo.contains(preprocessedM)) {
            result.add(Music(title, playTime, index))
        }
    }

    if (result.isEmpty()) return "(None)"

    // 재생 시간이 긴 순, 같으면 먼저 입력된 순으로 정렬
    result.sortWith(compareByDescending<Music> { it.playTime }.thenBy { it.index })
    return result.first().title
}

fun getTime(startTime: String, endTime: String): Int {
    val startParts = startTime.split(":")
    val endParts = endTime.split(":")

    val startHour = startParts[0].toInt()
    val startMinute = startParts[1].toInt()
    val endHour = endParts[0].toInt()
    val endMinute = endParts[1].toInt()

    return ((endHour - startHour) * 60) + (endMinute - startMinute)
}

/*
"ABCDEFG"	["12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"]	"HELLO"
"CC#BCC#BCC#BCC#B"	["03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"]	"FOO"
"ABC"	["12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"]	"WORLD"
*/
