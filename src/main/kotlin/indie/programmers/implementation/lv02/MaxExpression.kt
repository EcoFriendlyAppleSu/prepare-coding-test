package indie.programmers.implementation.lv02

fun main() {
    val expression ="100-200*300-500+20"
    println(solution04(expression))
}
/*
"100-200*300-500+20"	60420
"50*6-3*2"	300
*/
fun solution04(expression: String): Long {
    val operaterList = listOf("+", "-", "*")
    val totalOperaterList = permutation(operaterList, 3)
    // println(totalOperaterList.joinToString("\n") { it.joinToString(", ")})
    val result = mutableListOf<Long>()
    for(eachOp in totalOperaterList) {
        val temp = calculateExp(expression, eachOp)
        result.add(temp)
    }
    return result.maxOrNull() ?: 0L
}

fun calculateExp(expression: String, opList: List<String>): Long {
    val numberList = mutableListOf<Long>()
    val targetOpList = mutableListOf<String>()
    var temp = ""
    for((index, value) in expression.withIndex()) {
        if(expression[index].isDigit()) {
            temp += expression[index]
        } else {
            targetOpList.add(expression[index].toString())
            numberList.add(temp.toLong())
            temp = ""
        }
    }

    if(temp.isNotEmpty()) {
        numberList.add(temp.toLong())
    }

    for(op in opList) {
        var i = 0
        while(i < targetOpList.size) {
            if(targetOpList[i] == op) {
                val result = when(op) {
                    "*" -> numberList[i] * numberList[i+1]
                    "+" -> numberList[i] + numberList[i+1]
                    "-" -> numberList[i] - numberList[i+1]
                    else -> throw IllegalArgumentException("지원하지 않는 연산자: $op")
                }

                numberList[i] = result
                numberList.removeAt(i+1)
                targetOpList.removeAt(i)
            } else { // 이 부분에서 index plus가 안됨
                i++
            }
        }
    }
    return Math.abs(numberList[0])
}

fun permutation(elements: List<String>, length: Int): List<List<String>> {
    if(length == 0) return listOf(emptyList())
    if(elements.isEmpty()) return emptyList()

    val perms = mutableListOf<List<String>>()
    for(index in elements.indices) {
        val remaining = elements.toMutableList().apply { removeAt(index) }
        for(perm in permutation(remaining, length -1)) {
            perms.add(listOf(elements[index]) + perm)
        }
    }
    return perms
}
