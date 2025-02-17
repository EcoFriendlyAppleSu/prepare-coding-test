package indie.abroad.week11

fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    val maxValue = findDepth(root, 1)
    return maxValue
}

fun findDepth(currentNode: TreeNode?, depth: Int): Int{
    if (currentNode == null) {
        return depth - 1
    }

    val leftValue = findDepth(currentNode.left, depth + 1)
    val rightValue = findDepth(currentNode.right, depth + 1)
    return maxOf(leftValue, rightValue)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
