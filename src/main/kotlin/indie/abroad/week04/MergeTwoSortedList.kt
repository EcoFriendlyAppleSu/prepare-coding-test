package indie.abroad.week04

/*
* 두 개의 리스트를 크기에 맞게 병합하는 과정
* */
fun main() {
    val node01 = ListNode(1)
    val node02 = ListNode(2)
    val node03 = ListNode(4)

    node01.next = node02
    node02.next = node03

    val node04 = ListNode(1)
    val node05 = ListNode(3)
    val node06 = ListNode(4)

    node04.next = node05
    node05.next = node06

    var currentNode: ListNode? = mergeTwoLists(node01, node04)
    while (currentNode != null) {
        println(currentNode.value)
        currentNode = currentNode.next
    }
}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val resultNode = ListNode(0)
    var currentNode = resultNode

    var firstCurrentNode = list1
    var secondCurrentNode = list2

    while (firstCurrentNode != null && secondCurrentNode != null) {
        if (firstCurrentNode.value <= secondCurrentNode.value) {
            currentNode.next = ListNode(firstCurrentNode.value)
            firstCurrentNode = firstCurrentNode.next
        } else {
            currentNode.next = ListNode(secondCurrentNode.value)
            secondCurrentNode = secondCurrentNode.next
        }
        currentNode = currentNode.next!!
    }

    if (firstCurrentNode != null) {
        currentNode.next = firstCurrentNode
    } else if(secondCurrentNode != null) {
        currentNode.next = secondCurrentNode
    }
    return resultNode.next
}


class ListNode(
    var value: Int,
) {
    var next: ListNode? = null
}


/*
* Input: list1 = [1,2,4], list2 = [1,3,4]
* Output: [1,1,2,3,4,4]
* */
