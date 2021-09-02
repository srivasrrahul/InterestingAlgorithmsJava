package SortArrayByIncreasingFrequency

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Solution {
  def frequencySort(nums: Array[Int]): Array[Int] = {
    val counter = new mutable.HashMap[Int,Int]() //Int Count
    for (num <- nums) {
      val currCount = counter.getOrElseUpdate(num,0)
      counter += ((num,currCount+1))
    }

    val countInt = new mutable.TreeMap[Int, mutable.TreeSet[Int]]

    for ((value,count) <- counter) {
      val currSet = countInt.getOrElseUpdate(count,new mutable.TreeSet[Int]()(Ordering[Int].reverse))
      currSet.add(value)
    }

    val arrayBuffer = new ArrayBuffer[Int]

    for ((count,values) <- countInt) {
      for (v <- values) {
        for (j <- 0 to counter.get(v).get) {
          arrayBuffer.append(v)
        }
      }
    }

    arrayBuffer.toArray


  }
}
