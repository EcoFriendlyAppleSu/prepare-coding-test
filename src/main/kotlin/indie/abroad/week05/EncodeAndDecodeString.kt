package indie.abroad.week05

fun main() {
    val strList = listOf("we", "say", ":", "yes")
    val str = "we:;say:;:::;yes"

    println(encoding(strList))
    println(decoding("lint:;code:;love:;you"))
    println(decoding("we:;say:;:::;yes"))
}

fun encoding(strs: List<String>): String {
    var result = ""
    if (strs.isEmpty()) return result
    for (index in 0 until strs.size - 1) {
        if (strs[index] == ":") {
            result += strs[index] + "::;"
        } else {
            result += strs[index] + ":;"
        }
    }
    result += strs[strs.size - 1]
    return result
}

fun decoding(str: String): List<String>{
    val splitedStrList = str.split(":;")
    val result = mutableListOf<String>()
    for (splitStr in splitedStrList){
        if (splitStr == "::") {
            result.add(":")
        } else {
            result.add(splitStr)
        }
    }
    return result
}


/*
Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
Explanation:
One possible encode method is: "we:;say:;:::;yes"
*/


/*
Input: ["lint","code","love","you"]
Output: ["lint","code","love","you"]
Explanation:
One possible encode method is: "lint:;code:;love:;you"
*/
