package indie.basic.backtracking

val keypad = mapOf<String, List<String>>(
    "2" to listOf("A", "B", "C"),
    "3" to listOf("D", "E", "F"),
    "4" to listOf("G", "H", "I"),
    "5" to listOf("J", "K", "L"),
    "6" to listOf("M", "N", "O"),
    "7" to listOf("P", "Q", "R", "S"),
    "8" to listOf("T", "U", "V"),
    "9" to listOf("W", "X", "Y", "Z"),
)

fun main() {
    val input = "23"
    val combination = letterCombinations(input)
    println(combination)
    val sol = solution(input)
    println(sol)
}

fun solution(digit: String): List<String> {
    if (digit.isEmpty()) return emptyList()
    val result = mutableListOf<String>()

    fun backtracking(index: Int, list: List<String>) {
        // 종료 조건
        if (index == digit.length) {
            result.add(list.joinToString(""))
            return
        }

        val target = digit[index].toString()
        val targetList = keypad[target] ?: emptyList()
        val mutableList = list.toMutableList()

        for (element in targetList) {
            mutableList.add(element)
            backtracking(index + 1, mutableList)
            mutableList.removeLast() // 마지막 제거
        }
    }
    backtracking(0, listOf())
    return result
}

fun letterCombinations(input: String): List<String> {
    if (input.isEmpty()) return emptyList()
    val phoneMap = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )

    val result = mutableListOf<String>()

    fun backtrack(index: Int, currentCombination: StringBuilder) {
        if(index == input.length) {
            result.add(currentCombination.toString())
            return
        }

        val currentDigit = input[index]
        val letters = phoneMap[currentDigit] ?: ""

        for(letter in letters) {
            currentCombination.append(letter)
            backtrack(index + 1, currentCombination)
            currentCombination.deleteAt(currentCombination.length - 1)
        }
    }
    backtrack(0, StringBuilder())
    return result
}

