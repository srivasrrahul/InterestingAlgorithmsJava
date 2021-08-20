package oceanviewbuildings

import scala.collection.mutable.ListBuffer

object Solution {
  def findBuildings(heights: Array[Int]): Array[Int] = {
    val lst = new ListBuffer[Int]
    var maxHeight = heights.last
    lst.append(heights.length-1)
    for (j <- heights.last-2 to 0 by -1) {
      if (heights(j) >= maxHeight) {
        lst.append(heights(j))
      }

      maxHeight = math.max(maxHeight,heights(j))
    }

    lst.reverse.toArray
  }
}
