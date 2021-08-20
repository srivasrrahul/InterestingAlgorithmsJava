package MostFreqElement

import scala.collection.mutable

class FreqStack() {


  val frequency = new mutable.TreeMap[Int,mutable.TreeSet[Int]]() //freqyency => TreeSet[Time]
  val timeVal = new mutable.HashMap[Int,Int]() //Time => Number
  val valCount = new mutable.HashMap[Int,Int]() //Int => Count
  var time = 0

  def getNewTime() : Int = {
    time = time+1
    time
  }

  def push(v: Int) : Unit = {
    val currTime = getNewTime()
    timeVal += ((currTime,v))

    val newCount = valCount.getOrElse(v,0)+1
    valCount += ((v,newCount))

    val set = frequency.getOrElseUpdate(newCount,new mutable.TreeSet[Int]())
    set.addOne(currTime)

    debug()
  }

  def debug() : Unit = {
    //   println("======================")
    // println("time val " + timeVal)
    // println("val count " + valCount)
    // println("freuqnecy "  + frequency)
  }
  def pop(): Int = {
    val freq = frequency.last
    if (freq._2.size == 1) {
      val currTime = freq._2.last
      val largestFreqNum = timeVal.get(currTime).get
      timeVal.remove(currTime)
      frequency.remove(freq._1)

      val newFreq = freq._1-1
      if (newFreq == 0) {
        valCount.remove(largestFreqNum)
      }else {
        valCount += ((largestFreqNum,newFreq))
      }
      largestFreqNum
    }else {
      val currTime = freq._2.last
      val largestFreqNum = timeVal.get(currTime).get
      timeVal.remove(currTime)

      val newFreq = freq._1-1
      if (newFreq == 0) {
        valCount.remove(largestFreqNum)
      }else {
        valCount += ((largestFreqNum,newFreq))
      }

      freq._2.remove(currTime)
      largestFreqNum
    }
  }

}


/**
 * Your FreqStack object will be instantiated and called as such:
 * var obj = new FreqStack()
 * obj.push(`val`)
 * var param_2 = obj.pop()
 */