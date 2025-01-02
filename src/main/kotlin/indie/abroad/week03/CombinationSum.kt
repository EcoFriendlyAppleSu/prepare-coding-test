package indie.abroad.week03

fun main() {
    val candidates = intArrayOf(2,3)
    val target = 7
    println(combinationSumUsingBackTracking(candidates, target).forEach(::println))
}


// 생각해보니 내장함수도 바운더리를 정할 때, 확실하게 사용되는 범위가 정해진다면 써도 무방할거같네 ㅇㅇ
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun combination(target: Int, current: List<Int>) {
        if (target == 0) {
            result.add(current)
            return
        }
        if (target < 0) return

        for (candidate in candidates) {
            combination(target - candidate, current + candidate)
        }
    }
    combination(target, emptyList())

    val removeDuplicates = mutableSetOf<List<Int>>()

    for (i in result) {
        val temp = i.sorted()
        removeDuplicates.add(temp)
    }
    return removeDuplicates.toList()
}


fun combinationSumUsingBackTracking(candidates: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun combination(target: Int, current: MutableList<Int>, start: Int) {
        println("current list is ${current.joinToString()}")
        if (target == 0) {
            result.add(current.toList()) // 현재 조합을 결과에 추가
            return
        }
        if (target < 0) return

        for (i in start until candidates.size) {
            current.add(candidates[i]) // 후보 추가
            combination(target - candidates[i], current, i) // 현재 후보를 다시 사용할 수 있음
            current.removeAt(current.lastIndex) // 백트래킹
        }
    }

    combination(target, mutableListOf(), 0)
    return result
}

/*
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
*/
