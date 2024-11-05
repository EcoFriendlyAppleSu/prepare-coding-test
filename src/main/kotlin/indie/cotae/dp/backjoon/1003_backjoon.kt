package indie.cotae.dp.backjoon

fun main() {
    /*val time = readln().toInt()
    for (i in 0 until time) {
        val n = readln().toInt()
        val visited = IntArray(n + 1)

        if (n == 0) {
            println("1 0")
            continue
        }
        fibo1003(n, visited)
        println("${visited[0]} ${visited[1]}")
    }*/

    /*val time = readln().toInt()
    for (i in 0 until time) {
        val n = readln().toInt()
        val visited = IntArray(n + 1)

        if (n == 0) {
            println("0 1")
            continue
        } else if (n == 1) {
            println("1 0")
            continue
        }

        visited[0] = 0
        visited[1] = 1
        visited[2] = 1

        for (j in 3..n) {
            visited[j] = visited[j-1] + visited[j-2]
        }
        println("${visited[n-1]} ${visited[n]}")
    }*/

    val time = readln().toInt()
    for (i in 0 until time) {
        val n = readln().toInt()
        var zero = 1
        var one = 0
        for (j in 0 until n) {
            val temp = zero
            zero = one
            one += temp
        }
        println("$zero $one")
    }

}

// 시간 초과 발생
fun fibo1003(number: Int, visited: IntArray): Int {
    if (number == 0) {
        visited[0] += 1
        return 0
    } else if (number == 1) {
        visited[1] += 1
        return 1
    } else {
        val temp = fibo1003(number - 1, visited) + fibo1003(number - 2, visited)
        return temp
    }
}