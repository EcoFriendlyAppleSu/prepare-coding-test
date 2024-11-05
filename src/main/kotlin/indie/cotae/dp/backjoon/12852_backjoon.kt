package indie.cotae.dp.backjoon

fun main() {
    val n = readln().toInt()
    val visited = IntArray(n + 1)
    val dp = MutableList(n + 1) { 0 }

    dp[1] = 0
    visited[1] = 1

    for (i in 2..n) {
        dp[i] = dp[i-1] + 1
        visited[i] = i - 1

        if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
            dp[i] = dp[i / 2] + 1
            visited[i] = i / 2
        }

        if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
            dp[i] = dp[i / 3] + 1
            visited[i] = i / 3
        }
    }

    println(dp[n])
    if (n != 0) {
        print("$n ")
    }

    var time = n
    while (time != 1) {
        print("${visited[time]} ")
        time = visited[time]
    }
}