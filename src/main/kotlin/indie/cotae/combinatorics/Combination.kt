package indie.cotae.combinatorics

fun main() {
    val nums = listOf(1,2,3,4)
    val k = 2
    println(combination(nums, k))
}

fun combination(nums: List<Int>, k: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val maxSize = nums.size
    val indices = IntArray(k)
    for (i in 0 until k) {
        indices[i] = i
    }

    while (indices[k - 1] < maxSize) {
        result.add(indices.map { nums[it] })
        var i = k - 1
        while (i >= 0 && indices[i] == i + maxSize - k) { // 0 보다 크고 indices의 마지막 원소가 마지막 인덱스 값을 가진다면
            i-- // 그 전의 값으로 갑니다. 마지막 원소의 배열이 아닐 경우 i는 -1이 되며 while loop을 종료하게 됩니다.
        }
        // 마지막 원소 그 전에 접근해 index 위치를 가리키는 indices 배열을 초기화 시킵니다.
        if (i >= 0) {
            indices[i]++
            for (j in i + 1 until k) {
                indices[j] = indices[j - 1] + 1
            }
        } else {
            break
        }
    }
    return result
}

fun combinations(arr: List<Int>, k: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val n = arr.size

    // 조합을 뽑을 인덱스를 나타내는 배열 조합 배열
    val indices = IntArray(k)

    // 초기화 (0부터 k-1까지)
    for (i in 0 until k) {
        indices[i] = i
    }

    // 맨 마지막에 있는 숫자가 ary.size 보다 작을 경우, 조합의 마지막 index 값이 배열 크기보다 작을 때, 반복
    while (indices[k - 1] < n) {
        // [0,1]
        // [0,2]
        // [1,2]
        result.add(indices.map { arr[it] }) // 배열 조합인 indices 기준으로 주어진 ary 요소에 접근해 값을 가져와 result에 넣는 과정

        // result는 indices의 value 값을 index로 사용하는 결과를 담을 list임. e.g) indices(0,1) -> ary(0,1) -> [1, 2]
        // result = [[1,2]]
        // result = [[1,2], [1,3]]
        println("result: $result")

        // indices 배열을 증가시켜서 다음 조합을 만듦. k -> 2, i -> 1
        var i = k - 1

        // i -> 1, 2 == 1 + 4 - 2 -> false
        // i -> 1, 2 == 1 + 4 - 2 -> false
        // i -> 1, 3 == 1 + 4 - 2 -> true -> i--
        while (i >= 0 && indices[i] == i + n - k) {
            i--
        }

        // i == 1
        // i == 0
        if (i >= 0) {
            // 이건 index를 가리키는 indices 값이고 [0,1] -> [0,2]
            // [0,2] -> [1,2]
            indices[i]++

            // 이 전 index에서 값을 1 증가시켜 index를 올리는 역할
            for (j in i + 1 until k) {
                indices[j] = indices[j - 1] + 1
            }
        } else {
            break
        }
    }

    return result
}
