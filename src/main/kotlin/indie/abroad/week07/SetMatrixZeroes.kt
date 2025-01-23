package indie.abroad.week07

import indie.abroad.week04.row

fun main() {
    val board = arrayOf(
        intArrayOf(0,1,2,0),
        intArrayOf(3,4,5,2),
        intArrayOf(1,3,1,5),
    )
    println(setZeroes(board))
}

fun setZeroes(matrix: Array<IntArray>): Unit {
    val rowSet = mutableSetOf<Int>()
    val colSet = mutableSetOf<Int>()

    val rowSize = matrix.size
    val colSize = matrix[0].size

    // find row col
    for (row in matrix.indices) {
        for (col in matrix[0].indices) {
            if (matrix[row][col] == 0) {
                rowSet.add(row)
                colSet.add(col)
            }
        }
    }

    if (rowSet.size == 0 && colSet.size == 0) return

    for (row in rowSet) {
        for (index in IntRange(0, colSize - 1)) {
            matrix[row][index] = 0
        }
    }

    for (col in colSet) {
        for(index in IntRange(0, rowSize - 1)) {
            matrix[index][col] = 0
        }
    }
}


/*
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
*/
