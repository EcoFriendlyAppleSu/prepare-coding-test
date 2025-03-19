package indie.programmers.stackqueue

fun main() {
    val prices = intArrayOf(1, 2, 3, 2, 3)
    println(solution(prices).joinToString(", "))
}

fun solution2(prices: IntArray): IntArray {
    val answer = IntArray(prices.size) // 정답 배열
    val stack = ArrayDeque<Int>() // 인덱스를 저장하는 스택

    for (index in prices.indices) {
        // 스택이 비어있지 않고, 현재 가격이 스택의 마지막 가격보다 낮으면 pop
        while (stack.isNotEmpty() && prices[stack.last()] > prices[index]) {
            val lastIndex = stack.removeLast() // 이전 가격의 인덱스
            answer[lastIndex] = index - lastIndex // 가격이 유지된 기간
        }
        stack.addLast(index) // 현재 인덱스를 추가
    }

    // 끝까지 가격이 떨어지지 않은 경우 처리
    while (stack.isNotEmpty()) {
        val lastIndex = stack.removeLast()
        answer[lastIndex] = prices.size - 1 - lastIndex
    }

    return answer
}

fun solution(prices: IntArray): IntArray {
    val answer = mutableListOf<Int>()
    val queue = ArrayDeque<Int>()
    val stack = mutableListOf<Int>()


    for (index in prices.size -1 downTo 0) {
        var temp = 0
        if (stack.isEmpty()) {
            stack.add(index)
            answer.add(temp)
            continue
        }

        while (stack.isNotEmpty()) {
            if (stack.last() >= prices[index]) {
                val pop = stack.removeLast()
                queue.addLast(pop)
                temp += 1
            } else {
                break
            }
        }

        stack.add(prices[index])

        while (queue.isNotEmpty()) {
            val last = queue.removeLast()
            stack.add(last)
        }
        answer.add(temp)
    }

    return answer.reversed().toIntArray()
}

/*
* [1, 2, 3, 2, 3]
* */
