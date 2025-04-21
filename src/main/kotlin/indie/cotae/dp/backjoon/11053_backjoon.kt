package indie.cotae.dp.backjoon

fun main() {
    val n = readln().toInt()
    val list = readln().split(" ").map { it.toInt() }
    val dp = IntArray(n) { 1 }  // 모든 위치에서 최소 길이는 1

    for(i in 1 until n) {  // 1부터 시작 (0번 요소는 이미 1로 설정됨)
        for(j in 0 until i) {
            if(list[i] > list[j]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
    }

    println(dp.maxOrNull() ?: 1)  // 배열이 비어있을 경우 기본값 1
}

/*
6
10 20 10 30 20 50
*/
