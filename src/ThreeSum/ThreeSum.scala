package ThreeSum

import scala.collection.{Searching, mutable}
import scala.collection.mutable.ListBuffer

object Solution {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    nums.sortInPlace()
    def twoSum(needle : Int,low : Int,high : Int) : List[List[Int]] = {
      val retValue = new ListBuffer[List[Int]]
      for (j <- low to high) {
        val pending = needle-nums(j)
        nums.search(pending,j+1,high+1) match {
          case Searching.Found(foundIndex) => {
            retValue.append(List(nums(j),nums(foundIndex)))
          }
          case _ => {

          }
        }
      }

      retValue.toList

      }

    val retValue = new mutable.HashSet[List[Int]]
    for (j <- 0 to nums.length-2) {
      val pending = 0-nums(j)
      val pendingSums = twoSum(pending,j+1,nums.length-1)
      if (pendingSums.size > 0) {
        for (pendingSum <-  pendingSums) {
          val lst = Array(nums(j),pendingSum.head,pendingSum.tail.head).sorted.toList
          retValue.add(lst)
        }

      }
    }

    retValue.toList
  }
}