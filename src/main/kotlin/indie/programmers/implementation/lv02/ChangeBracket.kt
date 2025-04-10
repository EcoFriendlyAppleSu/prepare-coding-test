package indie.programmers.implementation.lv02

fun main() {
    val p = ")("
    println(solution03(p))
}

fun divide(string: String): Pair<String, String> {
   var left = 0
   var right = 0
   for (i in string.indices) {
       if (string[i] == '(') {
           left += 1
       } else {
           right += 1
       }
       if (left == right) {
           return Pair(string.substring(0, i + 1), string.substring(i + 1))
       }
   }
   return Pair("", "")  // 기본 반환값 (도달하지 않을 것으로 예상)
}

fun check(string: String): Boolean {
   val stack = mutableListOf<Char>()
   for (i in string) {
       if (i == '(') {
           stack.add(i)
       } else if (i == ')') {
           if (stack.isEmpty()) {
               return false
           }
           stack.removeAt(stack.size - 1)  // pop
       }
   }
   return true
}

fun solution03(string: String): String {
   if (string == "") {
       return ""
   }

   val (u, v) = divide(string)

   if (check(u)) {
       return u + solution03(v)
   } else {
       var answer = "("
       answer += solution03(v)
       answer += ")"

       for (i in 1 until u.length - 1) {
           if (u[i] == '(') {
               answer += ')'
           } else {
               answer += '('
           }
       }

       return answer
   }
}
/*
1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
  4-3. ')'를 다시 붙입니다.
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
  4-5. 생성된 문자열을 반환합니다.
  */


/*
"(()())()"	"(()())()"
")("	"()"
"()))((()"	"()(())()"
*/
