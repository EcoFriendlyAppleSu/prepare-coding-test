package indie.abroad.week02

fun main() {
    println(climbStairs(45))
}

fun climbStairs(n: Int): Int {
    val step = IntArray(n+1)

    if (n == 1) {
        return 1
    }
    step[1] = 1
    step[2] = 2

    for (i in 3 until step.size) {
        step[i] = step[i-1] + step[i-2]
    }

    return step[n]
}
