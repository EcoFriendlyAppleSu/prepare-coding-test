package indie.programmers.greedy

fun main() {
    val n = 5
    val lost = intArrayOf(2, 4)
    val reverse = intArrayOf(1, 3, 5)
    println(solution(n, lost, reverse))
}

fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    val reserveSet = reserve.toMutableSet()
    val lostSet = lost.toMutableSet()

    // 여벌을 가진 학생이 도난당한 경우 처리
    val common = reserveSet.intersect(lostSet)
    reserveSet.removeAll(common)
    lostSet.removeAll(common)

    // 체육복을 빌려줄 수 있는 학생 탐색 (앞번호 → 뒷번호 순서)
    val newLostSet = lostSet.toMutableSet() // 체육복을 빌린 학생을 업데이트하기 위한 새로운 set
    for (student in lostSet.sorted()) {
        when {
            student - 1 in reserveSet -> {
                reserveSet.remove(student - 1) // 앞번호에서 빌림
                newLostSet.remove(student)
            }
            student + 1 in reserveSet -> {
                reserveSet.remove(student + 1) // 뒷번호에서 빌림
                newLostSet.remove(student)
            }
        }
    }

    // 체육복을 입을 수 있는 학생 수 계산
    return n - newLostSet.size
}
/*
5	[2, 4]	[1, 3, 5]	5
5	[2, 4]	[3]	4
*/
