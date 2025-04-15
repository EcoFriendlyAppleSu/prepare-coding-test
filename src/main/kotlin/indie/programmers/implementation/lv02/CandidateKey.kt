package indie.programmers.implementation.lv02

fun main() {
    val relation = arrayOf(
        arrayOf("100", "ryan", "music", "2"),
        arrayOf("200", "apeach", "math", "2"),
        arrayOf("300", "tube", "computer", "3"),
        arrayOf("400", "con", "computer", "4"),
        arrayOf("500", "muzi", "music", "3"),
        arrayOf("600", "apeach", "music", "2"),
    )
    println(solution(relation)) // will be 2
}
fun solution(relation: Array<Array<String>>): Int {
    val columnSize = relation[0].size
    val candidateKeys = mutableListOf<List<Int>>()

    // 가능한 모든 속성 조합 생성
    val allCombinations = mutableListOf<List<Int>>()
    for (i in 1..columnSize) {
        allCombinations.addAll(combination((0 until columnSize).toList(), i))
    }

    // 유일성과 최소성을 모두 체크
    for (keyCandidate in allCombinations) {
        // 유일성 체크
        if (isUnique(keyCandidate, relation)) {
            // 최소성 체크
            if (isMinimal(keyCandidate, candidateKeys)) {
                candidateKeys.add(keyCandidate)
            }
        }
    }

    return candidateKeys.size
}

// 유일성 체크: 해당 속성 조합으로 모든 튜플을 유일하게 식별할 수 있는지 확인
fun isUnique(columns: List<Int>, relation: Array<Array<String>>): Boolean {
    val tupleSet = HashSet<String>()

    for (row in relation) {
        // 해당 컬럼 인덱스의 값들을 하나의 문자열로 조합
        val tuple = columns.joinToString(",") { row[it] }
        // 중복된 튜플이 있다면 유일성 만족 X
        if (!tupleSet.add(tuple)) {
            return false
        }
    }

    return true
}

// 최소성 체크: 이미 후보키인 속성의 부분집합인지 확인
fun isMinimal(columns: List<Int>, candidateKeys: List<List<Int>>): Boolean {
    for (key in candidateKeys) {
        if(key.all { it in columns }) {
            return false
        }
    }
    return true
}

// 속성 조합 생성 함수
fun combination(list: List<Int>, length: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun backtracking(elements: List<Int>, startIndex: Int, length: Int) {
        if (elements.size == length) {
            result.add(elements)
            return
        }

        for (index in startIndex until list.size) {
            backtracking(elements + listOf(list[index]), index + 1, length)
        }
    }

    backtracking(emptyList(), 0, length)
    return result
}
