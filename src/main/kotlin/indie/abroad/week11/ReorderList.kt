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

/*
* 리스트를 뒤집는 방법.
* 리스트를 뒤집을 때, 이전의 Node를 두고 각 노드를 탐색해가며 추가하는 방식으로 진행
* */
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var curr = head
    while (curr != null) {
        val nextTemp = curr.next
        curr.next = prev
        prev = curr
        curr = nextTemp
    }
    return prev  // 새로운 head가 된 prev 반환
}
