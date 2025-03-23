package indie.programmers.bfsdfs

fun main() {
    // 3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]
    val n = 3
    val computers = arrayOf(
        intArrayOf(1, 1, 0),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 0, 1),
    )
    println(solution(n, computers))
}
fun solution(n: Int, computers: Array<IntArray>): Int {
    var answer = 0
    val visited = BooleanArray(n) { false }

    fun bfs(start: Int) {
        val queue = ArrayDeque<Int>()
        queue.add(start)
        visited[start] = true
        while(!queue.isEmpty()) {
            val pop = queue.removeFirst()
            for(i in 0 until n) {
                if(computers[pop][i] == 1 && !visited[i]) {
                    visited[i] = true
                    queue.add(i)
                }
            }
        }
    }

    for(i in 0 until n) {
        if(!visited[i]) { // 방문 전이라면
            bfs(i) // 모두 연결 맺고
            answer += 1
        }
    }
    return answer
}
