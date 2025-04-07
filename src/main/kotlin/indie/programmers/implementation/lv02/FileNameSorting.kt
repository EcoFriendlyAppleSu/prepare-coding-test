package indie.programmers.implementation.lv02

fun main() {
    val files = arrayOf("img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG")
    println(solution(files).toList())
}
fun solution(files: Array<String>): Array<String> {
    // 안정적인 정렬을 위해 원본 인덱스 보존
    return files.mapIndexed { index, file ->
        // 파일명 분석
        val head: String
        val number: Int

        // HEAD 부분 찾기 (첫 번째 숫자가 나올 때까지)
        val numberStartIdx = file.indexOfFirst { it.isDigit() }
        head = file.substring(0, numberStartIdx).lowercase()

        // NUMBER 부분 찾기 (연속된 숫자들, 최대 5자리)
        var numberEndIdx = numberStartIdx
        while (numberEndIdx < file.length &&
               file[numberEndIdx].isDigit() &&
               numberEndIdx - numberStartIdx < 5) {
            numberEndIdx++
        }

        number = file.substring(numberStartIdx, numberEndIdx).toInt()

        // 원본 파일, HEAD, NUMBER, 원본 인덱스를 함께 저장
        FileInfo(file, head, number, index)
    }.sortedWith(
        compareBy<FileInfo> { it.head } // compareBy method에 type을 명시해주고
            .thenBy { it.number }
            .thenBy { it.originalIndex } // 원래 순서 유지
    ).map { it.originalFile }.toTypedArray()
}

// 파일 정보를 담는 데이터 클래스
data class FileInfo(
    val originalFile: String,  // 원본 파일명
    val head: String,          // HEAD 부분 (소문자로 변환됨)
    val number: Int,           // NUMBER 부분 (숫자로 변환됨)
    val originalIndex: Int     // 원래 입력 순서
)
