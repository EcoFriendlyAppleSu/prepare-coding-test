package indie.cotae.greedy.leecotae

/**
 * 컴퓨터적 사고란 요구사항을 데이터의 관점으로 바라보며 "변수, 조건, 반복, 타입, 함수" 등을 이용하여 데이터를 컴퓨터가 이해할 수 있는 형태로 표현하는 과정
 * 의식적인 연습
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터 구조를 기술하고 생각해보기
 * 3. 적절한 알고리즘 선택
 * 4. 2 + 3 을 통해 새로운 데이터를 도출하기
 */
fun main() {
    val firstInput = readlnOrNull() ?: ""
    val (aryLength, M, N) = firstInput.split(" ").map { it.toIntOrNull() ?: 0 }
    val secondInput = readlnOrNull() ?: ""
    val secondList = secondInput.split(" ").map { it.toIntOrNull() ?: 0 }.toIntArray()

    // sorted() 함수의 시간 복잡도 최악은 n * log n 입니다.
    val sortedNumbers = secondList.sortedDescending()
    val firstMax = sortedNumbers.getOrNull(0)!!
    val secondMax = sortedNumbers.getOrNull(1)!!

    val divideNumber = M.div(N + 1)
    val spareNumber = M % (N + 1)

    val result = divideNumber * (N.times(firstMax) + secondMax) + (spareNumber.times(firstMax))
    println(result)
}