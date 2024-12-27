package indie.abroad.week03

import kotlin.math.pow

fun main() {
    val n = 12345
    println(reverseBits1(n))
}

// you need treat n as an unsigned value
fun reverseBits1(n:Int):Int {
    val nStr = n.toString(2)
    val totalLength = nStr.length
    var temp = ""
    if (totalLength != 32) {
        for (i in 0 until 32 - totalLength) {
            temp += "0"
        }
    }
    val fullBitString = temp + nStr
    var result = 0

    for (i in (fullBitString.length - 1) downTo 0) {
        val eachBitValue = 2.0.pow(i).toInt()
        if (fullBitString[i] == '0') {
            continue
        } else {
            result += eachBitValue
        }
    }
    println(result.toString(2))
    return result
}

fun numToBinaryString(n: Long): String {
    var result = ""
    var temp = n
    while (temp > 1) {
        result += (temp % 2).toString()
        temp /= 2
    }
    result += "1"
    return result.reversed()
}


fun reverseBits(n: Int): Int {
    var input = n
    var result = 0

    for (i in 0 until 32) {
        // 결과에 현재 비트 추가
        result = (result shl 1) or (input and 1)
        // 입력 비트를 오른쪽으로 이동
        input = input shr 1
    }

    return result
}
