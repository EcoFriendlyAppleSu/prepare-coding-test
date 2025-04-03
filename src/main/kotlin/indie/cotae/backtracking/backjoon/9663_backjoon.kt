package indie.cotae.backtracking.backjoon


fun main() {
    val n = readln().toInt()
    println(solution(n))
}

/**
 * n by n chess board를 필요하지 않습니다.
 * 2d에서 queens[i] = j 를 통해 queen을 둔 것을 표현할 수 있습니다.
 * "i 번째 행의 j 번째 열에 queen이 존재한다."
 */
fun solution(n: Int): Int {
    var count = 0
    val queens = IntArray(n) { -1 } // 배열 세팅 n by n

    // row, col에 queen을 배치할 수 있는지 판단하는 함수
    fun isSafe(row: Int, col: Int): Boolean { // 매개변수로 입력된 row, col 위치에 queen을 둘 수 있는지 판별합니다.
        for(preRow in 0 until row) { // 배열을 통해 이전 row들에서 queen의 col 값을 가져옵니다.
            val preCol = queens[preRow] // 이전 col 값을 가져오고

            if(preCol == col) { // 현재 위치하고 있는 column에 queen이 위치해 있다면 ex, queen[0] = 3 / queen[1] = 3
                return false // 놓을 수 없다고 판단하여 false를 반환합니다.
            }

            // 두 점의 차가 y = x, y = -x 위에 있다면 즉, 대각선 위에 존재하게 된다면 queen을 위치시킬 수 없습니다.
            // 같은 대각선 상에 위치해 있다는 것은 두 점의 차이가 동일함을 의미하기 때문에 row, col 차이를 구하고 동일한지 판별합니다.
            val rowDiff = row - preRow
            val colDiff = Math.abs(col - preCol)

            // 같은 대각선 상에 존재한다면
            if (rowDiff == colDiff) {
                return false // queen을 놓을 수 없다고 판단하여 false를 반환합니다.
            }
        }
        return true
    }

    fun backtrack(row: Int, n: Int) {
        if(row == n) { // row == n 이라면 맨 마지막 줄까지 queen을 놓은 것임으로
            count += 1 // 경우의 수를 하나 추가하고
            return // 다음 depth가 없으므로 escape합니다.
        }

        for(col in 0 until n) {
            if(isSafe(row, col)) {
                queens[row] = col // queen을 두고
                backtrack(row + 1, n) // 다음 행으로 진행
                queens[row] = -1
            }
        }
    }

    backtrack(0, n)
    return count
}
