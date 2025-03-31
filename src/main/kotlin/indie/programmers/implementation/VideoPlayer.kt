package indie.programmers.implementation

fun main() {
    val videoLength = "07:22"
    val pos = "04:05"
    val opStart = "00:15"
    val opEnd = "04:07"
    val commands = arrayOf("next")
    println(solution(videoLength, pos, opStart, opEnd, commands))
}

/*"07:22", "04:05", "00:15", "04:07", ["next"]*/

fun solution(videoLength: String, pos: String, opStart: String, opEnd: String, commands: Array<String>): String {
    var currentTime = pos
    // command를 순회합니다.
    for(command in commands) {
        if (checkOpening(opStart, opEnd, currentTime)) {
            currentTime = opEnd
        }
        // 다음으로 넘거갈 경우
        if (command == "next") {
            currentTime = nextTime(currentTime)
        } else if (command == "prev") {
            currentTime = prevTime(currentTime)
        }

        if (checkOpening(opStart, opEnd, currentTime)) {
            currentTime = opEnd
        }
        // 다음으로 진행된 값이 비디오 길이보다 크다면 끝점으로 이동합니다.
        if(overTime(videoLength, currentTime)) {
            currentTime = videoLength
        }
    }
    return currentTime
}

fun checkOpening(opStart: String, opEnd: String, currentTime: String): Boolean {
    val (opStartHour, opStartMinute) = opStart.split(':')
    val opStartNumber = opStartHour.toInt() * 60 + opStartMinute.toInt()
    val (currentHour, currentMinute) = currentTime.split(':')
    val currentNumber = currentHour.toInt() * 60 + currentMinute.toInt()
    val (opEndHour, opEndMinute) = opEnd.split(':')
    val opEndNumber = opEndHour.toInt() * 60 + opEndMinute.toInt()
    return opStartNumber <= currentNumber && currentNumber <= opEndNumber
}

fun nextTime(currentTime: String): String {
    var result = ""
    val (hour, minute) = currentTime.split(':')
    var newMinute = minute.toInt().plus(10)
    var newHour = hour.toInt()
    if (newMinute > 60) {
        newHour += 1
        newMinute -= 60
    }

    result += if (newHour < 10) {
        "0$newHour"
    } else {
        "$newHour"
    }

    result += ":"

    result += if (newMinute < 10) {
        "0$newMinute"
    } else {
        "$newMinute"
    }

    return result
}

fun prevTime(currentTime: String): String {
    var result = ""
    val (hour, minute) = currentTime.split(':')
    var newHour = hour.toInt()
    var newMinute = minute.toInt().minus(10)
    if (newHour <= 0 && newMinute < 0) {
        return "00:00"
    } else if (newHour > 0 && newMinute < 0) {
        newHour -= 1
        newMinute += 60
        result += if (newHour < 10) {
            "0$newHour"
        } else {
            "$newHour"
        }
        result += ":"
        result += if (newMinute < 10) {
            "0$newMinute"
        } else {
            "$newMinute"
        }
        return result
    } else {
        result += if (newHour < 10) {
            "0$newHour"
        } else {
            "$newHour"
        }
        result += ":"
        result += if (newMinute < 10) {
            "0$newMinute"
        } else {
            "$newMinute"
        }
        return result
    }
}

fun overTime(videoLength: String, currentTime: String): Boolean {
    val (videoHour, videoMinute) = videoLength.split(':')
    val (hour, minute) = currentTime.split(':')

    if(videoHour.toInt() <= hour.toInt() && videoMinute.toInt() <= minute.toInt()) {
        return true
    }
    return false
}

/*
video_len	pos	op_start	op_end	commands	result
"34:33"	"13:00"	"00:55"	"02:55"	["next", "prev"]	"13:00"
*/
