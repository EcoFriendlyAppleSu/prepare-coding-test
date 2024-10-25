package indie.cotae.bfsdfs.backjoon

import java.util.ArrayDeque

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val visited = BooleanArray(100001) { false }

    var count = 1
    var minimumMeetTime = 0
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.offerLast(Pair(n, 0))

    while (queue.isNotEmpty()) {
        val (current, time) = queue.removeFirst()
        visited[current] = true

        if (current == k && minimumMeetTime != 0 && minimumMeetTime == time) {
            count += 1
        }

        if (current == k && minimumMeetTime == 0) {
            minimumMeetTime = time
        }

        for (next in listOf(current - 1, current + 1, current * 2)) {
            if (next in 0..100000 && !visited[next]) {
                queue.offerLast(Pair(next, time +1))
            }
        }
    }
    println(minimumMeetTime)
    println(count)
}