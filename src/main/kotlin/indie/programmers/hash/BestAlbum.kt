package indie.programmers.hash

fun main() {
    val genres = arrayOf("classic", "pop", "classic", "classic", "pop")
    val plays = intArrayOf(500, 600, 150, 800, 2500)
    println(solution1(genres, plays))
}

/**
 * 총 장르 플레이 횟수를 신경쓰지 못했음
 */
fun solution(genres: Array<String>, plays: IntArray): IntArray {
    val map = mutableMapOf<String, MutableList<Pair<Int, Int>>>()

    for (genre in genres) {
        map[genre] = mutableListOf()
    }

    for (index in genres.indices) {
        map[genres[index]]!!.add(Pair(index, plays[index]))
    }

    // map element를 순회해 play가 위로 가게 순회
    val sortedMap = map.mapValues { (_, value) -> value.sortedByDescending { it.second }.toMutableList() }.toMutableMap()
    
    val result = mutableListOf<Int>()
    
    while (true) {
        if (sortedMap.isEmpty()) break
        var maxPlayGenre = ""
        var maxPlay = Integer.MIN_VALUE
        
        for ((key, value) in sortedMap) {
            val (index, play) = value.first()
            if (play > maxPlay) {
                maxPlay = play
                maxPlayGenre = key
            }
        }
        val bestAlbum = sortedMap[maxPlayGenre]
        if (bestAlbum.isNullOrEmpty() || bestAlbum.size < 2) {
            sortedMap.remove(maxPlayGenre)
        } else {
            val firstSong = bestAlbum.removeFirst()
            val secondSong = bestAlbum.removeFirst()
            result.add(firstSong.first)
            result.add(secondSong.first)
            if (bestAlbum.size < 2) { sortedMap.remove(maxPlayGenre) }
        }
    }

    return result.toIntArray()
}

fun solution1(genres: Array<String>, plays: IntArray): IntArray {
    val genrePlayCount = mutableMapOf<String, Int>()  // 장르별 총 재생 횟수
    val genreSongs = mutableMapOf<String, MutableList<Pair<Int, Int>>>() // 장르별 (고유번호, 재생수) 리스트

    // 데이터 초기화
    for (index in genres.indices) {
        val genre = genres[index]
        val play = plays[index]

        // 장르별 총 재생 횟수 누적
        genrePlayCount[genre] = genrePlayCount.getOrDefault(genre, 0) + play

        // 장르별 노래 목록 추가
        genreSongs.putIfAbsent(genre, mutableListOf())
        genreSongs[genre]!!.add(Pair(index, play))
    }

    // 1. 장르별 총 재생 횟수 내림차순 정렬
    val sortedGenres = genrePlayCount.entries.sortedByDescending { it.value }.map { it.key }

    val result = mutableListOf<Int>()

    // 2. 장르별로 가장 많이 재생된 노래 2개 선택
    for (genre in sortedGenres) {
        val songs = genreSongs[genre]!!
            .sortedWith(compareByDescending<Pair<Int, Int>> { it.second } // 재생 횟수 기준 내림차순 정렬
                .thenBy { it.first }) // 재생 횟수가 같으면 고유 번호 기준 오름차순 정렬

        // 최대 2개까지 선택
        result.add(songs[0].first)
        if (songs.size > 1) {
            result.add(songs[1].first)
        }
    }

    return result.toIntArray()
}
