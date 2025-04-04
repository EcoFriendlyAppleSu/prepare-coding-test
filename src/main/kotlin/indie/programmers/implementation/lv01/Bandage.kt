package indie.programmers.implementation.lv01

fun main() {
    val bandage = listOf(5, 1, 5)
    val health = 30
    val attacks = arrayOf(
        intArrayOf(2, 10),
        intArrayOf(9, 15),
        intArrayOf(10, 5),
        intArrayOf(11, 5),
    )
    println(solution(bandage, health, attacks))
} // [1, 15], [5, 16], [8, 6] // [5, 1, 5]	30	[[2, 10], [9, 15], [10, 5], [11, 5]]

fun solution(bandage: List<Int>, health: Int, attacks: Array<IntArray>): Int {
    var currentHealth = health
    val maxHealth = health // 체력은 최대값을 넘을 수 없습니다.

    val skillTime = bandage[0]
    val healthPoint = bandage[1]
    val plusHealth = bandage[2]

    val queue = ArrayDeque<Pair<Int, Int>>()

    for(attack in attacks) {
        val time = attack[0]
        val damage = attack[1]
        queue.addLast(Pair(time, damage))
    }

    var currentTime = 0
    var currentSkillTime = 0
    while(queue.isNotEmpty()) {
        val attackTime = queue.first().first
        if (currentTime == attackTime) { // 실행 시간과 공격 받은 시점이 같다면
            val (time, damage) = queue.removeFirst()
            currentHealth -= damage // 데미지 손상
            if (currentHealth <= 0) { // 0 이하로 내려가면 회복이 불가능하기 때문에 -1을 return하고 종료
                return -1
            }
            currentSkillTime = 0 // 손상이 입을 경우 스킬 시전 시간 초기화
        } else { // 공격을 당하지 않았을 때엔
            currentSkillTime += 1 // 스킬 시전 시간을 늘리고
            if(currentSkillTime == skillTime) { // 붕대 감기 스킬이 완성되었을 때,
                currentHealth += healthPoint // 헬스 포인트를 얻습니다.
                currentHealth += plusHealth // 완성 했으니 추가 포인트도 얻습니다.
                if(currentHealth >= maxHealth) { // 그러나 최대 체력보다 높은 값을 갖고 있다면
                    currentHealth = maxHealth // 최대 수치를 넘지 못합니다.
                }
                currentSkillTime = 0// 끝으로 currentSkillTime을 초기화시킵니다.
            } else { // 만약 붕대 갑기 스킬이 완성된 상황이 아니라면
                currentHealth += healthPoint // 헬스 포인드를 더하고
                if(currentHealth >= maxHealth) { // 최대 치라면
                    currentHealth = maxHealth // 넘지 못합니다.
                }
            }
        }
        currentTime += 1
    }
    return currentHealth
}


/*
bandage	health	attacks	result
[5, 1, 5]	30	[[2, 10], [9, 15], [10, 5], [11, 5]]	5
[3, 2, 7]	20	[[1, 15], [5, 16], [8, 6]]
*/
