package indie.cotae.greedy.backjoon

/**
 * computation thinking 은 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 표현하는 과정
 * 의식적으로 생각
 * 1. 요구사항을 데이터의 관점으로 바라보기
 * 2. 표현할 데이터 구조 생각해보기
 * 3. 적절한 알고리즘 선택
 * 4. 2 + 3 을 통해 새로운 데이터를 추출
 */
fun main() {

    val n = readln().toIntOrNull()!!
    val costList = readln().split(" ").map { it.toIntOrNull() ?: 0 }.toList().sorted()

    var result = 0
    var temp = 0

    for (cost in costList) {
        temp += cost
        result += temp
    }
    println(result)
}