package indie.programmers.implementation

fun main() {
    val wallet = arrayOf(50, 50)
    val bill = arrayOf(100, 241)
    println(solution(wallet, bill))
}
// [50, 50]	[100, 241]	4

fun solution(wallet: Array<Int>, bill: Array<Int>): Int {
    var answer = 0
    var valuePair = Pair(bill[0], bill[1])
    while(true) {
        if((valuePair.first <= wallet[0] && valuePair.second <= wallet[1]) ||
            (valuePair.first <= wallet[1] && valuePair.second <= wallet[0])) {
            break
        } else {
            var firstValue = valuePair.first
            var secondValue = valuePair.second
            if(firstValue >= secondValue) {
                firstValue /= 2
            } else {
                secondValue /= 2
            }
            valuePair = Pair(firstValue, secondValue)
            answer += 1
        }
    }
    return answer
}

/*
wallet	bill	result
[30, 15]	[26, 17]	1
*/
