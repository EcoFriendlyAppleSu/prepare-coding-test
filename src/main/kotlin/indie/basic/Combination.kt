package indie.basic


fun main() {
    val list = listOf(1, 2, 3, 4)
    println(combinations(list, 2).joinToString("\n") { it.joinToString(", ")})
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
