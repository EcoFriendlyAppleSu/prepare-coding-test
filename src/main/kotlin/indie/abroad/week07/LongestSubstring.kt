package indie.abroad.week07

import kotlin.math.max

fun main() {
    val s = "abac"
    println(lengthOfLongestSubstring(s))
}

fun lengthOfLongestSubstring(s: String): Int {
    var maximumLength = 0
    for (start in s.indices) {
        val tempList = mutableListOf<Char>()
        for (end in start until s.length) {
            if (tempList.contains(s[end])) {
                break // escape loop
            }
            tempList.add(s[end])
            maximumLength = max(tempList.size, maximumLength)
        }
    }
    return maximumLength
}


/*
Input: s = "pwwkew"
Output: 3
*/
