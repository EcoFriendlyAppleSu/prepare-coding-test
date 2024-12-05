package indie.abroad.week01

/**
 * Computation Thinking 이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨터가 이해할 수 있는 형태로 표현하는 과정
 */
fun main() {
    val input = intArrayOf(1,2,3,4)
    println(containsDuplicate(input))
}


fun containsDuplicate(nums: IntArray): Boolean {
    val changeSet = nums.toSet()
    return changeSet.size != nums.size
}
/*

Input: nums = [1,2,3,4]
Output: false

Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
*/
