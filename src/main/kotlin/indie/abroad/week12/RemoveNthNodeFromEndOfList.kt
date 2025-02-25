package indie.abroad.week12

fun main() {

}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val tempNodeList = mutableListOf<ListNode>()
    var currentHead = head
    while (currentHead != null) {
        tempNodeList.add(currentHead)
        currentHead = currentHead.next
    }

    val preIndex = tempNodeList.size - n - 1
    val postIndex = tempNodeList.size - n + 1

    if (preIndex < 0) {
        if (tempNodeList.size == 1) {
            return null
        }
        return tempNodeList[postIndex]
    } else if (postIndex == tempNodeList.size) {
        tempNodeList[preIndex].next = null
        return head
    } else {
        tempNodeList[preIndex].next = tempNodeList[postIndex]
    }
    return head
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
