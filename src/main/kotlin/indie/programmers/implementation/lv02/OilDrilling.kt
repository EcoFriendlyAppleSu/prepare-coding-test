package indie.programmers.implementation.lv02

fun main() {
    val land = arrayOf(
        intArrayOf(0, 0, 0, 1, 1, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 1, 1, 0, 0),
        intArrayOf(1, 1, 0, 0, 0, 1, 1, 0),
        intArrayOf(1, 1, 1, 0, 0, 0, 0, 0),
        intArrayOf(1, 1, 1, 0, 0, 0, 1, 1),
    )
    println(solutionY(land))

    val solution = Solution1().solution(land)

}

fun solutionY(land: Array<IntArray>): Int {
    val col = land[0].size
    val result = mutableListOf<Int>()
    for(i in 0 until col) {
        val temp = drilling(i, land)
        result.add(temp)
    }
    return result.maxOrNull() ?: 0
}

fun drilling(col: Int, land: Array<IntArray>): Int {
    val visitBoard = Array(land.size) { BooleanArray(land[0].size) { false } }
    var count = 0
    // land[x][col]을 진행해 land가 hit이라면 변경
    for(row in 0 until land.size) {
        if(visitBoard[row][col] == false && land[row][col] == 1) {
            count += bfs(row, col, visitBoard, land)
        }
    }
    return count
}
// dfs로 해결하는 방법을 찾긴해야겠는데
fun bfs(x: Int, y: Int, visitBoard: Array<BooleanArray>, land: Array<IntArray>): Int {
    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, 1, 0, -1)
    var count = 0
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    visitBoard[x][y] = true
    count += 1

    while(queue.isNotEmpty()) {
        val (row, col) = queue.removeFirst()

        for(i in 0 until 4) {
            val nx = row + dx[i]
            val ny = col + dy[i]
            if (nx >= 0 && nx < land.size && ny >= 0 && ny < land[0].size && visitBoard[nx][ny] == false && land[nx][ny] == 1) {
                queue.add(Pair(nx, ny))
                visitBoard[nx][ny] = true
                count++
            }
        }
    }
    return count
}

// 최적화 한 후 답안
class Solution1 {
    fun solution(land: Array<IntArray>): Int {
        val rows = land.size
        val cols = land[0].size

        // 석유 덩어리(oil blob)마다 고유 ID를 부여
        val oilBlobIds = Array(rows) { IntArray(cols) }
        // 각 석유 덩어리의 크기를 저장
        val oilBlobSizes = mutableMapOf<Int, Int>()
        // 각 열에 있는 석유 덩어리 ID들을 저장 (중복 없이)
        val colToOilBlobs = List(cols) { mutableSetOf<Int>() }

        var nextBlobId = 1

        // 전체 땅을 한 번만 BFS로 탐색하여 모든 석유 덩어리 식별
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (land[i][j] == 1 && oilBlobIds[i][j] == 0) {
                    // 새 석유 덩어리 발견, BFS로 크기와 포함된 열 정보 수집
                    val size = bfs(i, j, land, oilBlobIds, nextBlobId, colToOilBlobs)
                    oilBlobSizes[nextBlobId] = size
                    nextBlobId++
                }
            }
            println("Oil Blob Size is ${oilBlobSizes}")
        }

        // 각 열에서 얻을 수 있는 최대 석유량 계산
        var maxOil = 0
        for (col in 0 until cols) {
            val totalOil = colToOilBlobs[col].sumOf { blobId -> oilBlobSizes[blobId] ?: 0 }
            maxOil = maxOf(maxOil, totalOil)
        }

        return maxOil
    }

    private fun bfs(
        startRow: Int,
        startCol: Int,
        land: Array<IntArray>,
        oilBlobIds: Array<IntArray>,
        blobId: Int,
        colToOilBlobs: List<MutableSet<Int>>
    ): Int {
        val rows = land.size
        val cols = land[0].size
        val dx = intArrayOf(0, 1, 0, -1)
        val dy = intArrayOf(1, 0, -1, 0)

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(startRow, startCol))
        oilBlobIds[startRow][startCol] = blobId
        colToOilBlobs[startCol].add(blobId)

        var size = 1

        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()

            for (i in 0 until 4) {
                val newRow = row + dx[i]
                val newCol = col + dy[i]

                if (newRow in 0 until rows && newCol in 0 until cols
                    && land[newRow][newCol] == 1 && oilBlobIds[newRow][newCol] == 0) {
                    queue.add(Pair(newRow, newCol))
                    oilBlobIds[newRow][newCol] = blobId
                    colToOilBlobs[newCol].add(blobId)
                    size++
                }
            }
        }

        return size
    }
}

/*
[[0, 0, 0, 1, 1, 1, 0, 0],
[0, 0, 0, 0, 1, 1, 0, 0],
[1, 1, 0, 0, 0, 1, 1, 0],
[1, 1, 1, 0, 0, 0, 0, 0],
[1, 1, 1, 0, 0, 0, 1, 1]]
*/
