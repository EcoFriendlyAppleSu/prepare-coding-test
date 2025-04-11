package indie.programmers.implementation.lv02

fun main() {
    val orders = arrayOf("XYZ", "XWY", "WXA")
    val course = intArrayOf(2, 3, 4)
    println(solution(orders, course).joinToString())
}

fun solution(orders: Array<String>, course: IntArray): Array<String> {
    val answer = mutableListOf<String>()

    for (num in course) { // 코스요리 메뉴 개수
        val menuCombinations = mutableListOf<String>()

        for (order in orders) { // 주문 현황
            val sortedOrder = order.toCharArray().sorted().joinToString("")
            menuCombinations.addAll(generateCourse(sortedOrder, num))
        }
        println(menuCombinations)

        // 각 메뉴 조합의 등장 횟수 계산
        val countMap = mutableMapOf<String, Int>()
        for (menu in menuCombinations) {
            countMap[menu] = countMap.getOrDefault(menu, 0) + 1
        }
        println(countMap)

        // 최소 2번 이상 등장하고, 가장 많이 등장한 메뉴 조합 찾기
        if (countMap.isNotEmpty()) {
            val maxCount = countMap.values.maxOrNull() ?: 0
            if (maxCount >= 2) {
                for ((key, value) in countMap) {
                    if (value == maxCount) {
                        answer.add(key)
                    }
                }
            }
        }
    }

    return answer.sorted().toTypedArray()
}

fun generateCourse(str: String, length: Int): List<String> {
    val result = mutableListOf<String>()

    fun combine(startIndex: Int, current: String) {
        if (current.length == length) {
            result.add(current)
            return
        }

        // String, Char 덧셈 시 합쳐진 문자열 생성 type coercion
        for (i in startIndex until str.length) {
            combine(i + 1, current + str[i])
        }
    }

    combine(0, "")
    return result
}

/*
["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
	[2,3,4]
		["AC", "ACDE", "BCFG", "CDE"]
*/
