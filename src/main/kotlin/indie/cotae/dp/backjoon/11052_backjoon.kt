package indie.cotae.dp.backjoon

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt() // 구매하려는 카드 개수

    // 카드팩 가격 입력 (1-indexed를 0-indexed로 변환)
    val prices = IntArray(n + 1)
    for (i in 1..n) {
        prices[i] = scanner.nextInt()
    }

    val dp = IntArray(n+1)

    for(i in 1..n) {
        for(j in 1..i) {
            dp[i] = maxOf(dp[i], dp[i-j] + prices[j])
        }
    }
    print(dp[n])
}

/*
4
1 5 6 7*/
