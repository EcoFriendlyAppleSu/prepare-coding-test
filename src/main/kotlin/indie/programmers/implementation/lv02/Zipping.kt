package indie.programmers.implementation.lv02

fun main() {
    val str = "a"
    println(solutionX(str))
}

fun solutionX(str: String): Int {
    val result = mutableListOf<Int>()
    if(str.length == 1) return 1
    val divCount = str.length / 2
    for(time in 1 until divCount + 1) {
        val temp = mutableListOf<String>()
        var start = 0
        var end = time
        while(true) {
            if (end < str.length) {
                val substring = str.substring(start, end)
                temp.add(substring)
                start += time
                end += time
            } else {
                end -= time
                temp.add(str.substring(end, str.length))
                break
            }
        }
        val zipString = zip(temp)
        println(zipString)
        result.add(zipString.length)
    }
    return result.minOrNull() ?: 0
}

fun zip(list: List<String>): String {
    if(list.isEmpty()) return ""

    var count = 1
    var result = ""
    var current = list[0]
    for(i in 1 until list.size) {
        if(list[i] == current) {
            count++
        } else {
            if(count > 1) {
                result += count.toString() + current
            } else {
                result += current
            }
            current = list[i]
            count = 1
        }
    }
    if(count > 1) {
        result += count.toString() + current
    } else {
        result += current
    }

    return result
}


/*
"aabbaccc"	7
"ababcdcdababcdcd"	9
"abcabcdede"	8
"abcabcabcabcdededededede"	14
"xababcdcdababcdcd"	17
*/
