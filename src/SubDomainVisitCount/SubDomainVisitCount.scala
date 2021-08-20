package SubDomainVisitCount

//package SubDomainVisitCount

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Solution {
  def subdomainVisits(cpdomains: Array[String]): List[String] = {
    val counters = new mutable.HashMap[String,Int]()

    for (domins <- cpdomains) {
      val parsedValue = domins.split(" ")
      //println(parsedValue.mkString(","))
      val arr = parsedValue(1).split("\\.").toList
      val count = parsedValue(0).toInt
      var lst = arr
      for (j <- 0 to arr.length-1) {
        val currentPath = lst.mkString(".")
        val defCounter = counters.getOrElseUpdate(currentPath,0)
        counters += ((currentPath,defCounter+count))
        lst = lst.tail
      }
    }

    val retValue = new ListBuffer[String]
    counters.foreach(strCount => {
      retValue.append(strCount._2.toString + " " + strCount._1.toString)
    })

    retValue.toList

  }
}
