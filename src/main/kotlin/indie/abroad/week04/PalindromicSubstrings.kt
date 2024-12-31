package indie.abroad.week04

fun main() {
    val s = "aaa"
    println(countSubstrings(s))
}

/*
* 자른 문자열을 개별적으로 회문인지 판단해야하는데 주어진 문자열에서 포함하는 것으로 문제를 해석하고
* 해결했기 때문에 통과하지 못함
* */
fun countSubstrings01(s: String): Int {
    val result = mutableListOf<String>()
    val maxLength = s.length
    var startIndex = 0
    while (startIndex < maxLength) {
        val endIndex = startIndex + 1
        for (i in endIndex until maxLength + 1) {
            val temp = s.substring(startIndex, i)
            if (s.contains(temp.reversed())) {
                result.add(temp)
            }
        }
        startIndex += 1
    }
    println(result.joinToString())
    return result.size
}

fun countSubstrings(s: String): Int {
    var count = 0

    val maxLength = s.length
    for (startIndex in 0 until maxLength) {
        for (endIndex in startIndex + 1..maxLength) {
            val temp = s.substring(startIndex, endIndex)
            if (temp == temp.reversed()) {
                count++
            }
        }
    }
    return count
}
