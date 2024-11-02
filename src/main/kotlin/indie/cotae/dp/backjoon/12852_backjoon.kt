package indie.cotae.dp.backjoon

/**
 * 다시 도전 필요
 */
fun main() {
    val N = readln().toInt()
    val mem = IntArray(N + 1) { 0 } // 연산 횟수 메모이제이션
    val path = Array(N + 1) { "" }   // 최단 경로
    path[1] = "1"

    for (idx in 2..N) {
        mem[idx] = mem[idx - 1] + 1
        var prev = idx - 1

        if (idx % 3 == 0 && mem[idx / 3] + 1 < mem[idx]) {
            mem[idx] = mem[idx / 3] + 1
            prev = idx / 3
        }

        if (idx % 2 == 0 && mem[idx / 2] + 1 < mem[idx]) {
            mem[idx] = mem[idx / 2] + 1
            prev = idx / 2
        }

        path[idx] = "$idx ${path[prev]}"
    }

    println(mem[N])
    println(path[N])
    println(path.joinToString("\n"))
}