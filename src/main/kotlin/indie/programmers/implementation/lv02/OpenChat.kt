package indie.programmers.implementation.lv02

fun main() {
    val record = arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan")
    println(solution02(record).joinToString(" "))
}

fun solution02(record: Array<String>): Array<String> {
    val map = mutableMapOf<String, String>() // id, name 담는 map
    val inAndOutList = mutableListOf<String>() // 참가, 떠남을 기록하는 list
    val enterMessage = "님이 들어왔습니다."
    val leaveMessage = "님이 나갔습니다."

    // map setting
    for(eachRecord in record) {
        val tempList = eachRecord.split(" ") // leave일 땐, size가 2임


        if(tempList[0] != "Change") {
            val idCondition = "${tempList[1]} ${tempList[0]}"
            inAndOutList.add(idCondition)
        }

        if(tempList[0] == "Enter") {
            map[tempList[1]] = tempList[2]
        } else if(tempList[0] == "Change") {
            map[tempList[1]] = tempList[2]
        } else {
            continue
        }
    }

    val result = mutableListOf<String>()
    for(index in 0 until inAndOutList.size){
        val (id, condition) = inAndOutList[index].split(" ")
        val name = map[id]!!
        if(condition == "Enter") {
            result.add("$name$enterMessage")
        } else {
            result.add("$name$leaveMessage")
        }
    }
    return result.toTypedArray()
}
/*
["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]
["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
*/
