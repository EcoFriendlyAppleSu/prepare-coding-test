package indie.programmers.implementation.lv01

fun main() {
    val data = arrayOf(
        intArrayOf(1, 20300104, 100, 80),
        intArrayOf(2, 20300804, 847, 37),
        intArrayOf(3, 20300401, 10, 8),
    )
}

fun solution(data: Array<IntArray>, ext: String, standard: Int, sort: String): Array<IntArray> {
    val extMap = mapOf<String, Int> (
        "code" to 0,
        "date" to 1,
        "maximum" to 2,
        "remain" to 3,
    )

    val extIndex = extMap[ext]!!
    val sortIndex = extMap[sort]!!
    val filteredData = data.filter { it -> it[extIndex] < standard }
    return filteredData.sortedBy { it[sortIndex] }.toTypedArray()
}

/*
data
[[], []]	"date"	20300501	"remain"	[[3,20300401,10,8],[1,20300104,100,80]]
ext
[2, 20300804, 847, 37]
val_ext
"date"
sort_by
20300501
*/
