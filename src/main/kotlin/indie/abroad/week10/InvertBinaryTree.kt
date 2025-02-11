package indie.abroad.week10

fun main() {

}

fun invertTree(root: TreeNode?): TreeNode? {
    recursiveNode(root)
    return root
}

fun recursiveNode(parentNode: TreeNode?) {
    if (parentNode == null) return

    swapNode(parentNode) // 현재 노드의 left와 right를 교환
    recursiveNode(parentNode.left)  // 왼쪽 서브트리 탐색
    recursiveNode(parentNode.right) // 오른쪽 서브트리 탐색
}

fun swapNode(parentNode: TreeNode?) {
    if (parentNode == null) return

    val temp = parentNode.left
    parentNode.left = parentNode.right
    parentNode.right = temp
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/*
Kotlin에서는 객체 자체가 아닌, 객체의 참조값(Reference)이 복사됨
따라서, parentNode.left와 parentNode.right를 직접 수정해야 트리가 올바르게 반전됨
지역 변수(leftNode, rightNode)를 바꿔봤자 원본 parentNode에는 영향을 주지 않음

따라서, 아래와 같이 작성한다면 원본인 parentNode의 left, right의 위치를 변경할 수 없음

fun swapNode(parentNode: TreeNode?): TreeNode? {
    var leftNode = parentNode?.left
    var rightNode = parentNode?.right
    var temp: TreeNode? =  null

    temp = leftNode
    leftNode = rightNode
    rightNode = temp
    return parentNode
}*/
