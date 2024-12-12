package indie.programmers.stackqueue

/**
 * programmers 옳바른 괄호 문제
 */
fun main() {
    val example = ")()()())"
    println(solution(example))

}

fun solution(example: String): Boolean {
    if (example.isBlank()) {
        return true
    }
    val splitExample = example.toList()
    val stack = mutableListOf<Char>()


    for (i in splitExample.indices) {
        if (splitExample[i] == '(') {
            stack.add('(')
        } else if (splitExample[i] == ')'){
            if (stack.isEmpty()) {
                return false
            } else {
                stack.removeLast()
            }
        }
    }
    return stack.isEmpty()
}

/*
"()()"	true
*/
