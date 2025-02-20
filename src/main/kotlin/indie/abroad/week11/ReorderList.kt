package indie.abroad.week11

fun main() {

}

fun reorderList(head: ListNode?): Unit {
    val tempNodeList = mutableListOf<ListNode>()
    var currentNode = head

    while (currentNode != null) {
        tempNodeList.add(currentNode)
        currentNode = currentNode.next
    }

    // 양쪽 끝에서부터 교차로 연결
    var i = 0
    var j = tempNodeList.size - 1
    while (i < j) {
        // 먼저 앞쪽 노드의 next가 뒤쪽 노드를 가리키게 함
        tempNodeList[i].next = tempNodeList[j]
        i++  // 다음 앞쪽 노드 선택

        // 만약 앞쪽과 뒤쪽이 만난 경우 (짝수개일 때), 반복 종료
        if (i == j) break

        // 뒤쪽 노드의 next가 새로운 앞쪽 노드를 가리키게 함
        tempNodeList[j].next = tempNodeList[i]
        j--  // 다음 뒤쪽 노드 선택
    }
    tempNodeList[i].next = null
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
