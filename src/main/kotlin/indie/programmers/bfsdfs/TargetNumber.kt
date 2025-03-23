package indie.programmers.bfsdfs

fun main() {
    val numbers = intArrayOf(1, 1, 1, 1, 1)
    val target = 5
    println(solution(numbers, target))
}

fun solution(numbers: IntArray, target: Int): Int {
    var answer = 0
    val list = numbers.toMutableList()
    val queue = ArrayDeque<Int>()
    if(numbers.isEmpty() || list.isEmpty()) return answer

    val firstElement = list.removeAt(0)
    val negativeFirst = firstElement * (-1)
    queue.addLast(firstElement)
    queue.addLast(negativeFirst)
    var time = 2
    // list 안에 값이 존재할 때까지 반복합니다.
    while(list.isNotEmpty()) {
        val currentValue = list.removeAt(0) // 첫 번째 원소를 꺼내오고
        val negativeCurrentValue = currentValue * (-1)

        for(i in 0 until time) {
            val pop = queue.removeFirst()
            queue.addLast(pop + currentValue)
            queue.addLast(pop + negativeCurrentValue)
        }
        time *= 2
    }

    while(!queue.isEmpty()) {
        val pop = queue.removeFirst()
        if(pop == target) { answer += 1}
    }
    return answer
}


/*
[1, 1, 1, 1, 1]	3	5
*/
