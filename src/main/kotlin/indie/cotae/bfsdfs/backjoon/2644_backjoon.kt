package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

/**
 * 유연한 사고가 필요합니다. 방문에 가중치를 두는 것을 생각했어야 되는데!
 * bfs로 풀이
 */
fun main() {
    val n = readln().toInt()
    val (person01, person02) = readln().split(" ").map { it.toInt() }
    val edge = readln().toInt()
    val matrix = MutableList<MutableList<Int>>(n + 1) { mutableListOf() }
    val visited = Array(n + 1) { 0 }


    for (i in 0 until edge) {
        val temp = readln().split(" ").map { it.toInt() }
        matrix[temp[0]].add(temp[1])
        matrix[temp[1]].add(temp[0])
    }

    if (person01 == person02) {
        println(-1)
    }
    visited[person01] = 1

    dfs2644(matrix, person01, visited)

    println(visited.joinToString())

    if (visited[person02] == 0) {
        println(-1)
    } else {
        println(visited[person02] - 1)
    }

    /*var result = 0
    val queue = ArrayDeque<Int>()
    queue.offer(person01)
    visited[person01] = 1

    while (queue.isNotEmpty()) {
        val popElement = queue.removeFirst()

        if (popElement == person02) {
            result = visited[person02] -1
            break
        }
        for (next in matrix[popElement]) {
            if (visited[next] == 0) {
                queue.offer(next)
                visited[next] = visited[popElement] + 1
            }
        }
    }

    if (visited[person02] == 0) {
        println(-1)
    } else {
        println(result)
    }

    println(matrix.joinToString("\n") { row -> row.joinToString() })*/
}

fun dfs2644(matrix: List<List<Int>>, current: Int, visited: Array<Int>) {
    for (next in matrix[current]) {
        if (visited[next] == 0) {
            visited[next] += visited[current] + 1
            dfs2644(matrix, next, visited)
        }
    }
}

/**
9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6
 */