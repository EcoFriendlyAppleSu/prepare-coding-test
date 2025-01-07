package indie.abroad.week05

fun main() {
    val map01 = mutableMapOf("a" to 1, "b" to 2)
    val map02 = mutableMapOf("b" to 2, "a" to 1)

    if (map01.equals(map02)) {
        println("yes")
    }

    val str01 = "abc"
    val str02 = "cba"
    val strKey01 = str01.toCharArray().sorted().joinToString("")
    val strKey02 = str02.toCharArray().sorted().joinToString("")
    println(strKey01)
    println(strKey02)

    val strs = arrayOf("eat","tea","tan","ate","nat","bat")
    println(groupAnagrams(strs).joinToString(" "))
    println(groupAnagrams(strs))
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val result = mutableMapOf<String, MutableList<String>>()

    for (str in strs) {
        val key = str.toCharArray().sorted().joinToString("")
        result.computeIfAbsent(key) { mutableListOf() }.add(str)
    }

    if (strs.isEmpty()) return listOf(listOf())
    return result.values.toList()
}



/*
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
*/
