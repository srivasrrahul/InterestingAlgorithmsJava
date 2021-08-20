package MaxSwap

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

    //println(numberLocations)
    //println(s)
    var replaced = false
    for (j <- 0 to s.length()-1 if replaced == false) {
      val current = s(j).asDigit
      if (current == numberLocations.last._1) {
        //println(current + " " + numberLocations.size)
        //continue
        val set = numberLocations.get(current).get
        set.remove(j)
        if (set.size == 0) {
          numberLocations.remove(current)
        }
      }else {
        //replace with first max
        //println("here " + numberLocations)
        val last = numberLocations.last
        val replaceValu = last._1
        val replaceIndex = last._2.last
        val t = str.charAt(j)

        str.setCharAt(j,str(replaceIndex))
        str(replaceIndex) = t

        replaced = true

      }
    }

    str.toString().toInt
  }

  def main(args: Array[String]): Unit = {
    println(maximumSwap(7263))
  }
}
