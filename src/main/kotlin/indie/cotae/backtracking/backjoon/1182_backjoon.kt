package indie.cotae.backtracking.backjoon

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val list = readln().split(" ").map { it.toInt() }
    println(solution(list, s))
}

fun solution(list: List<Int>, target: Int): Int {
    var count = 0
    fun combination(elements: List<Int>, startIndex: Int, length: Int) {
        if(elements.size == length) {
            var temp = 0
            for(element in elements) {
                temp += element
            }
            if(temp == target) {
                count++
            }
            return
        }
        for(index in startIndex until list.size) {
            combination(elements + list[index], index + 1, length)
        }
    }

    for(time in 1 until list.size + 1) {
        combination(emptyList(), 0, time)
    }
    return count
}


/*
5 0
-7 -3 -2 5 8
*/
