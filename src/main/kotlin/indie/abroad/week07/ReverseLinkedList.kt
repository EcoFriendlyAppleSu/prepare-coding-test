package indie.abroad.week07

fun main() {
    val node01 = ListNode(1)
    val node02 = ListNode(2)
    val node03 = ListNode(3)
    val node04 = ListNode(4)
    val node05 = ListNode(5)

    node01.next = node02
    node02.next = node03
    node03.next = node04
    node04.next = node05

    var result = reverseList(node01)
    while (result!!.next != null) {
        println(result.value)
        result = result.next!!
    }

}

fun reverseList(head: ListNode?): ListNode? {
    val stack = mutableListOf<ListNode>()
    var currentNode = head
    if (head == null) return null

    while (currentNode != null) {
        stack.add(currentNode)
        currentNode = currentNode.next
    }


    for (i in stack.size - 1 downTo 1) {
        stack[i].next = stack[i-1]
    }
    stack[0].next = null
    return stack[stack.size -1]
}


class ListNode(var value: Int) {
    var next: ListNode? = null
}


/*
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
*/
