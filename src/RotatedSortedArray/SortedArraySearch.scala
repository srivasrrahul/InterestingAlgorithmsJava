package RotatedSortedArray

import scala.collection.Searching

object Solution {
  def search(nums: Array[Int], target: Int): Int = {
    def findPivot(low : Int,high : Int) : Int = {
      val s = high-low+1
      if (s < 3) {
        low
      }else {
        val mid = low + (high-low)/2
        if (nums(mid) > nums(mid-1) && nums(mid) > nums(mid+1)) {
          mid
        }else {
          if (nums(mid) > nums(low)) {
            findPivot(mid+1,high)
          }else {
            findPivot(low,mid)
          }
        }

      }
    }

    val pivotIndex = findPivot(0,nums.length-1)
    //println(pivotIndex)
    nums.search(target,0,pivotIndex+1) match {
      case Searching.Found(foundIndex) => {
        foundIndex
      }
      case _ => {
        nums.search(target,pivotIndex+1,nums.length) match {
          case Searching.Found(foundIndex) => foundIndex
          case _ => -1
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(6,7,8,9,1,2,3,4,5)
    println(search(arr,0))
  }
}
