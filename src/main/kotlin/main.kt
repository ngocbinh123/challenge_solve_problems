import kotlin.math.sqrt

fun main(args: Array<String>) {
//  val v = arrayOf(
//    intArrayOf(1,1),
//    intArrayOf(2,2),
//    intArrayOf(1,2),
//  )

  val v = arrayOf(
    intArrayOf(1,4),
    intArrayOf(3,4),
    intArrayOf(3,10),
  )
  val result = solution(v)
  print("result: [${result[0]},${result[1]}]")
}

private fun solution(v: Array<IntArray>): IntArray {
  val widthArr = arrayListOf(getWidth(v[0],v[1]), getWidth(v[0],v[2]), getWidth(v[1],v[2]))
  val maxWidth = widthArr.maxOrNull()!!

  val (midP, pointB) = when(widthArr.indexOf(maxWidth)) {
    0 -> Pair(getMidPoint(v[0],v[1]), v[2])
    1 -> Pair(getMidPoint(v[0],v[2]), v[1])
    else -> Pair(getMidPoint(v[1],v[2]), v[0])
  }
  val xOfP = midP[0]*2 - pointB[0]
  val yOfP = midP[1]*2 - pointB[1]
  return intArrayOf(xOfP,yOfP)
}

private fun getMidPoint(a: IntArray, b: IntArray) : IntArray{
  val xOfMid = (a[0] + b[0])/2
  val yOfMid = (a[1] + b[1])/2
  return intArrayOf(xOfMid, yOfMid)
}

private fun getWidth(a: IntArray, b: IntArray): Double {
  val x = a[0]-b[0]
  val y = a[1]-b[1]
  return sqrt((x*x + y*y).toDouble())
}

