package indie.cotae.implementation.leecotae

/**
 * Computation Thinking이란 요구사항을 데이터의 관점에서 바라보고 변수, 타입, 조건, 반복, 함수 등을 사용해 컴퓨터가 이해할 수 있는 방식으로 표현하는 과정
 * 의식적으로 컴퓨터적 사고를 키우는 방법
 * 1. 요구사항을 데이터의 관점으로 바라보기
 * 2. 데이터 구조를 기술하고 생각해보기
 * 3. 적절한 알고리즘 생각
 * 4. 2 + 3을 통해 새로운 데이터를 도출
 */
fun main () {
    val n = readln().toInt()
    val movingList = readln().split(" ").map { it }
    val startPoint = intArrayOf(1,1)

    /**
     * Map 자료구조 선언하는 방법 기억해두자
     * 여기서 Map 자료구조를 사용한 이유와 Value 로 listOf를 사용한 이유는?
     * 처음에는 변수 명으로 일치시켜 사용하고자 했음 "val L = listOf(0,-1)"
     * 그러나 이 방법은 값의 비교가 아닌 변수의 이름을 비교해야 함으로 추가작업이 들어감
     * 움직이는 좌표를 입력한 글자와 일치시키는 작업은 필요한데 명확하게 일대일 매칭이라고 판단함
     * 따라서 map이라는 자료구조를 선택
     * value로 오는 값을 list로 선언해 사용한 이유는 움직이는 좌표 값은 불변하기 때문으로 intArray보단 listof를 사용
     */
    val movingMap : MutableMap<String, List<Int>> = mutableMapOf(
        "L" to listOf(0, -1),
        "R" to listOf(0, 1),
        "U" to listOf(-1, 0),
        "D" to listOf(1, 0),
    )

    for (move in movingList) {
        val (x, y) = movingMap[move]!!

        val tempX = startPoint[0] + x
        val tempY = startPoint[1] + y

        if ((tempX <= 0 || tempX > n) || (tempY <= 0 || tempY > n)) {
            continue
        } else {
            startPoint[0] = tempX
            startPoint[1] = tempY
        }
    }

    println(startPoint.contentToString())
}