package indie.abroad.week08

fun main() {
    val n = 2147483645
    println(hammingWeight(n))
}
// 숫자가 Decimal로 주어지더라도 bit 연산에서는 내부적으로 binary 로 변환하여 처리됩니다.
fun hammingWeight(n: Int): Int {
    var count = 0
    var mask = 1 shl 31
    while (mask != 0) {
        if (n and mask != 0) {
            count++
        }
        mask = mask ushr 1
    }
    return count
}

/*
Input: n = 11
Output: 3
Explanation: The input binary string 1011 has a total of three set bits.
*/
