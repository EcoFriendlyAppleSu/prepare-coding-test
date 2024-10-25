package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque
import kotlin.math.max
import kotlin.system.exitProcess

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수를 통해 컴퓨터가 이해할 수 있는 형태로 만드는 과정
 */

var globalH = 0
var globalR = 0
var globalC = 0
val dx = listOf(0,0,0,0,1,-1)
val dy = listOf(0,0,1,-1,0,0)
val dz = listOf(1,-1,0,0,0,0)

fun main() {
    val (m, n, h) = readln().split(" ").map { it.toInt() }
    val board = mutableListOf<MutableList<MutableList<Int>>>()
    val visited = MutableList(h) { MutableList(n) { MutableList(m) { false } } }

    for (i in 0 until h) {
        val eachFloor = mutableListOf<MutableList<Int>>()
        for (j in 0 until n) {
            val eachRow = readln().split(" ").map { it.toInt() }.toMutableList()
            eachFloor.add(eachRow)
        }
        board.add(eachFloor)
    }

    globalC = m
    globalR = n
    globalH = h

    val queue = ArrayDeque<Triple<Int, Int, Int>>()
    var answer = 0

    for (i in 0 until h) {
        for (j in 0 until n) {
            for (k in 0 until m) {
                if (board[i][j][k] == 1 && !visited[i][j][k]) {
                    queue.offerLast(Triple(i, j, k))
                    visited[i][j][k] = true
                }
            }
        }
    }

    bfs7569(board, visited, queue)

    for (i in board) {
        for (j in i) {
            for (k in j) {
                if (k == 0) {
                    println(-1)
                    exitProcess(0)
                }
            }
            answer = max(answer, j.max())
        }
    }

    println(answer -1)
}

fun bfs7569(
    board: MutableList<MutableList<MutableList<Int>>>,
    visited: MutableList<MutableList<MutableList<Boolean>>>,
    queue: ArrayDeque<Triple<Int, Int, Int>>
) {

    while (queue.isNotEmpty()) {
        val popElement = queue.removeFirst()

        for (i in 0 until 6) {
            val nz = dz[i] + popElement.first
            val nx = dx[i] + popElement.second
            val ny = dy[i] + popElement.third

            if (nz < 0 || nz >= globalH || nx < 0 || nx >= globalR || ny < 0 || ny >= globalC) {
                continue
            }

            if (board[nz][nx][ny] == 0 && !visited[nz][nx][ny]) {
                queue.offerLast(Triple(nz, nx, ny))
                board[nz][nx][ny] = board[popElement.first][popElement.second][popElement.third] + 1
                visited[nz][nx][ny] = true
            }
        }
    }
}


/*
5 3 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 1 0 0
0 0 0 0 0
*/
