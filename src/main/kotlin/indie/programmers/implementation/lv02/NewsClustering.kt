package indie.programmers.implementation.lv02

fun main() {
    val str1 = "aa1+aa2"
    val str2 = "AAAA12"

    println(solution(str1, str2))
}

fun solution(str1: String, str2: String): Int {
    val str1List = mutableListOf<String>()
    val str2List = mutableListOf<String>()

    for(index in 0 until str1.length - 1) {
        val temp = str1.substring(index, index + 2)
        val first = temp[0]
        val second = temp[1]
        if (!first.isLetter() || !second.isLetter()) {
            continue
        } else {
            str1List.add(temp.lowercase())
        }
    }

    for(index in 0 until str2.length - 1) {
        val temp = str2.substring(index, index + 2)
        val first = temp[0]
        val second = temp[1]
        if (!first.isLetter() || !second.isLetter()) {
            continue
        } else {
            str2List.add(temp.lowercase())
        }
    }

    val str1Set = str1List.toSet()
    val str2Set = str2List.toSet()

    val totalSet = str1Set + str2Set
    val oneMinusTwo = str1Set - str2Set
    val twoMinusOne = str2Set - str1Set

    val commonSet = totalSet - (oneMinusTwo + twoMinusOne)

    val commonResult = mutableListOf<String>()
    val totalResult = mutableListOf<String>()

    for(element in commonSet) {
        var temp = 0
        val aValue = str1List.count { it == element }
        val bValue = str2List.count { it == element }
        if(aValue >= bValue) {
            temp = bValue
        } else {
            temp = aValue
        }
        for(i in 0 until temp) {
            commonResult.add(element)
        }
    }

    for(element in totalSet) {
        var temp = 0
        val aValue = str1List.count { it == element }
        val bValue = str2List.count { it == element }
        if (aValue >= bValue) {
            temp = aValue
        } else {
            temp = bValue
        }
        for(i in 0 until temp) {
            totalResult.add(element)
        }
    }

    if (commonResult.isEmpty() && totalResult.isEmpty()) return 1 * 65536

    val result = commonResult.size.toDouble() / totalResult.size
    return Math.floor(result * 65536).toInt()
}

/*
aa1+aa2	AAAA12	43690
*/
