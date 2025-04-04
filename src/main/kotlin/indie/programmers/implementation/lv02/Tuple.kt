package indie.programmers.implementation.lv02

/**
 * String을 split()을 사용하여 분절한다 하더라도 요소가 존재하지 않으면 그 문자열 그대로 배열에 담아 return합니다.
 *
 * sort, sorted의 차이는 원본 변경입니다.
 * sort: 원본 리스트를 직접 변경하고 Unit(void)을 반환합니다. 따라서 이 함수는 가변(mutable) 리스트에서만 사용 가능합니다.
 * sorted: 원본 리스트를 변경하지 않고 정렬된 새로운 리스트를 반환합니다. 불변(immutable) 리스트에서도 사용 가능합니다.
 */
fun main() {
    val str = "{{123}}"
    println(solution(str))
}

fun solution(s: String): IntArray {
    val subString = s.substring(2, s.length - 2)
    val divideList = subString.split("},{") // 분절함, {{123}} 일때 123 리턴
    val tempList = mutableListOf<List<Int>>()
    for(element in divideList) {
        val numberList = element.split(",").map { it.toInt() }.toList()
        tempList.add(numberList)
    }

    val sortedList = tempList.sortedBy { it.size }
    val tempSet = mutableSetOf<Int>()
    for(eachList in sortedList) {
        for(i in eachList) {
            tempSet.add(i)
        }
    }
    return tempSet.toIntArray()
}

/*
"{{2},{2,1},{2,1,3},{2,1,3,4}}"	[2, 1, 3, 4]
"{{1,2,3},{2,1},{1,2,4,3},{2}}"	[2, 1, 3, 4]
"{{20,111},{111}}"	[111, 20]
"{{123}}"	[123]
"{{4,2,3},{3},{2,3,4,1},{2,3}}"	[3, 2, 4, 1]
*/
