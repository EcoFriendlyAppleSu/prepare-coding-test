package indie.programmers.implementation

fun main() {
    val n = 22
    val w = 6
    val num = 8
    println(solution(n, w, num))
}

/*n	w	num	result
22	6	8	3*/
fun solution(n: Int, w: Int, num: Int): Int {
    val numRows = (n + w - 1) / w
    val grid = Array(numRows) { IntArray(w) } // 0으로 초기화

    var boxNum = 1
    for(i in 0 until numRows) {
        if(i % 2 == 0) {
            for(j in 0 until w) {
                if(boxNum <= n) {
                    grid[i][j] = boxNum++
                } else {
                    grid[i][j] = 0
                }
            }
        } else {
            for(j in w - 1 downTo 0) {
                if(boxNum <= n) {
                    grid[i][j] = boxNum++
                } else {
                    grid[i][j] = 0
                }
            }
        }
    }

    var targetRow = -1
    var targetCol = -1

    for (i in 0 until numRows) {
        for (j in 0 until w) {
            if (grid[i][j] == num) {
                targetRow = i
                targetCol = j
                break
            }
        }
        if (targetRow != -1) break
    }

    var count = 0
    for(i in targetRow until numRows) {
        if(grid[i][targetCol] != 0) {
            count++
        }
    }
    return count
}
