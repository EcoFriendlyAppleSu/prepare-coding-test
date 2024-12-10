package indie.abroad.week01

/**
 * 주어진 숫자들에서 빈도 수가 가장 큰 k 개의 숫자를 구하는 문제
 * map에서 List로 변경해 key 혹은 value에 따라 정렬을 수행할 수 있습니다. List<Pair<Int, Int>> 로 바뀜
 *
 */
fun main() {
    val nums = intArrayOf(1,1,1,2,2,3)
    val k = 2
    println(topKFrequent(nums, k))
}

fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = mutableMapOf<Int, Int>()

    for (i in nums.indices) {
        if (map.containsKey(nums[i])) {
            val value = map[nums[i]]!!
            map[nums[i]] = value + 1
        } else {
            map.putIfAbsent(nums[i], 1)
        }
    }
    val sortedMap = map.toList().sortedByDescending { it.second }.toMap()
    return sortedMap.entries.take(k).map { it.key }.toIntArray()
}

fun topKFrequent01(nums: IntArray, k: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for(num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }

    // count List 초기화
    // map의 value는 nums Size를 넘을 수 없음.
    val countList = Array(nums.size + 1) { mutableListOf<Int>() }
    for ((key, value) in map) {
        countList[value].add(key)
    }

    val result = mutableListOf<Int>()
    for (i in countList.size - 1 downTo 0) {
        for (num in countList[i]) {
            result.add(num)
            if (result.size == k) {
                return result.toIntArray()
            }
        }
    }
    return result.toIntArray()
}
