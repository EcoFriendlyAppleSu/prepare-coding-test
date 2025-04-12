package indie.cotae.greedy.backjoon

fun main() {
    val time = readln()
    val aList = readln().split(" ").map { it.toInt() }
    val bList = readln().split(" ").map { it.toInt() }

    val ascAList = aList.sorted()
    val descBList = bList.sortedDescending()
    println(solution02(ascAList, descBList))
}

fun solution02(aList: List<Int>, bList: List<Int>): Long {
    var result = 0L
    for(i in 0 until aList.size) {
        result += (aList[i] * bList[i])
    }
    return result
}

// memory 초과 발생
fun solution(aListList: List<List<Int>>, bList: List<Int>): Long {
    val result = mutableListOf<Long>()
    for (aList in aListList) {
        var temp = 0L
        for (index in aList.indices) {
            temp += (aList[index] * bList[index])
        }
        result.add(temp)
    }
    return result.minOrNull() ?: 0
}

fun permutation(list: List<Int>, length: Int): List<List<Int>> {
    if(length == 0) return listOf(emptyList())
    if(list.isEmpty()) return emptyList()
    val perms = mutableListOf<List<Int>>()
    for(index in list.indices) {
        val remaining = list.toMutableList().apply { removeAt(index) }
        for(perm in permutation(remaining, length-1)) {
            perms.add(listOf(list[index]) + perm)
        }
    }
    return perms
}
/*
5
1 1 1 6 0
2 7 8 3 1*/
