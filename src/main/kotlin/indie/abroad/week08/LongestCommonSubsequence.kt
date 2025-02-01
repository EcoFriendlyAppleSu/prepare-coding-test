package indie.abroad.week08

fun main() {
    val text1 = "abcde"
    val text2 = "ace"
    println(longestCommonSubsequence(text1, text2))
}

fun longestCommonSubsequence(text1: String, text2: String): Int {
    val n = text1.length
    val m = text2.length
    val dp = Array(n + 1) { IntArray(m + 1) }

    for (i in 1..n) {
        for (j in 1..m) {
            if (text1[i - 1] == text2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }
    return dp[n][m]
}

/*
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
*/
