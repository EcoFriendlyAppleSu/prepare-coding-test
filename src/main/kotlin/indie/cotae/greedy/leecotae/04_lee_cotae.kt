package indie.cotae.greedy.leecotae

/**
 * computation thinking 컴퓨터적 사고란 요구사항을 데이터의 관점으로 바라보며 변수, 타입, 조건, 반속, 함수 등을 통해 컴퓨터가 이해할 수 있는 형태로 표현하는 방법
 *
 * 의식적인 연습
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터 구조를 기술하고 생각해보기
 * 3. 적절한 알고리즘 설정
 * 4. 2+3 을 통해 새로운 데이터 도출
 */
fun main() {
    // 콘솔에서 입력을 받고 공백을 기준으로 분리
    var (n, k) = readln().split(" ").map { it.toInt() }
    var result = 0

    while (true) {
        if (n == 1) {
            break
        }
        if ((n % k) == 0) {
            n /= k
            result += 1
        } else {
            n -= 1
            result += 1
        }
    }
    println(result)
}