package indie.abroad.week02

/*
* 전위, 중위 순회로 Binary Tree Node를 구하시오.
* */
fun main() {
    val preorder = intArrayOf(3, 9, 20, 15, 7)
    val inorder = intArrayOf(9, 3, 15, 20, 7)

    val root = buildTree(preorder, inorder)

    // 레벨 순서 탐색 함수
    fun levelOrderTraversal(root: TreeNode?): List<Int?> {
        if (root == null) return emptyList()
        val queue = ArrayDeque<TreeNode?>()
        val result = mutableListOf<Int?>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()
            if (currentNode != null) {
                result.add(currentNode.value) // result에 넣고
                queue.add(currentNode.left) // 다음 왼쪽 부분에 탐색 대상을 넣습니다.
                queue.add(currentNode.right)
            } else {
                result.add(null)
            }
        }
        // 마지막 left Node level에서 존재하는 null 값을 제거하는 과정
        while (result.isNotEmpty() && result.last() == null) {
            result.removeAt(result.size - 1)
        }
        return result
    }

    println(levelOrderTraversal(root)) // 출력: [3, 9, 20, null, null, 15, 7]
}

fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty() || inorder.isEmpty()) return null

    val rootValue = preorder[0]
    val rootNode = TreeNode(rootValue)

    // root를 기준으로 왼쪽, 오른쪽 subTree를 구성
    val pivotValue = inorder.indexOf(rootValue)
    val leftInOrder = inorder.slice(0 until pivotValue)
    val rightInOrder = inorder.slice(pivotValue + 1 until inorder.size)

    // 왼쪽, 오른쪽 subTree를 기준으로 leftPreOrder, rightPreOrder 구성
    val leftPreOrder = preorder.slice(1 until leftInOrder.size + 1)
    val rightPreOrder = preorder.slice(leftInOrder.size + 1 until preorder.size)

    rootNode.left = buildTree(leftPreOrder.toIntArray(), leftInOrder.toIntArray())
    rootNode.right = buildTree(rightPreOrder.toIntArray(), rightInOrder.toIntArray())

    return rootNode
}

class TreeNode(
    val value: Int,
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
