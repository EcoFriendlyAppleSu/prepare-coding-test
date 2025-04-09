package indie.programmers.implementation.lv02

fun main() {
    val list01 = intArrayOf(3, 2, 7, 2)
    val list02 = intArrayOf(4, 6, 5, 1)
    println(solution01(list01, list02))
}

fun solution01(queue1: IntArray, queue2: IntArray): Int {
    // 큐 초기화
    val q1 = ArrayDeque<Int>()
    val q2 = ArrayDeque<Int>()
    var sum1 = 0L
    var sum2 = 0L

    // 초기 합계 계산 및 큐 채우기
    for (num in queue1) {
        q1.add(num)
        sum1 += num
    }

    for (num in queue2) {
        q2.add(num)
        sum2 += num
    }

    // 총합이 홀수면 동일하게 만들 수 없음
    val totalSum = sum1 + sum2
    if (totalSum % 2 != 0L) return -1

    val target = totalSum / 2

    // 최대 시도 횟수 제한
    val maxAttempts = (queue1.size + queue2.size) * 2
    var count = 0

    while (count < maxAttempts) {
        if (sum1 == target) return count

        if (sum1 > target && q1.isNotEmpty()) {
            // q1에서 q2로
            val element = q1.removeFirst()
            q2.addLast(element)
            sum1 -= element
            sum2 += element
        } else if (sum2 > target && q2.isNotEmpty()) {
            // q2에서 q1로
            val element = q2.removeFirst()
            q1.addLast(element)
            sum2 -= element
            sum1 += element
        } else {
            // 더 이상 진행 불가능
            return -1
        }

        count++
    }

    return -1
}

// 값 복사가 무수히 발생해 제한 조건에 걸림
fun solution(queue1: IntArray, queue2: IntArray): Int {
    val seed01 = ArrayDeque<Int>()
    val seed02 = ArrayDeque<Int>()
    var temp = 0
    val result = mutableListOf<Int>()
    for(i in 0 until queue1.size) {
        seed01.add(queue1[i])
        temp += queue1[i]
    }
    for(i in 0 until queue1.size) {
        seed02.add(queue2[i])
        temp += queue2[i]
    }
    if(temp % 2 != 0) return -1

    var leftQ = ArrayDeque(seed01)
    var rightQ = ArrayDeque(seed02)
    var lrTime = 0
    while(true) {
        val element = leftQ.removeFirst()
        var flag = false
        rightQ.addLast(element)
        lrTime++
        var eachTime = 0
        if(leftQ.sum().toLong() == rightQ.sum().toLong()) {
            result.add(lrTime)
            break
        }
        while(rightQ.isNotEmpty()) {
            val innerElement = rightQ.removeFirst()
            leftQ.addLast(innerElement)
            eachTime++
            if (rightQ.sum().toLong() == leftQ.sum().toLong()) {
                flag = true
                break
            }
        }
        if(flag == true) {
            lrTime += eachTime
            result.add(lrTime)
            break
        }
    }

    leftQ = ArrayDeque(seed01)
    rightQ = ArrayDeque(seed02)
    var rlTime = 0
    while(true) {
        val element = rightQ.removeFirst()
        var flag = false
        leftQ.addLast(element)
        rlTime++
        var eachTime = 0
        if(leftQ.sum().toLong() == rightQ.sum().toLong()) {
            result.add(rlTime)
            break
        }
        while(leftQ.isNotEmpty()) {
            val innerElement = leftQ.removeFirst()
            rightQ.addLast(innerElement)
            eachTime++
            if (rightQ.sum().toLong() == leftQ.sum().toLong()) {
                flag = true
                break
            }
        }
        if(flag == true) {
            rlTime += eachTime
            result.add(rlTime)
            break
        }
    }

    return result.minOrNull() ?: -1
}


/*
[3, 2, 7, 2]	[4, 6, 5, 1]	2
*/
