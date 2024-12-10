package indie.programmers.hash

// 조합을 사용하면 조합을 구성할 때, 중복이 포함되어 시간 초과 발생
fun main() {
    val clothes: Array<Array<String>> = arrayOf(
        arrayOf("yellow_hat", "headgear"),
        arrayOf("blue_sunglasses", "eyewear"),
        arrayOf("green_turban", "headgear"),
    )

    var answer = 0

    val map = mutableMapOf<String, Int>()
    for (cloth in clothes) {
        map[cloth[1]] = map.getOrDefault(cloth[1],0) + 1
    }

    val categoryCloth = map.keys.toList()

    for (i in 0 ..categoryCloth.size) {
        val eachCombination = combination(categoryCloth, i)
        var temp = 0
        for (cloth in eachCombination) { // [[headgear], [eyewear]]
            var tempClothValue = 1
            for (eachCloth in cloth) {
                val value = map[eachCloth]!!
                tempClothValue *= value
            }
            temp += tempClothValue
        }
        answer += temp
    }

    println(answer)
}

fun combination(categoryClothList: List<String>, k: Int): List<List<String>> {
    val result = mutableListOf<List<String>>()
    val maxSize = categoryClothList.size
    val indices = IntArray(k)
    for (i in 0 until k) {
        indices[i] = i
    }

    while (indices[k - 1] < maxSize) {
        result.add(indices.map { categoryClothList[it] })
        var i = k - 1
        while (i >= 0 && indices[i] == i + maxSize - k) {
            i--
        }
        if (i >= 0) {
            indices[i]++
            for (j in i + 1 until k) {
                indices[j] = indices[j-1]+1
            }
        } else {
            break
        }
    }
    return result
}

// 풀이
fun programmersSolution(clothes: Array<Array<String>>): Int {
    val map = mutableMapOf<String, Int>()

    // 의상 카테고리별 의상 수를 셈
    for (cloth in clothes) {
        map[cloth[1]] = map.getOrDefault(cloth[1], 0) + 1
    }

    // 각 카테고리에서 하나 이상의 의상을 선택하는 경우의 수를 계산
    var answer = 1
    for (count in map.values) {
        answer *= (count + 1)
    }

    // "모두 선택하지 않는 경우"를 제외해야 하므로 1을 빼기
    return answer - 1
}
