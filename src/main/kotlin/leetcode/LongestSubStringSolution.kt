package leetcode

fun main(args: Array<String>) {
  val s =
    "hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
  val count = LongestSubStringSolution().lengthOfLongestSubstring(s)
  println("$count")
}

class LongestSubStringSolution {
  fun lengthOfLongestSubstring(s: String): Int {
    if (s.length <= 1) return s.length
    else if (s.trim().isEmpty()) return 1
    var count = -1
    var startIndex = 0
    var endIndex = 1
    val sArr = s.toCharArray()
    do {
      val subArr = sArr.copyOfRange(startIndex, endIndex)
      val isNoRepeat = isNoRepeatString(subArr)
      if (isNoRepeat) {
        count = count.coerceAtLeast(subArr.size)
        endIndex += 1
      } else {
        startIndex += 1
      }
    } while (endIndex <= s.length)
    return count
  }

  private fun isNoRepeatString(sArr: CharArray): Boolean {
    val lastChar = sArr.last()
    val count = sArr.count { it == lastChar }
    return count <= 1
  }
}