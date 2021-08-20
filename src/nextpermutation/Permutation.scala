package nextpermutation

import scala.collection.mutable

object Solution {
  def swap(nums : Array[Int],j : Int, k : Int) : Unit = {
    val t = nums(j)
    nums(j) = nums(k)
    nums(k) = t
  }

  def nextPermutation(nums: Array[Int]): Unit = {
    def sortLeft(i: Int) : Unit = {
      java.util.Arrays.sort(nums,i,nums.length)
    }

    var ifLessThanFound = false
    val valueIndexes = new mutable.TreeMap[Int,mutable.TreeSet[Int]]()
    val indexSet = valueIndexes.getOrElseUpdate(nums.last,new mutable.TreeSet[Int]())
    indexSet.add(nums.length-1)

    for (j <- nums.length-2 to 0 by -1 if ifLessThanFound == false) {
      val current = nums(j)
      if (current < valueIndexes.last._1) {
        val indexeToBeRepalced = valueIndexes.rangeFrom(current+1).head._2.head
        swap(nums,j,indexeToBeRepalced)
        sortLeft(j+1)
        ifLessThanFound = true
      }else {
        val indexes = valueIndexes.getOrElseUpdate(current,new mutable.TreeSet[Int]())
        indexes.add(j)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1,1,5)
    println(arr.mkString(","))
    for (j <- 0 to 23) {
      nextPermutation(arr)
      println(arr.mkString(","))
    }
  }
}
