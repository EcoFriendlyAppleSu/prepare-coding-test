package indie.cotae.priorityqueue.backjoon

import kotlin.math.abs

class Solution {
    // Pair(absoluteValue, realValue)
    private val heap = mutableListOf<Pair<Int,Int>>()

    fun push(value: Int) {
        heap.add(Pair(abs(value), value))
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
        return rootValue.second
    }

    private fun heapifyDown(rootIndex: Int) {
        var currentIndex = rootIndex
        while (true) {
            val leftChildIndex = 2 * currentIndex + 1
            val rightChildIndex = 2 * currentIndex + 2
            var smallestIndex = currentIndex

            if (leftChildIndex < heap.size) {
                if (heap[leftChildIndex].first < heap[smallestIndex].first || (heap[leftChildIndex].first == heap[smallestIndex].first && heap[leftChildIndex].second < heap[smallestIndex].second)) {
                    smallestIndex = leftChildIndex
                }
            }

            if (rightChildIndex < heap.size) {
                if (heap[rightChildIndex].first < heap[smallestIndex].first || (heap[rightChildIndex].first == heap[smallestIndex].first && heap[rightChildIndex].second < heap[smallestIndex].second)) {
                    smallestIndex = rightChildIndex
                }
            }

            if (currentIndex == smallestIndex) break
            heap.swap(currentIndex, smallestIndex)
            currentIndex = smallestIndex
        }
    }

    private fun heapifyUp(lastIndex: Int) {
            var currentIndex = lastIndex
            while (currentIndex > 0) {
                val parent = (currentIndex - 1) / 2
                // 절댓값이 작거나, 절댓값이 같다면 실제 값이 작은 경우
                if (heap[currentIndex].first < heap[parent].first || (heap[currentIndex].first == heap[parent].first && heap[currentIndex].second < heap[parent].second)) {
                    heap.swap(currentIndex, parent)
                    currentIndex = parent
                } else break
            }
        }

    fun isEmpty(): Boolean {
        return heap.isEmpty()
    }

    private fun MutableList<Pair<Int, Int>>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}

fun main() {
    val time = readln().toInt()
    val givenList = mutableListOf<Int>()
    val solution = Solution()

    for (i in 0 until time) {
        givenList.add(readln().toInt())
    }

    for (i in givenList.indices) {
        if (givenList[i] != 0) {
            solution.push(givenList[i])
        } else {
            if (solution.isEmpty()) {
                println(0)
            } else {
                println(solution.poll())
            }
        }
    }
}

/*
18
1
-1
0
0
0
1
1
-1
-1
2
-2
0
0
0
0
0
0
0
*/
