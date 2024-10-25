package indie.cotae.greedy.backjoon

/**
 * Computation Thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 데이터를 컴퓨터가 이해하는 방식으로 표현하는 과정
 * 의식적인 연습
 * 1. 요구사항을 데이터의 관점으로 바라보기
 * 2. 데이터 구조를 기술하고 생각해보기
 * 3. 적절한 알고리즘 선택
 * 4. 2 + 3 을 통한 새로운 데이터 도출
 */
fun main() {
    var (n, k) = readln().split(" ").map { it.toInt() }
    val coinList = IntArray(n) {
        readln().toIntOrNull() ?: 0
    }
    var startIndex = 0
    var result = 0

    // 데이터를 다루는 방식에서 민감하게 여부를 따져야합니다.
    for (i in 0 until coinList.size) {
        if ((k / coinList[i]) > 0) {
            startIndex = i
        }
    }

    for (i in startIndex downTo 0) {
        result += k / coinList[i]
        k %= coinList[i]
        if (k == 0) {
            break
        }
    }
    println(result)
}

/*
fun main() {
    var (n, k) = readln().split(" ").map { it.toInt() }
    val coinList = IntArray(n) {
        readln().toIntOrNull() ?: 0
    }
    var startIndex = 0
    var result = 0

    for (i in n - 1 downTo 0) {
        result += k / coinList[i]
        k %= coinList[i]
    }
    println(result)
}*/
