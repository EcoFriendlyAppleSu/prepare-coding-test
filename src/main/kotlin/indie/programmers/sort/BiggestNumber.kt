package indie.programmers.sort

fun main() {
    val numbers = intArrayOf(3, 30, 34, 5, 9)
    println(solution(numbers))
}

fun solution2(numbers: IntArray): String {
    val strings = numbers.map { it.toString() }
    val sorted = strings.sortedWith { a, b -> (b + a).compareTo(a + b) }
    if (sorted[0] == "0") return "0"
    return sorted.joinToString("")
}

fun solution(numbers: IntArray): String {
    var answer = ""
    // 1~9 map에 나눠 데이터를 담는다
    val map = mutableMapOf<Char, MutableList<String>>()
    for (number in numbers) {
        map.getOrPut(number.toString().first()) { mutableListOf() }.add(number.toString())
    }

    // 나눠 담은 각각의 배열에서 가장 긴 값에 맞춰 0을 뒤에 붙이고 내림차순으로 정리한다.
    // 내림차순으로 정리할 때, 같은 값에 대해선 원본이 짧은 수가 앞에 오는 것으로 정렬한다.
    for (index in 9 downTo 1) {
        val key = index.toString().first()
        if (map[key] == null) {
            continue
        }
        val value = map[key]!!
        // 9 ~ 1 순회해서 정렬 로직을 수행한 후
        val sortedValue = sortingProcess(value)
        map[key] = sortedValue // 값 변경
    }

    // 하나씩 붙이면서 문자열 추가
    for (index in 9 downTo 1) {
        val key = index.toString().first()
        if (map[key] == null) {
            continue
        }
        val value = map[key]!!
        for (j in value.indices) {
            answer += value[j]
        }
    }
    // 값을 문자열로 변경해 반환한다.
    return answer
}

fun sortingProcess(strings: MutableList<String>): MutableList<String> {
    // 원본, 비교값
    val list = mutableListOf<Pair<String, Long>>()

    // 길이별로 정리
    strings.sortByDescending { it.length }
    val firstLength = strings[0].length // 기준
    for (index in 0 until strings.size) {
        var str = strings[index]
        while (true) {
            if (str.length == firstLength) break
            str += "0"
        }
        list.add(Pair(strings[index], str.toLong()))
    }
    val sortedList = list.sortedWith(compareByDescending<Pair<String, Long>> { it.second }.thenBy { it.first.length })

    return sortedList.map { it.first }.toMutableList()
}
/*
[3, 30, 34, 5, 9]	"9534330"
*/
