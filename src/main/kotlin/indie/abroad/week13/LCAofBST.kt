package indie.abroad.week13

fun main() {

}

fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    var currentNode = root

    while (currentNode != null) {
        when {
            p?.`val`!! < currentNode.`val` && q?.`val`!! < currentNode.`val` -> currentNode = currentNode.left
            p?.`val`!! > currentNode.`val` && q?.`val`!! > currentNode.`val` -> currentNode = currentNode.right
            else -> return currentNode
        }
    }
    return null
}

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/*
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
*/
