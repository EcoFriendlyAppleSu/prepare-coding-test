package indie.programmers.implementation.lv02

import kotlin.math.sqrt

fun main() {
    println(solution(437674, 3))
}

fun solution(n: Int, k: Int): Int {
    var answer: Int = -1
    val numberList = generateJinsuString(n, k)
    val beforeCheckList = mutableListOf<Long>()
    val temp = findZeroIndexList(numberList)
    for(i in 0 until temp.size -1) {
        var a = ""
        for(j in temp[i] until temp[i+1]) {
            a += numberList[j].toString()
        }
        beforeCheckList.add(a.toLong())
    }
    return beforeCheckList.count { isPrime(it) }
}

fun findZeroIndexList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    result.add(0) // 첫 인덱스는 포함
    for((index, value) in list.withIndex()) {
        if(value == 0) {
            result.add(index)
        }
    }
    result.add(list.size) // 마지막 인덱스도 포함
    return result
}

fun generateJinsuString(number: Int, jinsu: Int): List<Int> {
    val result = mutableListOf<Int>()
    var currentNumber = number

    while(currentNumber != 0) {
        result.add(currentNumber % jinsu)
        currentNumber /= jinsu
    }
    return result.reversed().toList()
}
fun isPrime(number: Long): Boolean {
    if(number <= 1) return false
    if(number == 2L) return true
    if(number % 2 == 0L) return false // 짝수 거르기

    val sqrt = Math.sqrt(number.toDouble()).toLong()
    for(value in 3L..sqrt step 2) {
        if(number % value == 0L) return false
    }
    return true
}


/*
[2, 1, 1, 0, 2, 0, 1, 0, 1, 0, 1, 1]*/
