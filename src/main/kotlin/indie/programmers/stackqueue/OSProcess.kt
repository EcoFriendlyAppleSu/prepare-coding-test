package indie.programmers.stackqueue

fun main() {
    val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
    val location = 0
    println(solution2(priorities, location))
}

fun solution(priorities: IntArray, location: Int): Int {
    var answer = 0
    val heapq = PriorityQueue()
    for (index in priorities.indices) {
        heapq.addElement(Pair(priorities[index], index))
    }

    val hit = Pair(priorities[location], location)

    while (true) {
        val element = heapq.popElement()
        if (element!! == hit) {
            answer += 1
            break
        }
        answer += 1
    }

    return answer
}

class PriorityQueue{
    val heapq = mutableListOf<Pair<Int, Int>>()

    fun addElement(element: Pair<Int,Int>) {
        heapq.add(element)
        heapifyUp(heapq.lastIndex)
    }

    fun popElement(): Pair<Int,Int>? {
        if (heapq.isEmpty()) return null
        val rootElement = heapq.first()
        val lastIndex = heapq.removeAt(heapq.lastIndex)

        if (heapq.isNotEmpty()) {
            heapq[0] = lastIndex
            heapifyDown(0)
        }
        return rootElement
    }

    private fun heapifyDown(index: Int) {
        var currentIndex = index
        while (true) {
            val leftChildIndex = 2 * currentIndex + 1
            val rightChildIndex = 2 * currentIndex + 2
            var largestIndex = currentIndex

            if (leftChildIndex < heapq.size && heapq[leftChildIndex].first > heapq[largestIndex].first) {
                largestIndex = leftChildIndex
            }
            if (rightChildIndex < heapq.size && heapq[rightChildIndex].first > heapq[largestIndex].first) {
                largestIndex = rightChildIndex
            }
            if (currentIndex == largestIndex) break
            heapq.swap(currentIndex, largestIndex)
            currentIndex = largestIndex
        }
    }

    fun heapifyUp(lastIndex: Int){
        var currentIndex = lastIndex
        while (currentIndex > 0) {
            val parent = (currentIndex -1) /2
            if (heapq[currentIndex].first > heapq[parent].first) {
                heapq.swap(currentIndex, lastIndex)
                currentIndex = parent
            } else { break }
        }
    }

    private fun MutableList<Pair<Int,Int>>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}

fun solution2(priorities: IntArray, location: Int): Int {
    val priorityQueue = ArrayDeque<Pair<Int, Int>>()

    priorities.forEachIndexed { index, value ->
        priorityQueue.addLast(Pair(value, index))
    }

    val sortedByDescending = priorities.sortedDescending() // 첫 번째 원소로 내림차순 정렬

    var count = 0
    var index = 0  // 현재 실행할 우선순위 인덱스

    while (priorityQueue.isNotEmpty()) {
        val current = priorityQueue.removeFirst()

        // 현재 프로세스의 우선순위가 가장 높은 우선순위와 같은지 확인
        if (current.first == sortedByDescending[index]) {
            count++
            index++

            // 실행된 프로세스가 우리가 찾는 프로세스인지 확인
            if (current.second == location) {
                return count
            }
        } else {
            // 우선순위가 낮다면 다시 큐의 끝에 추가
            priorityQueue.addLast(current)
        }
    }
    return -1
}
