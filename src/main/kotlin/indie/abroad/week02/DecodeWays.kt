package indie.abroad.week02

fun main() {

    val s = "111111111111111111111111111111111111111111111"

    println(numDecodings(s))
    println(lengthDivider(s).forEach(::println))
}

fun numDecodings01(s: String): Int {
    if (s.isEmpty() || s.first() == '0') return 0 // 빈 문자열 또는 '0'으로 시작하는 경우 디코딩 불가

    val n = s.length
    val dp = IntArray(n + 1) // dp[i]는 s[0..i-1]까지 디코딩 가능한 경우의 수를 저장
    dp[0] = 1 // 빈 문자열은 하나의 방법으로 디코딩 가능
    dp[1] = if (s[0] != '0') 1 else 0 // 첫 문자가 '0'이 아니면 한 가지 방법 가능

    for (i in 2..n) {
        val oneDigit = s.substring(i - 1, i).toInt()
        val twoDigits = s.substring(i - 2, i).toInt()

        // 한 자리 숫자가 유효한 경우
        if (oneDigit in 1..9) {
            dp[i] += dp[i - 1]
        }

        // 두 자리 숫자가 유효한 경우
        if (twoDigits in 10..26) {
            dp[i] += dp[i - 2]
        }
    }

    return dp[n]
}

fun numDecodings(s: String): Int {
    val alphabetList = mutableListOf<String>()
    var answer = 0
    for (i in 1..26) {
        alphabetList.add(i.toString())
    }
    val dividedList = lengthDivider(s)

    for (dividedElement in dividedList) {
        var index = 0
        var value = 0
        for (each in dividedElement) {
            val subString = s.substring(index, index + each)
            if (subString.first() != '0' && alphabetList.contains(subString)) {
                index += each
                value += 1
            }
        }
        if (value == dividedElement.size) {
            answer += 1
        }
    }
    return answer
}

fun lengthDivider(s: String): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun divide(target: Int, current: List<Int>) {
        if (target == 0) {
            result.add(current)
            return
        }
        if (target < 0) return

        divide(target - 1, current + 1)
        divide(target - 2, current + 2)
    }

    divide(s.length, emptyList())
    return result
}


/*
Input: s = "226"
Output: 3
*/

