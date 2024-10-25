package indie.cotae.greedy.leecotae

/**
 * 컴퓨터적 사고란 사용자의 요구사항을 데이터의 관점에서 바라보고 "변수, 타입, 조건, 반복, 함수" 등을 이용하여 데이터를 컴퓨터가 이해할 수 있는 형태로 표현하는 과정
 * 🍎 의식적으로 컴퓨터적 사고하기
 * 1. 요구사항을 데이터의 관점에서 바라보기
 * 2. 데이터의 구조를 기술하고 생각해보기
 * 3. 적절한 알고리즘 선택
 * 4. 2 + 3 연결해 새로운 데이터를 도출하기
 */
fun main() {
    print("Enter payback money : ")
    var paybackMoney = readlnOrNull()?.toIntOrNull()

    // 첫 번째 풀이 -> 나열식
    /*val fiveHundred = 500
    val oneHundred = 100
    val fifty = 50
    val ten = 10
    var result = 0 // mutable var

    result += paybackMoney!!.div(fiveHundred)
    paybackMoney %= fiveHundred

    result += paybackMoney.div(oneHundred)
    paybackMoney %= oneHundred

    result += paybackMoney.div(fifty)
    paybackMoney %= fifty

    result += paybackMoney.div(ten)
    paybackMoney %= ten

    print(message = "result is : $result")*/

    // 두 번째 풀이 -> 데이터 구조를 Array로 잡고 반복문을 통해 문제 해결. 요구사항에서 주어진 데이터는 불변하기 때문에 불변인 Array에 값을 할당함.
    val coins = intArrayOf(500, 100, 50, 10)
    var result = 0
    for (coin in coins) {
        result += paybackMoney!!.div(coin)
        paybackMoney %= coin
    }
    print(message = "result is : $result")
}