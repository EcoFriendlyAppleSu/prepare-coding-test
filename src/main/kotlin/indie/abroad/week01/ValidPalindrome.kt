package indie.abroad.week01

/**
 * 문자열의 대칭을 판단하는 문제
 * String으로 오는 문자열을 Char 하나로 판별하고 Lower case로 변경한 후
 * 대칭 검사
 *
 */
fun main() {
//    val s = "A man, a plan, a canal: Panama"
    val s = "0P"
    println(isPalindrome(s))
}


fun isPalindrome(s: String): Boolean {
    val splitGivenString = s.toCharArray()
        .filter { it.isLetter() || it.isDigit() }
        .map { it.lowercaseChar() }
        .toCharArray()

    if (splitGivenString.isEmpty()) { return true }

    var start = 0
    var end = splitGivenString.size - 1

    while (start <= end) {
        if (splitGivenString[start] == splitGivenString[end]) {
            start += 1
            end -= 1
            continue
        }
        return false
    }
    return true
}

/*
Input: s = "A man, a plan, a canal: Panama"
Output: true
*/
