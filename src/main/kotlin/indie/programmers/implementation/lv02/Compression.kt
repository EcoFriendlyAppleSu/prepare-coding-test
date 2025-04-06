package indie.programmers.implementation.lv02

fun main() {
    val str = "TOBEORNOTTOBEORTOBEORNOT"
    val map = ('A'..'Z').mapIndexed { index, value -> value.toString() to index + 1}.toMap().toMutableMap()
    val result = mutableListOf<Int>()
    var startIndex = 0

    while(startIndex < str.length) {
        var w = str[startIndex].toString()
        var c = ""

        var endIndex = startIndex + 1

        // 현재 입력에서 사전에 있는 가장 긴 문자열 찾기
        while(endIndex < str.length) {
            val next = w + str[endIndex]
            if(map.containsKey(next)) {
                w = next
                endIndex++
            } else {
                c = str[endIndex].toString() // 사전에 추가되는 마지막 글자
                break
            }
        }

        result.add(map[w]!!)

        if(endIndex < str.length) {
            map[w + c] = map.size + 1
        }

        startIndex = endIndex
    }
    println(result)
}
