package indie.cotae.greedy.backjoon

fun main() {
    val n = readln().toInt()
    val list = mutableListOf<Int>()
    for(i in 0 until n) {
        list.add(readln().toInt())
    }
    val sorted = list.sorted()
    var currentWeight = 0
    for(i in 0 until sorted.size) {
        val weight = sorted[i] * (n -i)
        currentWeight = maxOf(currentWeight, weight)
    }
    println(currentWeight)
}

/*
2
10
15
*/
