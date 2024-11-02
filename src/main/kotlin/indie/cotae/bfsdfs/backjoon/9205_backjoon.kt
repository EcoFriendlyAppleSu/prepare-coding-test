package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque
import kotlin.system.exitProcess

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨터가 이해할 수 있는 형태로 변경하는 과정
 */

// 다시 도전 필요
fun main() {
    val time = readln().toInt()
    val amountOfStore = readln().toInt()
    val (startX, startY) = readln().split(" ").map { it.toInt() }
    val tempStoreList = mutableListOf<Pair<Int,Int>>()
    for (i in 0 until amountOfStore) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        tempStoreList.add(Pair(x, y))
    }
    val (endX, endY) = readln().split(" ").map { it.toInt() }

    val beer = 50
    val amountOfBeer = 20

    val startPoint = Pair(startX / beer, startY / beer)
    val endPoint = Pair(endX / beer, endY / beer)
    val storeList = tempStoreList.map { Pair(it.first / beer, it.second / beer) }.toMutableList()

    val board = Array(endPoint.first) { IntArray(endPoint.second) { 0 } }
    val visited = Array(endPoint.first) { BooleanArray(endPoint.second) { false } }

    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, -1, 0, 1)

    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.addLast(startPoint)
    visited[startPoint.first][startPoint.second] = true

    while (queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx >= 0 && nx < endPoint.first && ny >= 0 && ny < endPoint.second && !visited[nx][ny]) {
                queue.offerLast(Pair(nx, ny))
                visited[nx][ny] = true
                board[nx][ny] = board[x][y] + 1
            }
        }
    }

    var nextStore = Pair(-1, -1)
    var minValue = Int.MAX_VALUE

    // 편의점을 돌면서 가장 가까운 곳을 찾습니다.
    for (store in storeList) {
        if (board[store.first][store.second] > 20) {
            continue
        } else {
            if (board[store.first][store.second] < minValue) {
                minValue = board[store.first][store.second]
                nextStore = Pair(store.first, store.second)
            }
        }
    }

    if (nextStore == endPoint) {
        println("happy")
        exitProcess(0)
    }
}
