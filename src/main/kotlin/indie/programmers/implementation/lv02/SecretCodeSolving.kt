package indie.programmers.implementation.lv02

fun main() {

}

fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
    // answer = 30 * 29 * 28 * 27 * 26 // n이 30일 때, 만들 수 있는 최대의 수
    val list = (1..n).toList()
    var lists = combinationX(list, 5)

    for((index, value) in q.withIndex()) {
        val currentQ = value.toSet()
        val wantAnswer = ans[index]
        lists = filterCombinations(lists, currentQ, wantAnswer)
    }

    return lists.size
}

fun filterCombinations(lists: List<List<Int>>, q: Set<Int>, answer: Int): List<List<Int>> {
    return lists.filter { candidate ->
        // 현재 후보 조합과 시도한 숫자의 교집합 크기가 응답과 일치하는지 확인
        candidate.intersect(q).size == answer
    }
}

fun combinationX(list: List<Int>, length: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun comb(elements: List<Int>, startIndex: Int, length: Int) {
        if(elements.size == length) {
            result.add(elements)
            return
        }

        for(index in startIndex until list.size) {
            comb(elements + listOf(list[index]), index + 1, length)
        }
    }
    comb(emptyList(), 0, length)
    return result
}
