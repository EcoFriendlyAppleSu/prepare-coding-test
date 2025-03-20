package indie.programmers.bruteforce

fun main() {
    val numbers = "17"
    println(solution(numbers)) // 소수 개수 출력
}

fun solution(numbers: String): Int {
    val list = numbers.toCharArray().map { it.digitToInt() }
    val tempSet = mutableSetOf<Int>() // 중복 제거를 위해 Set 사용

    for (len in 1..list.size) { // 길이 1부터 numbers.length까지의 순열 생성
        val permSet = permutations(list, len)
        val result = permSet.map { it.joinToString("").toInt() }
        tempSet.addAll(result) // 중복 방지
    }

    // 소수 판별 및 개수 세기
    return tempSet.count { isPrime(it) }
}

// 순열 생성 함수 (길이가 length인 순열을 생성)
fun <T> permutations(elements: List<T>, length: Int): Set<List<T>> {
    if (length == 0) return setOf(emptyList())
    if (elements.isEmpty()) return emptySet()

    val perms = mutableSetOf<List<T>>()
    for (i in elements.indices) {
        val remaining = elements.toMutableList().apply { removeAt(i) }
        for (perm in permutations(remaining, length - 1)) {
            perms.add(listOf(elements[i]) + perm)
        }
    }
    return perms
}

// 소수 판별 함수 (O(√N))
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}
