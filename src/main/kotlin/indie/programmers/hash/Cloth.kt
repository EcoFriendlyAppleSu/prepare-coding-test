package indie.programmers.hash

import kotlin.Array

fun main() {
    val clothes = arrayOf(
        arrayOf("yellow_hat", "headgear"),
        arrayOf("blue_sunglasses", "eyewear"),
        arrayOf("green_turban", "headgear"),
    )

    println(solution(clothes))
}

fun solution(clothes: Array<Array<String>>): Int {
    var answer = 1

    val map = mutableMapOf<String, Int>()

    for (cloth in clothes) {
        map[cloth[1]] = map.getOrDefault(cloth[1], 0) + 1
    }

    // 각 카테고리에서 선택하지 않은 것을 포함한 값이 value + 1
    for (value in map.values) {
        answer *= (value + 1)
    }

    // 모든 카테고리 옷을 입지 않은 조건을 빼줍니다.
    return answer -1
}
