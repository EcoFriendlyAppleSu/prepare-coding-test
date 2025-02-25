package indie.abroad.week12

fun main() {
    val a = null
    val b = null

    println(a == b)
}

/**
 * 재귀 문제를 다룰 때 의식적인 연습
 * 1. Base Case(기저 조건)를 명확하게 정의
 * 2. 기저 조건으로 주어진 문제에 따라서 탈출 조건 정의
 * 3. 작은 문제로 나눠 생각하기
 * 4. 작은 문제를 결합하여 정답 도출
 */
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true // 둘 다 null이면 같음
    if (p == null || q == null) return false // 한쪽만 null이면 다름
    if (p.`val` != q.`val`) return false // 값이 다르면 다름

    // 왼쪽 서브트리와 오른쪽 서브트리를 각각 재귀적으로 비교
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
