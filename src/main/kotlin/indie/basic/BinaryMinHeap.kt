package indie.basic

/**
 * Heap 이란 최소값이나 최대값을 빠르게 찾아내기 위해 고안된 완전 이진 트리를 사용한 자료구조
 * push를 하게 되면 마지막 배열에 추가된 값이 정렬이 맞지 않음으로 heapifyUp을 사용해 값을 정렬합니다.
 * poll을 하게 되면 root에 있는 가장 작은 값을 꺼내와 삭제합니다. 해당 자리가 비어 있음으로 맨 마지막 값을 채워 넣은 후 heapifyDown을 진행해 정렬을 진행합니다.
 *
 * Tree에서 부모 Node가 N이라면 자식 Node의 Left는 2*n +1, Right는 2*n +2
 */
class BinaryMinHeap {
    // 배열로 heap 저장
    private val heap = mutableListOf<Int>()
    // 최소값 반환
    fun peek(): Int? = heap.firstOrNull()

    // 값 삽입
    fun push(value: Int) {
        heap.add(value)
        heapifyUp(heap.lastIndex)
    }

    // 값 추출
    fun poll(): Int? {
        if (heap.isEmpty()) return null // heap이 비어 있다면 null을 반환합니다.
        val rootValue = heap.first() // root의 값을 담아두고
        val lastValue = heap.removeAt(heap.lastIndex) // 루트의 마지막 index 값을 제거해

        if (heap.isNotEmpty()) {
            heap[0] = lastValue // 마지막 값을 루트로 이동
            heapifyDown(0) // 정렬을 사용해 heapify
        }
        return rootValue
    }

    private fun heapifyDown(index: Int) {
        var current = index
        while (true) {
            val leftChild = 2 * current + 1
            val rightChild = 2 * current + 2
            var smallest = current

            // heap size 안에 존재하고 left child 값이 부모 값보다 작다면
            if (leftChild < heap.size && heap[leftChild] < heap[smallest]) {
                // 변경합니다. (index임)
                smallest = leftChild
            }
            // heap size 안에 존재하고 right child 값이 부모 값보다 작다면
            if (rightChild < heap.size && heap[rightChild] < heap[smallest]) {
                smallest = rightChild
            }
            // 값이 변경되지 않았다면 -> 자식 노드들이 모두 부모 노드보다 값이 크다면 => 부모 노드의 크기가 최소
            if (smallest == current) break // loop 종료

            // 값이 변경 되었다면 -> 자식 노드들 중 작은 값이 존재한다면 변경 (value 변경)
            heap.swap(current, smallest)
            // index 변경
            current = smallest
        }
    }


    private fun heapifyUp(index: Int) {
        var current = index // 추가된 부분의 index를 current에 할당합니다.
        while (current > 0) { // current 가 0일때까지 반복해 진행합니다.
            val parent = (current - 1) / 2 // 완전 이진 트리에서 같은 부모를 가리키기 위한 연산
            if (heap[current] < heap[parent]) { // 만약 부모의 값이 더 클 경우 (최소 힙이기 때문에)
                heap.swap(current, parent) // swap 합니다.
                current = parent // 부모와 값이 변경되었으니 위치하는 index 또한 변경됩니다.
            } else { // 추가되기 전까지 완전 이진 트리를 유지하고 있기 때문에 값이 부모의 값보다 현재의 값이 클 경우 변경을 진행하지 않습니다.
                break
            }
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
    val minHeap = BinaryMinHeap()

    minHeap.push(10)
    minHeap.push(5)
    minHeap.push(20)
    minHeap.push(1)

    println("현재 힙 상태:")
    minHeap.printHeap()

    println("최솟값 (peek): ${minHeap.peek()}")
    println("최솟값 제거 (poll): ${minHeap.poll()}")
    println("최솟값 제거 후 상태:")
    minHeap.printHeap()
}
