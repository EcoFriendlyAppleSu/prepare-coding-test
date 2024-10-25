package indie.cotae.greedy.leecotae

/**
 * 컴퓨터적 사고란 사용자 요구사항을 데이터 관점으로 바라보며 "변수, 타입, 조건, 반복, 함수" 등을 이용하여 데이터를 컴퓨터가 이해할 수 있는 형태로 표현하는 과정
 * 의식적으로 연습하기
 * 1. 요구사항을 데이터 관점으로 바라보기
 * 2. 데이터의 구조를 기술하고 생각해보기
 * 3. 적절한 알고리즘 생각하기
 * 4. 2 + 3 을 통해 새로운 데이터를 산출하기
 */
fun main() {
    val firstInput = readlnOrNull() ?: ""
    val (N, M) = firstInput.split(" ").map { it.toIntOrNull() }
    val matrix = mutableListOf<MutableList<Int>>()

    for (i in 0 until N!!) {
        val input = readlnOrNull() ?: ""
        val row = input.split(" ").map { it.toIntOrNull() ?: 0 }.toMutableList()
        matrix.add(row)
    }

    val candidateList = mutableListOf<Int>()

    for (i in 0 until matrix.size) {
        candidateList.add(matrix[i].minOrNull()!!)
    }

    println(candidateList.maxOrNull())
}

// matrix 필요 유무를 다시 한 번 생각해보자
