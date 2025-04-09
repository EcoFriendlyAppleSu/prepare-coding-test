package indie.programmers.implementation.lv02

fun main() {
    val players = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
    val m = 3
    val k = 5
    println(solution(players, m, k))
}

fun solution(players: IntArray, m: Int, k: Int): Int {
    val result = mutableListOf<Int>()
    val queue = ArrayDeque<Int>()
    for(i in 0 until k) { // 시간을 계산할 queue 초기화
        queue.addLast(0)
    }

    for(player in players) { // 플레이어를 순회해 서버 증설을 결정합니다.
        if(player < m) { // 게임 이용자 숫자가 default 설정자보다 작다면
            result.add(0) // 0 을 넣습니다.
            queue.removeFirst() // 큐 앞을 하나 출력하고
            queue.addLast(0) // 뒤에 증설된 서버 수를 증가시킵니다.
        } else { // 기본보다 컸을 경우
            val needServerCount = player / m
            val capaStart = queue.sum() // 현재 상황에서 이용할 수 있는 컴퓨터 수
            if(needServerCount < capaStart) { // 최대 이용 수 보다 인원수가 작다면 증설 없음
                result.add(0)
                queue.removeFirst()
                queue.addLast(0)
            } else { // 최대 이용 수 보다 인원수가 크다면 증설 필요
                if(needServerCount - capaStart >= 0) {
                    val temp = needServerCount - capaStart
                    result.add(temp)
                    queue.removeFirst()
                    queue.addLast(temp)
                }
            }
        }
    }
    println(result)
    return result.sum()
}
