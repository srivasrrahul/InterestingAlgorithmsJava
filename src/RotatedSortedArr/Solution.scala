package RotatedSortedArr

object Solution {
  def findMin(nums: Array[Int]): Int = {
    def findPivot(low : Int,high : Int) : Int = {
      if (low >= high) {
        low
      }else {
        val mid = low + (high-low)/2
        if (nums(mid) < nums(high)) {
          findPivot(low,mid)
        }else {
          findPivot(mid+1,high)
        }
      }
    }

    nums(findPivot(0,nums.length-1))
  }

  def main(args: Array[String]): Unit = {
    println(findMin(Array(3,3,4,5,6,7,8,9,10,11,12,13,14,15,16,1,2)))
  }
}
