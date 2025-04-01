package indie.basic


fun main() {
    val list = listOf(1, 2, 3, 4)
    println(combinations(list, 2).joinToString("\n") { it.joinToString(", ")})

    val result = solution(list)
    println(result)
}

fun <T> combinations(elements: List<T>, count: Int): List<List<T>> {
    if (count == 0) return listOf(emptyList())
    if (elements.isEmpty()) return emptyList()

    val head = elements.first()
    val rest = elements.drop(1) // [1,2,3,4] -> [2, 3, 4]

    // 나머지(rest)에서 몇 개(count)를 선택하여 모든 조합을 재귀를 통해 찾는 과정
    val combsWithHead = combinations(rest, count -1).map { listOf(head) + it }

    val combsWithoutHead = combinations(rest, count)

    return combsWithHead + combsWithoutHead
}

fun solution(list: List<Int>): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun combine(current: List<Int>, start: Int, length: Int) {
        if(current.size == length) {
            result.add(current)
            return
        }

        for(i in start until list.size) {
            combine(current + list[i], i+1, length)
        }
    }
    combine(emptyList(), 0, 2)
    return result
}
