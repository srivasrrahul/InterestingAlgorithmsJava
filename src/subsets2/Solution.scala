package subsets2

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Solution {
  def subsetsWithDup(nums: Array[Int]): List[List[Int]] = {
    nums.sortInPlace()
    def itr(j : Int) : List[List[Int]] = {
      if (j == nums.length-1) {
        List(List(nums(j)),List())
      }else {
        val lsts = itr(j+1)
        val retValue = new ListBuffer[List[Int]]
        for (lst <- lsts) {
          retValue.append(nums(j)::lst)
        }

        retValue.addAll(lsts)
        retValue.toList
      }
    }

    val lsts = itr(0)
    val retValue = new mutable.HashSet[List[Int]]
    lsts.foreach(lst => {
      retValue.add(lst)
    })

    retValue.toList
  }

  def main(args: Array[String]): Unit = {
    println(subsetsWithDup(Array(1,2,2)))
  }
}
