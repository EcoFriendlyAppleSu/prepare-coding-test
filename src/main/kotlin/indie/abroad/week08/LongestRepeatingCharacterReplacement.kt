package indie.abroad.week08

/*
* sliding window -> 두 개의 포인터를 사용해 윈도우의 길이를 유기적으로 늘렸다 줄였다 하면서 문자열 전체를 탐색하는 방법
* 부분 문자열의 길이 -> "End Index - Start Index + 1" 이 수식이 문자열 길이를 나타냅니다.
* */
fun main() {
    val s = "AABABBA"
    val k = 1
    println(characterReplacement(s, k))
}

fun characterReplacement(s: String, k: Int): Int {
    var maxLen = 0
    val counter = mutableMapOf<Char, Int>()
    var start = 0

    for (end in s.indices) {
        counter[s[end]] = counter.getOrDefault(s[end], 0) + 1 // character mapping

        // 부분 문자열의 길이에서 가장 많이 들어있는 글자의 수를 뺀 값이 k보다 클 경우 시작(start) 포인터를 이동시켜
        // k 만큼 변경했을 때, 연속할 수 있는 문자를 만들 수 있겠금 조정합니다.
        while (end - start + 1 - (counter.values.maxOrNull() ?: 0) > k) {
            counter[s[start]] = counter.getOrDefault(s[start], 0) - 1
            start++
        }

        // 탐색한 부분 문자열 중 가장 긴 문자열의 값을 저장
        maxLen = maxOf(maxLen, end - start + 1)
    }
    return maxLen
}


/*
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
*/
