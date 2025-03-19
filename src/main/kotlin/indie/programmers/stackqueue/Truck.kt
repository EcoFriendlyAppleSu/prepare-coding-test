package indie.programmers.stackqueue

fun main() {
    val bridgeLength = 2
    val weight = 10
    val truckWeights = intArrayOf(7, 4, 5, 6)

    println(solution(bridgeLength, weight, truckWeights))
}

fun solution2(bridgeLength: Int, weight: Int, truckWeights: IntArray): Int {
    val ready = truckWeights.toMutableList()
    val during = ArrayDeque<Int>(bridgeLength) // 다리 위의 트럭 상태 (0은 빈 공간)
    var answer = 0
    var currentWeight = 0 // 현재 다리 위의 총 무게

    repeat(bridgeLength) { during.addLast(0) } // 다리 길이만큼 0(빈 공간)으로 초기화

    while (ready.isNotEmpty() || currentWeight > 0) {
        // 1. 다리에서 트럭 제거 혹은 존재하지 않은 부분(= 0) 제거
        val exitingTruck = during.removeFirst()
        currentWeight -= exitingTruck

        // 2. 새로운 트럭 추가 가능 여부 확인
        if (ready.isNotEmpty() && currentWeight + ready.first() <= weight) {
            val newTruck = ready.removeFirst()
            during.addLast(newTruck)
            currentWeight += newTruck
        } else {
            during.addLast(0) // 다리가 가득 찼다면 빈 공간 추가
        }

        // 3. 시간 증가
        answer += 1
    }

    return answer
}


fun solution(bridgeLength: Int, weight: Int, truckWeights: IntArray): Int {
    val ready = truckWeights.toMutableList()
    val during = ArrayDeque<Int>(bridgeLength)
    var answer = 0

    for (i in 0 until bridgeLength) { during.addLast(0) }

    while (true) {
        if (isAllZero(during) && ready.isEmpty()) { break }

        var temp = 0
        for (i in during.indices) {
            temp += during[i]
        }
        if (ready.isNotEmpty() && temp + ready.first() <= weight) {
            during.removeFirst()
            val readyTruck = ready.removeFirst()
            during.addLast(readyTruck)
            answer += 1
        } else {
            during.removeFirst()
            during.addLast(0)
            answer += 1
        }

        if (ready.isEmpty()) {
            while (during.isNotEmpty()) {
                during.removeFirst()
                answer += 1
            }
            break
        }
    }
    return answer
}

fun isAllZero(during: ArrayDeque<Int>): Boolean {
    for (index in during.indices) {
        if (during[index] != 0) {
            return false
        }
    }
    return true
}


/*
2	10	[7,4,5,6]	8
*/
