package indie.programmers.implementation.lv02

fun main() {
    val fees = intArrayOf(1, 461, 1, 10)
    val records = arrayOf("00:00 1234 IN")
    println(solution1(fees, records))
}

fun solution1(fees: IntArray, records: Array<String>): IntArray {
    val inAndOutMap = generateInAndOutMap(records)
    val carRecordMap = mutableMapOf<String, Int>()

    for((car, timeList) in inAndOutMap.entries) {
        val temp = timeList.toMutableList()
        carRecordMap[car] = carRecordMap.getOrPut(car) { 0 }
        if(timeList.size % 2 != 0) { // 짝수가 아닐 때,
            temp.add(1439)
        }

        for(index in 0 until temp.size step(2)) {
            val firstValue = temp[index]
            val secondValue = temp[index + 1]
            carRecordMap[car] = carRecordMap[car]!!.plus(secondValue - firstValue)
        }
    }
    return getFeeList(fees, carRecordMap)
}

fun getFeeList(fees: IntArray, carMap: Map<String, Int>): IntArray {
    val map = mutableMapOf<Int, Int>()
    val (defaultTime, defaultFee, perTime, perFee) = fees
    for((car, time) in carMap.entries) {
        val intCar = car.toInt()
        map[intCar] = map.getOrPut(intCar) { 0 } // map 초기화
        if(time <= defaultTime) { // 기본 시간 이하라면
            map[intCar] = map[intCar]!!.plus(defaultFee)
        } else { // 기본 시간이 아니라면
            val overTime = time - defaultTime
            var perCount = overTime / perTime
            if(overTime % perTime != 0) { // 남아있다면
                perCount++ // 1 추가
            }
            val perValue = perCount * perFee
            map[intCar] = map[intCar]!!.plus(defaultFee + perValue)
        }
    }
    val sortedMap = map.toSortedMap()
    return sortedMap.values.toIntArray()
}

fun generateInAndOutMap(records: Array<String>): Map<String, List<Int>> {
    val map = mutableMapOf<String, MutableList<Int>>()
    for(record in records) {
        val (time, car, condition) = record.split(" ")
        val (hour, minute) = time.split(":")
        val timeValue = (hour.toInt() * 60) + minute.toInt()
        map.getOrPut(car) { mutableListOf() }.add(timeValue)
    }
    return map
}

/*
[1, 461, 1, 10]	["00:00 1234 IN"]	[14841]
*/
