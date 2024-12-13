package indie.basic

class BinaryMaxHeap {
    private val heap = mutableListOf<Int>()

    fun peek(): Int? = heap.firstOrNull()
    fun push(value: Int) {
        heap.add(value)
        heapifyUp(heap.lastIndex)
    }

    fun poll(): Int? {
        if (heap.isEmpty()) return null
        val rootValue = heap.first()
        val lastValue = heap.removeAt(heap.lastIndex)

        if (heap.isNotEmpty()) {
            heap[0] = lastValue
            heapifyDown(0)
        }
        return rootValue
    }

    private fun heapifyDown(index: Int) {
        var currentIndex = index
        while(true) {
            val leftChildIndex = 2 * currentIndex + 1
            val rightChildIndex = 2 * currentIndex + 2
            var largestIndex = currentIndex

            if (leftChildIndex < heap.size && heap[leftChildIndex] > heap[largestIndex]) {
                largestIndex = leftChildIndex
            }
            if (rightChildIndex < heap.size && heap[rightChildIndex] > heap[largestIndex]) {
                largestIndex = rightChildIndex
            }

            if (currentIndex == largestIndex) break

            heap.swap(currentIndex, largestIndex)
            currentIndex = largestIndex
        }
    }

    private fun heapifyUp(lastIndex: Int) {
        var current = lastIndex
        while (current > 0) {
            val parent = (current -1) /2
            if (heap[current] > heap[parent]) {
                heap.swap(current, parent)
                current = parent
            } else { break }
        }
    }

    fun printHeap() {
        println(heap)
    }

    private fun MutableList<Int>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}

fun main() {
    val maxHeap = BinaryMaxHeap()
    maxHeap.push(10)
    maxHeap.push(5)
    maxHeap.push(20)
    maxHeap.push(1)
    maxHeap.push(100)
    maxHeap.push(15)


    println("현재 힙 상태:")
    maxHeap.printHeap()

    println("최대값 (peek): ${maxHeap.peek()}")
    println("최대값 제거 (poll): ${maxHeap.poll()}")
    println("최대값 제거 후 상태:")
    maxHeap.printHeap()
}
