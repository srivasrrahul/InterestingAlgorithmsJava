package RotatedSortedArray

object Solution1{
  def findMin(nums: Array[Int]): Int = {
    def findPivot(low : Int,high : Int) : Int = {
      //println(low + " " + high)
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

    if (nums.last > nums.head) {
      nums(0)
    }else {
      val pivot = findPivot(0,nums.length-1)
      //println(pivot)
      if (pivot != nums.length-1) {
        nums(pivot+1)
      }else {
        nums(0)
      }
    }

  }
}
