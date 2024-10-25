package indie.cotae.implementation.leecotae

/**
 * computation thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용하여 컴퓨터가 이해할 수 있는 형태로 표현하는 과정
 * 의식적인 연습
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터 자료구조를 기술하고 생각하기
 * 3. 적절한 알고리즘 선택
 * 4. 2 + 3 을 통해 새로운 데이터 산출
 */

fun currentDirection(direction: Int): Int {
    if (direction -1 == -1) {
        return 3
    }
    return direction -1
}

fun main() {
    // 방향을 결정할 때, 3 x 3 의 배열에서 중앙에 기준을 잡고 결정하자
    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)

    val (n, m) = readln().split(" ").map { it.toInt() }
    var (startX, startY, direction) = readln().split(" ").map { it.toInt() }
    val board = mutableListOf<MutableList<Int>>()
    val visitedBoard = Array(n) { IntArray(m) { 0 } }
    // val board = Array(n) { IntArray(m)}

    for (time in 0 until n) {
        val temp = readln().split(" ").map { it.toInt() }.toMutableList()
        board.add(temp)
    }

    visitedBoard[startX][startY] = 1
    var result = 1
    var turningTime = 0

    // 반복문을 어디서부터 진행해야할까? 끝이 존재하는 게임이기 때문에 while문을 사용해 종료 시그널이 나오기 전까지 진행
    while (true) {
        direction = currentDirection(direction)
        val nx = dx[direction]
        val ny = dy[direction]

        var tempX = startX + nx
        var tempY = startY + ny
        // if (tempX in 0..< n && tempY in 0 ..< m) { // 외곽이 항상 바다로 되어 있어 따지지 않아도 됩니다.
        if (visitedBoard[tempX][tempY] == 0 && board[tempX][tempY] == 0) { // 갈 수 있는 곳은 방문하지 않은 곳 그리고 육지이다. 다른 경우의 수는 생각하지 않아도 되는데 그 부분이 잘 안되는거 같다
            visitedBoard[tempX][tempY] = 1
            startX = tempX
            startY = tempY
            result += 1
            turningTime = 0
            continue
        } else {
            turningTime += 1
        }

        if (turningTime == 4) {
            tempX = startX - dx[direction]
            tempY = startY - dy[direction]
            if (board[tempX][tempY] == 0) {
                startX = tempX
                startY = tempY
            } else {
                break
            }
            turningTime = 0
        }
    }


    println(board.joinToString("\n") {row -> row.joinToString()})
    println()
    println(visitedBoard.joinToString("\n") {row -> row.joinToString()})
    println()
    println(result)
}