package indie.basic

fun main() {
    val list = listOf(1,2,3,4)
    println(permutation(list, 2).joinToString("\n") { it.joinToString(", ") })

    println(generatePermutation(list))
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

fun generatePermutation(list: List<Int>): List<List<Int>> {
    if (list.isEmpty()) return emptyList()

    fun recursive(elements: List<Int>, length: Int): List<List<Int>> {
        if(length == 0) return listOf(emptyList())
        if(elements.isEmpty()) return emptyList()

        val temp = mutableListOf<List<Int>>()
        for(index in elements.indices) {
            val remaining = elements.toMutableList().apply { removeAt(index) }
            for (perm in recursive(remaining, length - 1)) {
                temp.add(listOf(elements[index]) + perm)
            }
        }
        return temp
    }

    return recursive(list, 2)
}
