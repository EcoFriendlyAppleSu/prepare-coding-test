package indie.abroad.week09

fun main() {

}

fun hasCycle(head: ListNode?): Boolean {
    var currentNode = head

    while (currentNode?.next != null) {
        if (currentNode.`val` == 10001) return true // 이미 방문한 노드이면 사이클 존재
        currentNode.`val` = 10001 // 방문한 노드 표시
        currentNode = currentNode.next // 다음 노드로 이동
    }

    return false // `null`을 만났다면 사이클 없음
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
