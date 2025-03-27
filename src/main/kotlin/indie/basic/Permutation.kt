package indie.basic

fun main() {
    val list = listOf(1,2,3,4)
    println(permutation(list, 2).joinToString("\n") { it.joinToString(", ") })
}

fun <T> permutation(elements: List<T>, length: Int): List<List<T>> {
    if (length == 0) return listOf(emptyList())
    if (elements.isEmpty()) return emptyList()

    val perms = mutableListOf<List<T>>()
    for(index in elements.indices) {
        val remaining = elements.toMutableList().apply { removeAt(index) }
        for (perm in permutation(remaining, length - 1)) {
            perms.add(listOf(elements[index]) + perm)
        }
    }
    return perms
}
