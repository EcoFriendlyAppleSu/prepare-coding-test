package indie.abroad.week06

fun main() {
    val s = "(])"
    println(isValid(s))
}

fun isValid(s: String): Boolean {
    val stack = mutableListOf<Char>()

    for (element in s) {
        if (element == ')' || element == ']' || element == '}') {
            if (stack.isEmpty()) {
                return false
            } else if (stack.last() == '(' && element == ')') {
                stack.removeAt(stack.lastIndex)
            } else if (stack.last() == '[' && element == ']') {
                stack.removeAt(stack.lastIndex)
            } else if (stack.last() == '{' && element == '}') {
                stack.removeAt(stack.lastIndex)
            } else {
                return false
            }
        } else {
            stack.add(element)
        }
    }
    return stack.isEmpty()
}


/*
Input: s = "()[]{}"
Output: true

Input: s = "([])"
Output: true
*/
