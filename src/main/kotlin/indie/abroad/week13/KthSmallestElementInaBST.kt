package indie.abroad.week13

fun main() {

}

fun kthSmallest(root: TreeNode?, k: Int): Int {
    val stack = ArrayDeque<TreeNode>()
    var current = root
    var count = 0

    while (stack.isNotEmpty() || current != null) {
        // 왼쪽 자식 노드들을 계속 탐색하여 stack에 추가
        while (current != null) {
            stack.addLast(current)
            current = current.left
        }

        // 가장 왼쪽 노드를 pop
        current = stack.removeLast()
        count++

        // k번째 노드라면 값 반환
        if (count == k) return current.`val`

        // 오른쪽 서브트리 탐색
        current = current.right
    }

    return -1  // 이론적으로 도달할 수 없는 부분
}
