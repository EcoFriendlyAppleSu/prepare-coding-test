package indie.programmers.bruteforce

fun main() {
    val brown = 10
    val yellow = 2
    println(solution(brown, yellow))
}

fun solution(brown: Int, yellow: Int): IntArray {
    var answer = intArrayOf()
    val yellowList = caseOfYellow(yellow)
    for(element in yellowList) {
        val (first, second) = element
        if(first < second) continue
        if(generateBrown(first, second) == brown) {
            return intArrayOf(first + 2, second + 2)
        }
    }
    return answer
}

fun caseOfYellow(yellow: Int): List<Pair<Int,Int>> {
    if(yellow == 1) return listOf(Pair(1,1))
    val list = mutableListOf<Pair<Int,Int>>()

    for(i in 2..yellow) {
        if(yellow % i == 0) {
            list.add(Pair(i, yellow/i))
        }
    }
    return list
}

fun generateBrown(row: Int, col: Int): Int {
    return (row * 2) + (col * 2) + 4
}
/*
10	2	[4, 3]
*/
