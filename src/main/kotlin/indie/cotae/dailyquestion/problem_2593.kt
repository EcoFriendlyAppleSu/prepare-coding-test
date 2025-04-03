package indie.cotae.dailyquestion

/**
 * 1. 가장 작은 수, 중복 시 가장 작은 인덱스의 수를 기준으로 탐색이 진행됩니다.
 * 2. 선택된 수의 양 옆이 marking이 안 된 상황이 있다면 기준 값을 marking 합니다.
 * 3. 선택된 수를 기준으로 marking이 되어 있다면 해당 수는 포함되지 않습니다.
 * 4. 모든 marking이 끝난 후 marking이 된 수의 값을 더해 답을 도출합니다.
 */
fun main() {
    val nums = listOf(2,1,3,4,5,2)
    println(solution_2593(nums))
}

fun solution_2593(nums: List<Int>): Int {
    if (nums.size == 1) {
        return 0
    }
    // 방문을 기록하는 Board Init
    val markingBoard = BooleanArray(nums.size) { false }
    val maxNumber = nums.max()
    val lastIndex = 0
    var startNum = 1

    // 순회하는 값이 maxNumber와 같은 상황이 아닐 때까지, 순회
    while (startNum != maxNumber) {
        // nums에 포함 숫자가 포함되어 있고
        if (nums.contains(startNum)) {
            // nums에 포함된 숫자의 인덱스를 구한다
            val tempIndex = nums.indexOf(startNum)
            // 인덱스가 마킹이 된 상황이라면
            if (markingBoard[tempIndex]) {
                val firstIndex = nums.indexOfFirst { it == startNum }
                val secondIndex = nums.indexOfFirst { it == startNum && nums.indexOf(it) > firstIndex }


            } else if(!markingBoard[tempIndex]) {

            }
        } else {
            startNum += 1
        }
    }

    return 0
}
