package indie.programmers.heap

fun main() {
    val scoville = intArrayOf(1, 2, 3, 9, 10, 12)
    val k = 7

    println(solution(scoville, k))
}

fun solution(scoville: IntArray, k: Int): Int {
    var answer = 0
    val priorityQueue = PriorityQueue() // 직접 구현한 우선순위 큐 사용
    scoville.forEach { priorityQueue.push(it) } // Min Heap 초기화

    while (priorityQueue.size() > 1) { // 1개 이하일 때 종료 조건 추가
        if (priorityQueue.peek() >= k) return answer // 가장 작은 값이 K 이상이면 종료

        val firstElement = priorityQueue.pop() ?: break
        val secondElement = priorityQueue.pop() ?: break

        val newScoville = firstElement + (secondElement * 2)
        priorityQueue.push(newScoville)
        answer += 1
    }

    return if (priorityQueue.peek() >= k) answer else -1
}



// min heap
class PriorityQueue() {
    val heapq = mutableListOf<Int>()

    fun push(value: Int) {
        heapq.add(value)
        heapifyUp(heapq.lastIndex)
    }

    fun peek(): Int = heapq.first() // 최솟값 반환

    fun size(): Int = heapq.size

    fun pop(): Int? {
        if (heapq.isEmpty()) return null
        val root = heapq.first()
        val lastValue = heapq.removeLast()
        if (heapq.isNotEmpty()) {
            heapq[0] = lastValue
            heapifyDown(0)
        }
        return root
    }

    private fun heapifyUp(index: Int) {
        var currentIndex = index
        while(currentIndex > 0) {
            val parentIndex = (currentIndex -1) / 2
            if (heapq[currentIndex] < heapq[parentIndex]) {
                heapq.swap(currentIndex, parentIndex)
                currentIndex = parentIndex
            } else break
        }
    }

    private fun heapifyDown(index: Int) {
        var currentIndex = index
        while(true) {
            val leftChildIndex = (currentIndex * 2) + 1
            val rightChildIndex = (currentIndex * 2) + 2
            var largestChildIndex = currentIndex

            if (leftChildIndex < heapq.size && heapq[leftChildIndex] < heapq[largestChildIndex]) {
                largestChildIndex = leftChildIndex
            }
            if (rightChildIndex < heapq.size && heapq[rightChildIndex] < heapq[largestChildIndex]) {
                largestChildIndex = rightChildIndex
            }
            if (currentIndex == largestChildIndex) break
            heapq.swap(currentIndex, largestChildIndex)
            currentIndex = largestChildIndex
        }
    }

    private fun MutableList<Int>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}
