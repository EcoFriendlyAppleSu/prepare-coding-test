package indie.programmers.implementation.lv02

fun main() {
    val orders = arrayOf("XYZ", "XWY", "WXA")
    val course = intArrayOf(2, 3, 4)
    println(solution(orders, course).joinToString())
}

// ["XYZ", "XWY", "WXA"]	[2,3,4]

fun solution(orders: Array<String>, course: IntArray): Array<String> {
    val result = mutableListOf<String>()

    // ABCFG에서 조합을 구해가지고
    for(eachCourse in course) {
        val tempMap = mutableMapOf<Int, List<String>>()
        val menuMap = mutableMapOf<String, Int>()
        // 새로운 메뉴 채움
        for((index, order) in orders.withIndex()) {
            var generateCourse = emptyList<String>()
            if(order.length < eachCourse) continue // 만들 수 없는 코드라면 pass
            generateCourse = generateCourse(order, eachCourse)
            tempMap[index] = tempMap.getOrPut(index) { generateCourse }
        }
        println(tempMap)

        for(values in tempMap.values) {
            for(value in values) {
                menuMap[value] = menuMap.getOrPut(value) { 0 } + 1
            }
        }
        println(menuMap)
        val max = menuMap.values.maxOrNull() ?: 0
        val filter = menuMap.filter { (key, value) ->
            value == max
        }.keys
        result.addAll(filter)
    }

    return result.sorted().toTypedArray()
}

fun generateCourse(str: String, length: Int): List<String> {
    val charArray = str.toCharArray()
    val result = mutableListOf<String>()

    fun combine(element: List<Char>, startIndex: Int, length: Int) {
        if(element.size == length) {
            result.add(element.joinToString(""))
            return
        }

        for(index in startIndex until charArray.size) {
            combine(element + listOf(charArray[index]), index +1, length)
        }
    }

    combine(emptyList(), 0, length)
    return result
}

/*
["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
	[2,3,4]
		["AC", "ACDE", "BCFG", "CDE"]
*/
