package indie.programmers.heap

fun main() {
    val jobs = arrayOf(
        intArrayOf(0, 3),
        intArrayOf(1, 9),
        intArrayOf(3, 5),
    )
    println(solution(jobs))
}

/*
우선순위 조건
 1. 작업 소요 시간이 짧은 것
 2. 작업 요청 시간이 빠른 것
 [작업 요청 시점, 작업 소요 시간]
*/
fun solution(jobs: Array<IntArray>): Int {
    val jobSize = jobs.size
    var totalCost = 0
    var currentTime = 0

    val waitingQueue = jobs.sortedBy { it[0] }.toMutableList() // 요청 시간 기준 정렬
    val diskQueue = DiskQueue()

    while (waitingQueue.isNotEmpty() || diskQueue.size() > 0) {
        // 현재 시간(currentTime) 이하에서 요청된 작업을 대기 큐에서 디스크 큐로 이동
        while (waitingQueue.isNotEmpty() && waitingQueue.first()[0] <= currentTime) {
            val job = waitingQueue.removeFirst()
            diskQueue.push(Triple(job[0], job[1], 0)) // 요청 시간, 소요 시간, 작업 번호
        }

        if (diskQueue.size() > 0) {
            val pop = diskQueue.pop()!!
            currentTime += pop.second // 작업 소요 시간 추가
            totalCost += currentTime - pop.first // 요청 시점부터 종료 시점까지 시간 계산
        } else {
            // 현재 시간에 실행할 수 있는 작업이 없으면, 다음 요청 시점으로 이동
            currentTime = waitingQueue.first()[0]
        }
    }

    return totalCost / jobSize
}



class DiskQueue {
    private val heapq = mutableListOf<Triple<Int, Int, Int>>() // (요청 시각, 소요 시간, 작업 번호)

    fun size() = heapq.size

    fun push(element: Triple<Int, Int, Int>) {
        heapq.add(element)
        heapifyUp(heapq.lastIndex)
    }

    fun pop(): Triple<Int, Int, Int>? {
        if (heapq.isEmpty()) return null
        val root = heapq.first()
        val lastElement = heapq.removeLast()
        if (heapq.isNotEmpty()) {
            heapq[0] = lastElement
            heapifyDown(0)
        }
        return root
    }

    private fun heapifyUp(index: Int) {
        var currentIndex = index
        while (currentIndex > 0) {
            val parentIndex = (currentIndex - 1) / 2
            if (heapq[currentIndex].second < heapq[parentIndex].second) { // 소요 시간이 짧은 작업 우선
                heapq.swap(currentIndex, parentIndex)
                currentIndex = parentIndex
            } else break
        }
    }

    private fun heapifyDown(index: Int) {
        var currentIndex = index
        while (true) {
            val leftChildIndex = (currentIndex * 2) + 1
            val rightChildIndex = (currentIndex * 2) + 2
            var smallestIndex = currentIndex

            if (leftChildIndex < heapq.size && heapq[leftChildIndex].second < heapq[smallestIndex].second) {
                smallestIndex = leftChildIndex
            }
            if (rightChildIndex < heapq.size && heapq[rightChildIndex].second < heapq[smallestIndex].second) {
                smallestIndex = rightChildIndex
            }
            if (currentIndex == smallestIndex) break
            heapq.swap(currentIndex, smallestIndex)
            currentIndex = smallestIndex
        }
    }

    private fun MutableList<Triple<Int, Int, Int>>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}

/*
[[0, 3], [1, 9], [3, 5]]
*/
