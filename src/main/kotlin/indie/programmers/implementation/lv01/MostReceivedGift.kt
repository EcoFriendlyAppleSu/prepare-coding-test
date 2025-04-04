package indie.programmers.implementation.lv01

fun main() {

}

fun solution2(friends: Array<String>, gifts: Array<String>): Int {
    // 선물 주고받은 기록을 담는 2차원 배열
    val board = Array(friends.size) { IntArray(friends.size) }

    // 각 사람의 인덱스 맵 (이름 -> 인덱스)
    val nameToIndex = friends.withIndex().associate { (index, name) -> name to index }

    // 선물 지수 계산 (준 선물 수 - 받은 선물 수)
    val giftIndices = IntArray(friends.size)

    // 선물 기록 처리
    for (gift in gifts) {
        val (giver, receiver) = gift.split(" ")
        val giverIdx = nameToIndex[giver] ?: continue
        val receiverIdx = nameToIndex[receiver] ?: continue

        board[giverIdx][receiverIdx]++
        giftIndices[giverIdx]++  // 준 선물 +1
        giftIndices[receiverIdx]--  // 받은 선물 -1
    }

    // 다음 달에 받을 선물 수 계산
    val nextMonthGifts = IntArray(friends.size)

    for (i in friends.indices) {
        for (j in i+1 until friends.size) {
            // 두 사람이 선물을 주고받은 횟수 비교
            if (board[i][j] > board[j][i]) {
                // i가 j에게 더 많이 줌
                nextMonthGifts[i]++
            } else if (board[i][j] < board[j][i]) {
                // j가 i에게 더 많이 줌
                nextMonthGifts[j]++
            } else {
                // 선물을 주고받은 횟수가 같다면 선물 지수 비교
                if (giftIndices[i] > giftIndices[j]) {
                    nextMonthGifts[i]++
                } else if (giftIndices[i] < giftIndices[j]) {
                    nextMonthGifts[j]++
                }
                // 선물 지수도 같으면 아무도 선물을 받지 않음
            }
        }
    }

    // 가장 많은 선물을 받는 사람의 선물 수 반환
    return nextMonthGifts.maxOrNull() ?: 0
}

// run time error 발생
fun solution(friends: Array<String>, gifts: Array<String>): Int {
    val indexMap = giftIndex(gifts) // 인덱스 값
    val friendMap = makeNameBoard(friends)
    val board = makeBoard(friends, gifts, friendMap)
    val resultMap = mutableMapOf<String, Int>()
    val indexFriendMap = indexFriendMap(friends)


    for(i in 0 until board.size) {
        for(j in 0 until board.size) {
            if(i <= j) { continue }
            if(board[i][j] != board[j][i]) { // 횟수가 같지 않을 때
                if(board[i][j] > board[j][i]) {
                    val name = indexFriendMap[i]!!
                    println("name is $name")
                    resultMap[name] = resultMap.getOrPut(name) { 0 } + 1
                } else {
                    val name = indexFriendMap[j]!!
                    resultMap[name] = resultMap.getOrPut(name) { 0 } + 1
                }
            } else {
                val firstIndexName = indexFriendMap[i]!!
                val secondIndexName = indexFriendMap[j]!!
                val firstValue = indexMap[firstIndexName]!!
                val secondValue = indexMap[secondIndexName]!!

                if(firstValue > secondValue) {
                    resultMap[firstIndexName] = resultMap.getOrPut(firstIndexName) { 0 } + 1
                } else if(firstValue < secondValue) {
                    resultMap[secondIndexName] = resultMap.getOrPut(secondIndexName) { 0 } + 1
                } else { continue }
            }
        }
    }

    return resultMap.values.maxOrNull() ?: 0
}

fun makeBoard(friends: Array<String>, gifts: Array<String>, friendBoard: Map<String, Int>): MutableList<MutableList<Int>>       {
    // 0 으로 초기화한 board
    val board = MutableList(friends.size) { MutableList<Int>(friends.size) {0} }

    for(gift in gifts) {
        val (firstName, secondName) = gift.split(" ")
        val firstIndex = friendBoard[firstName]!!
        val secondIndex = friendBoard[secondName]!!
        board[firstIndex][secondIndex] += 1
    }
    return board
}

fun indexFriendMap(friends: Array<String>): Map<Int, String> {
    val resultMap = mutableMapOf<Int, String>()

    for((index, value) in friends.withIndex()) {
        resultMap[index] = value
    }
    return resultMap
}

fun makeNameBoard(friends: Array<String>): Map<String, Int> {
    val resultMap = mutableMapOf<String, Int>()

    for((index, value) in friends.withIndex()) {
        resultMap[value] = index
    }
    return resultMap
}

fun giftIndex(gifts: Array<String>): Map<String, Int> {
    val nameSet = mutableSetOf<String>()
    val giftedMap = mutableMapOf<String, Int>()
    val giftingMap = mutableMapOf<String, Int>()
    val resultMap = mutableMapOf<String, Int>()

    for((index, name) in gifts.withIndex()) {
        val (toName, fromName) = name.split(" ")
        nameSet.add(toName)
        nameSet.add(fromName)
        giftedMap[toName] = giftedMap.getOrPut(toName) { 0 } + 1
        giftingMap[fromName] = giftingMap.getOrPut(fromName) { 0 } + 1
    }

    // 채워 넣는 순회 필요
    for (name in nameSet) {
        if(!giftedMap.containsKey(name)) {
            giftedMap[name] = 0
        }
        if(!giftingMap.containsKey(name)) {
            giftingMap[name] = 0
        }
    }

    for(name in nameSet) {
        val giftedValue = giftedMap[name]!!
        val giftingValue = giftingMap[name]!!
        resultMap[name] = giftedValue - giftingValue
    }
    return resultMap
}
