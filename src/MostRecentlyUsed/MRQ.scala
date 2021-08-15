package MostRecentlyUsed

import scala.collection.mutable

class MRUQueue(_n: Int) {

  val valTime = new mutable.HashMap[Int,Int]() //val => time
  val timeVal = new mutable.HashMap[Int,Int]() //time => val
  val orderedSet = new mutable.TreeSet[Int]()
  var time = 0
  def getNewTime() : Int = {
    time = time+1
    time
  }
  for (j <- 1 to _n) {
    val timeForJ = getNewTime()
    valTime += ((j,timeForJ))
    timeVal += ((timeForJ,j))
    orderedSet.add(timeForJ)
  }

  def fetch(k: Int): Int = {
    val set = orderedSet.take(k)
    val kthTime = set.last
    val khValue = timeVal.get(kthTime).get

    val newTime = getNewTime()

    valTime += ((khValue,newTime))
    timeVal.remove(kthTime)
    timeVal += ((newTime,khValue))
    orderedSet.remove(kthTime)
    orderedSet.add(newTime)
    khValue
  }

}
