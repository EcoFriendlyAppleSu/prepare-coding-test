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

// Bit 연산으로 숫자를 뒤집는 방법
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

/*

당신의 코드에서 잘못된 부분

	1.	비트 조작 대신 문자열 기반으로 처리:
	•	문자열을 이용해 2진수 변환 및 계산을 수행하면 비효율적이고, 부호 없는 정수를 처리하지 못할 가능성이 큽니다.
	2.	올바르지 않은 비트 연산 로직:
	•	비트 값을 뒤집는 과정에서 문자열의 각 비트를 읽고 계산하지만, 이를 비트 연산으로 바로 처리할 수 있습니다.
	3.	2의 거듭제곱 사용:
	•	2.0.pow(i).toInt()는 불필요하며, 비트를 직접 이동하는 방식이 훨씬 효율적입니다.*/
