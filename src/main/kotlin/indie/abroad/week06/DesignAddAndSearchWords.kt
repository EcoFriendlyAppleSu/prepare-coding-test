package indie.abroad.week06

import java.util.concurrent.atomic.AtomicInteger

/*
* - 문자열 요소에 접근하는 방법으로 StringBuilder를 사용해 변경할 수 있습니다.
* */

fun main() {
}

/*class WordDictionary() {
    val dictionary = mutableMapOf<String, MutableList<String>>()

    fun addWord(word: String) {
        val elementList = mutableListOf<String>()
        elementList.add(word)
        elementList.addAll(makingDotCombination(word))

        for (element in elementList) {
            dictionary.computeIfAbsent(element) { mutableListOf() }.add(word)
        }
        return
    }
    fun search(word: String): Boolean {
        val value = dictionary[word]
        return value.isNullOrEmpty()
    }

    fun makingDotCombination(str: String): List<String> {
        val result = mutableListOf<String>()
        val length = str.length
        fun generation(index: Int, sb: StringBuilder, count: Int) {
            // escape condition
            if (length == count) {
                result.add(sb.toString())
                return
            }

            for (i in index until length) {
                val originalChar = str[i]
                sb[i] = '.'
                generation(i + 1, sb, count + 1)
                sb[i] = originalChar
            }
        }

        for (i in 0 until length) {
            generation(0, StringBuilder(str), i)
        }
        return result.distinct()
    }
}*/

class WordDictionary() {
    // Trie 노드 클래스
    private class TrieNode {
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
        var endOfWord: Boolean = false
    }

    private val root = TrieNode()

    // 단어를 트라이에 추가
    fun addWord(word: String) {
        var currentNode = root
        for (char in word) {
            if (!currentNode.children.containsKey(char)) {
                currentNode.children[char] = TrieNode()  // 해당 문자에 대한 새로운 노드 생성
            }
            currentNode = currentNode.children[char]!! // point out next trie node
        }
        currentNode.endOfWord = true  // 단어의 끝을 표시
    }

    // 주어진 패턴을 검색
    fun search(word: String): Boolean {
        return searchHelper(word, 0, root)
    }

    // 재귀적으로 단어를 검색하는 헬퍼 함수
    private fun searchHelper(word: String, index: Int, node: TrieNode): Boolean {
        if (index == word.length) {
            return node.endOfWord  // 단어의 끝에 도달했으면 해당 노드가 단어의 끝인지 확인
        }

        val char = word[index]

        if (char == '.') {  // '.'이면 모든 자식 노드에 대해 탐색
            for (childNode in node.children.values) {
                if (searchHelper(word, index + 1, childNode)) {
                    return true
                }
            }
            return false  // '.'을 처리했지만 일치하는 노드가 없으면 false
        } else {
            // 현재 문자가 존재하는 자식 노드로 계속 탐색
            val childNode = node.children[char] ?: return false
            return searchHelper(word, index + 1, childNode)
        }
    }
}
