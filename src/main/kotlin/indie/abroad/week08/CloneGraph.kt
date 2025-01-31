package indie.abroad.week08

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}

fun main() {

}

fun cloneGraph(node: Node?): Node? {
    if (node == null) return null
    if (node.neighbors.isEmpty()) return Node(1)

    // Map< 기존 Node, 복사 Node>
    val nodeMap = mutableMapOf<Node, Node>()

    val queue = ArrayDeque<Node>()
    queue.add(node)
    nodeMap[node] = Node(node.`val`)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        val clonedNode = nodeMap[current]!! // 현재 노드의 복제본

        for (neighbor in current.neighbors) {
            if (neighbor == null) continue

            // 해당 이웃이 아직 복사되지 않았다면 복사하여 맵에 저장하고 큐에 추가
            if (!nodeMap.containsKey(neighbor)) {
                nodeMap[neighbor] = Node(neighbor.`val`)
                queue.add(neighbor)
            }

            // 복제된 현재 노드의 이웃 리스트에 복제된 이웃 노드를 추가
            // 양방향을 따질 필요 없이 내부 neighbor node list에 모든 노드가 있음
            clonedNode.neighbors.add(nodeMap[neighbor])
        }
    }
    return nodeMap[node]
}
