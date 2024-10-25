package indie.cotae.bfsdfs.leecotae.bfs

import java.util.ArrayDeque
import kotlin.collections.ArrayList

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 표현하는 과정
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터 구조를 기술하고 생각하기
 * 3. 적절한 알고리즘 선택
 * 4. 2 + 3을 통해 새로운 데이터 도출
 */

/**
 * 이번 문제를 통해 확실하게 알고 가야할 것
 * 1. vistied를 통해 모두 방문 처리를 하고 따져볼 필요가 없다.
 * 2. bfs에서 방문처리를 진행할 땐, queue에 집어놓고 방문처리를 진행한다.
 * 3. dfs에서도 마찬가지로 stack(memory method stack)에 값을 집어넣고 방문처리 하는 것은 동일하다.
 */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = mutableListOf<MutableList<Int>>()
    // val graph = mutableListOf<ArrayList<Int>>()
    // val visited = Array(n) { ArrayList(List(m) { false }) }.toCollection(ArrayList())

    for (i in 0 until n) {
        val tempList = readln().map { it.toString().toInt() }.toMutableList()
        graph.add(tempList)
    }


    val dx = listOf(0, 1, -1, 0)
    val dy = listOf(1, 0, 0, -1)

    val startPoint = Pair(0, 0)
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.offer(startPoint)

    while (queue.isNotEmpty()) {
        val popElement = queue.removeFirst()

        for (i in 0 until 4) {
            val nextX = popElement.first + dx[i]
            val nextY = popElement.second + dy[i]

            /*if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                continue
            }
            if (graph[nextX][nextY] == 0) {
                continue
            }
            if (graph[nextX][nextY] == 1) {
                graph[nextX][nextY] = graph[popElement.first][popElement.second] + 1
                queue.offerLast(Pair(nextX, nextY))
            }*/


            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                if (graph[nextX][nextY] == 1) {
                    graph[nextX][nextY] = graph[popElement.first][popElement.second] + 1
                    queue.offerLast(Pair(nextX, nextY))
                }
            }
        }
    }

    println(graph.joinToString("\n") { it -> it.joinToString()})
    println(graph[n-1][m-1])
}

/**
5 6
101010
111111
000001
111111
111111
 */