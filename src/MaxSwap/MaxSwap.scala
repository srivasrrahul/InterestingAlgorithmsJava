package scala

import scala.collection.mutable

object Solution {
  def maximumSwap(num: Int): Int = {
    val numberLocations = new mutable.TreeMap[Int,mutable.TreeSet[Int]]()
    val s = num.toString
    for (j <- 0 to s.length-1) {
      val set = numberLocations.getOrElseUpdate(s(j).asDigit,new mutable.TreeSet[Int]())
      set.add(j)
    }

    val str = new StringBuilder
    str.append(s)

    var replaced = false
    for (j <- 0 to str.length() if replaced == false) {
      val current = str(j).asDigit
      if (current == numberLocations.last) {
        //continue
        val set = numberLocations.get(current).get
        set.remove(j)
        if (set.size == 0) {
          numberLocations.remove(current)
        }
      }else {
        //replace with first max
        val last = numberLocations.last
        val replaceValu = last._1
        val replaceIndex = last._2.head
        val t = str.charAt(j)

        str.setCharAt(j,str(replaceIndex))
        str(replaceIndex) = t

        replaced = true

      }
    }

    str.toString().toInt
  }

  def main(args: Array[String]): Unit = {
    println(maximumSwap(7236))
  }
}
