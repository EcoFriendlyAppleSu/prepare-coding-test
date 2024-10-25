package indie.cotae.implementation.leecotae

/**
 * Computation Thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨타가 이해할 수 있도록 표현하는 과정
 * 의식적인 연습
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터의 자료구조를 기술하고 생각해보기
 * 3. 적절한 알고리즘을 생각해보기
 * 4. 2 + 3 을 통해 새로운 데이터를 산출하기
 */

fun main() {
    val input = readln().map { it.toString() }.toList()
    val translatedMap = mutableMapOf(
        "a" to 1,
        "b" to 2,
        "c" to 3,
        "d" to 4,
        "e" to 5,
        "f" to 6,
        "g" to 7,
        "h" to 8,
    )
    val possibleMoveBoard : List<List<Int>> = listOf(
        listOf(2, 1),
        listOf(2, -1),
        listOf(-2, -1),
        listOf(-2, 1),
        listOf(1, 2),
        listOf(1, -2),
        listOf(-1, 2),
        listOf(-1, -2),
    )

    val nightPosition = intArrayOf(translatedMap[input[0]]!!, input[1].toInt())
    var result = 0
    for (index in possibleMoveBoard.indices) {
        val metric = possibleMoveBoard[index]
        val tempX = nightPosition[0] + metric[0]
        val tempY = nightPosition[1] + metric[1]
        if (tempX < 1 || tempX > 8 || tempY < 1 || tempY > 8) {
            continue
        }
        result += 1
    }

    println(result)
}