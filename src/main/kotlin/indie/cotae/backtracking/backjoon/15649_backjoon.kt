package indie.cotae.backtracking.backjoon

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val list = (1..n).toList()
    val result = permutation(list, m)

    println(result.joinToString("\n") { it.joinToString(" ")})

}

fun permutation(list: List<Int>, length: Int): List<List<Int>> {
    if(length == 0) return listOf(emptyList())
    if(list.isEmpty()) return emptyList()
    val perms = mutableListOf<List<Int>>()
    for(index in list.indices) {
        val remaining = list.toMutableList().apply { removeAt(index) }
        for(perm in permutation(remaining, length -1)) {
            perms.add(listOf(list[index]) + perm)
        }
    }
    return perms
}
