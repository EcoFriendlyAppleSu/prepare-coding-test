package indie.programmers.implementation.lv01

fun main() {
    val mats = arrayOf(5, 3, 2)
    val park = arrayOf(
        arrayOf("A", "A", "-1", "B", "B", "B", "B", "-1"),
        arrayOf("A", "A", "-1", "B", "B", "B", "B", "-1"),
        arrayOf("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"),
        arrayOf("D", "D", "-1", "-1", "-1", "-1", "E", "-1"),
        arrayOf("D", "D", "-1", "-1", "-1", "-1", "-1", "F"),
        arrayOf("D", "D", "-1", "-1", "-1", "-1", "E", "-1"),
    )
    println(solution(mats, park))
}

fun solution(mats: Array<Int>, park: Array<Array<String>>): Int {
    val sortedMats = mats.sortedDescending()

    for (mat in sortedMats) {
        for (i in 0..park.size - mat) {  // 돗자리 크기를 고려한 범위 설정
            for (j in 0..park[0].size - mat) {  // 돗자리 크기를 고려한 범위 설정
                if (canPlaceMat(i, j, mat, park)) {
                    return mat
                }
            }
        }
    }
    return -1  // 어떤 돗자리도 놓을 수 없는 경우
}

fun canPlaceMat(startI: Int, startJ: Int, matSize: Int, park: Array<Array<String>>): Boolean {
    // 돗자리 범위가 공원을 벗어나는지 확인
    if (startI + matSize > park.size || startJ + matSize > park[0].size) {
        return false
    }

    // 돗자리 영역이 모두 "-1"(빈 공간)인지 확인
    for (i in startI until startI + matSize) {
        for (j in startJ until startJ + matSize) {
            if (park[i][j] != "-1") {
                return false
            }
        }
    }

    return true
}












