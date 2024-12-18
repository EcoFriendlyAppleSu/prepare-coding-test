package indie.abroad.week02

/**
 * lowerCase()로 주어짐
 * 공백은 상관 없음 만들 수 있는 단어이기 때문
 *
 * 1. 주어진 두 문자열의 길이가 다르다면 false 반환
 * 2. Map(mutable) 자료구조에 넣고 값을 비교합니다.
 */
fun main() {
    val s = "rat"
    val t = "car"
    println(isAnagram(s, t))
}

fun isAnagram(s: String, t: String): Boolean {
    val alphaArray = CharArray(26) { 'a' + it}

    if (s.length != t.length) return false

    val sMap = alphaArray.associateWith { 0 }.toMutableMap()
    val tMap = alphaArray.associateWith { 0 }.toMutableMap()

    for (i in s.indices) {
        sMap[s[i]] = sMap.getValue(s[i]).plus(1)
        tMap[t[i]] = tMap.getValue(t[i]).plus(1)
    }

    for (alphabet in alphaArray) {
        if (sMap[alphabet] != tMap[alphabet]) {
            return false
        }
    }

    return true
}

/*
Input: s = "anagram", t = "nagaram"
Output: true
*/
