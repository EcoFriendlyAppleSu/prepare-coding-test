package indie.programmers.implementation.lv02

fun main() {
    val size = 3
    val worlds = listOf("Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA")
    println(solution(worlds, size))
}

fun solution(worlds: List<String>, size: Int): Int {
    val cache = ArrayDeque<String>()
    val mutableWorlds = worlds.toMutableList()
    var answer = 0

    while (!mutableWorlds.isEmpty()) {
        val currentWorld = mutableWorlds.removeFirst().lowercase()
        if (cache.contains(currentWorld)) { // 캐시에 나라가 존재한다면
            cache.remove(currentWorld) // 캐시에서 삭제하고
            cache.addLast(currentWorld) // 마지막에 넣습니다. 갱신과정
            answer += 1
        } else { // 캐시에 나라가 존재하지 않다면
            if (cache.size == size) { // 캐시 사이즈가 주어진 사이즈라면
                cache.removeFirst() // 첫 번째 원소를 제거하고
                cache.addLast(currentWorld) // 추가된 world를 마지막에 넣습니다.
                answer += 5 // 실행시간 5초를 넣습니다
            } else { // 캐시 사이즈가 주어진 사이즈보다 작다면 캐싱할 공간이 남아있음을 나타냄으로
                cache.addLast(currentWorld) // 마지막에 추가하고
                answer += 5 // 실행 시간을 더합니다.
            }
        }
    }
    return answer
}



/*
3	["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"]	21
*/
