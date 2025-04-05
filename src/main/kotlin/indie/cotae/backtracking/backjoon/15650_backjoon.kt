package indie.cotae.backtracking.backjoon

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val list = (1..n).toList()
    solution01(list, m)
}

fun solution01(list: List<Int>, target: Int) {
    val result = mutableListOf<List<Int>>()

    fun combine(elements: List<Int>, startIndex: Int, length: Int) {
        if(elements.size == length) {
            val temp = elements.sorted()
            result.add(temp)
            return
        }

        for(index in startIndex until list.size) {
            combine(elements + listOf(list[index]), index + 1, length)
        }
    }

    combine(emptyList(), 0, target)

    println(result.joinToString("\n") { it.joinToString(" ")})
}


/*
3 1
*/
