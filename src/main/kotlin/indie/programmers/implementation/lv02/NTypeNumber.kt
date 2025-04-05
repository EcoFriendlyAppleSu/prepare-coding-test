package indie.programmers.implementation.lv02

fun main() {
    val n = 16
    val t = 16
    val m = 2
    val p = 1
    println(solution(n,t,m,p))
}
fun solution(n: Int, t: Int, m: Int, p: Int): String {
    val needSize = t * m // 필요한 문자열의 길이
    var numString = ""
    var currentNumber = 0
    while(numString.length < needSize) {
        val str = makeJinsuString(n, currentNumber)
        numString += str
        currentNumber++
    }

    var result = ""
    for((index, value) in numString.withIndex()) {
        if((index % m) == p -1) {
            result += value
        }
        if(result.length == t) return result
    }
    return result
}

fun makeJinsuString(n: Int, currentNumber: Int): String {
    val overTenDigitMap = mapOf(
        10 to "A",
        11 to "B",
        12 to "C",
        13 to "D",
        14 to "E",
        15 to "F",
    )
    val tempList = mutableListOf<Int>()
    var number = currentNumber
    while(true) {
        tempList.add(number % n)
        if (number / n == 0) {
            break
        }
        number /= n
    }
    val result = mutableListOf<String>()
    for((index, value) in tempList.withIndex()) {
        if (overTenDigitMap.contains(value)) {
            result.add(overTenDigitMap[value]!!)
        } else {
            result.add(tempList[index].toString())
        }
    }
    result.reverse()
    return result.joinToString("")
}

/*
16	16	2	1	"02468ACE11111111"
*/
