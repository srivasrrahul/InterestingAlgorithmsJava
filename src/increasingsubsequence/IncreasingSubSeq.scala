package increasingsubsequence

object Solution {
  def increasingTriplet(nums: Array[Int]): Boolean = {
    var m1 = Int.MaxValue
    var m2 = Int.MaxValue

    var found = false
    for (num <- nums if found == false) {
      if (num <= m1) {
        m1 = num
      }else {
        if (num <= m2) {
          m2 = num
        }else {
          found = true
        }
      }
    }

    found
  }
}
