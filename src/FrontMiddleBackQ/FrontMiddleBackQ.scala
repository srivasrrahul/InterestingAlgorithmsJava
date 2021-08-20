package FrontMiddleBackQ

import scala.collection.mutable


class FrontMiddleBackQueue() {

  val MID = Int.MaxValue/2
  val leftOrderdSet = new mutable.TreeSet[Int]() //left from 1 to MID
  val rightOrderedSet = new mutable.TreeSet[Int]() //MID+1 to Int.MaxValue
  val timeVal = new mutable.HashMap[Int,Int]() //time => val
  val valTime = new mutable.HashMap[Int,mutable.HashSet[Int]]() //value => time

  var leftTime = 0
  def getNewLeftTime() : Int = {
    leftTime = leftTime-1
    leftTime
  }

  var midTime = MID
  def getNewMidTime() : Int = {
    midTime = midTime-1
    midTime
  }

  var lastTime = MID
  def getLastTime() : Int = {
    lastTime = lastTime+1
    lastTime
  }

  def pushFront(v: Int) {
    val newTime = getNewLeftTime()
    val timeSet = valTime.getOrElseUpdate(v,new mutable.HashSet[Int])
    timeSet.add(v)
    timeVal += ((newTime,v))
    leftOrderdSet.add(newTime)

    if (leftOrderdSet.size > rightOrderedSet.size+1) {
      val leftLastTime = leftOrderdSet.last
      rightOrderedSet.add(leftLastTime)
      leftOrderdSet.remove(leftLastTime)
    }
  }

  def pushMiddle(v: Int) {
    val newMidTime= getNewMidTime()
    val timeSet = valTime.getOrElseUpdate(v,new mutable.HashSet[Int])
    timeSet.add(v)
    timeVal += ((newMidTime,v))
    leftOrderdSet.add(newMidTime)

    if (leftOrderdSet.size > rightOrderedSet.size+1) {
      val leftLastTime = leftOrderdSet.last
      rightOrderedSet.add(leftLastTime)
      leftOrderdSet.remove(leftLastTime)
    }
  }

  def pushBack(v: Int) {

    val newRightTime = getLastTime()
    val timeSet = valTime.getOrElseUpdate(v,new mutable.HashSet[Int])
    timeSet.add(v)
    timeVal += ((newRightTime,v))

    rightOrderedSet.add(newRightTime)

    if (rightOrderedSet.size > leftOrderdSet.size) {
      val rightHead = rightOrderedSet.head
      leftOrderdSet.add(rightHead)
      rightOrderedSet.remove(rightHead)
    }

  }

  def popFront(): Int = {
    if (leftOrderdSet.size > 0) {
      val firstTime = leftOrderdSet.head
      leftOrderdSet.remove(firstTime)

      val firstVal = timeVal.get(firstTime).get
      timeVal.remove(firstVal)

      valTime.get(firstTime).get.remove(firstVal)

      if (rightOrderedSet.size > leftOrderdSet.size) {
        val rightHead = rightOrderedSet.head
        leftOrderdSet.add(rightHead)
        rightOrderedSet.remove(rightHead)
      }

      firstVal
    }else {
      -1
    }


  }

  def popMiddle(): Int = {
    if (leftOrderdSet.size > 0) {
      val lastTime = leftOrderdSet.last
      leftOrderdSet.remove(lastTime)

      val lastVal = timeVal.get(lastTime).get
      timeVal.remove(lastVal)

      valTime.get(lastTime).get.remove(lastVal)

      if (rightOrderedSet.size > leftOrderdSet.size) {
        val rightHead = rightOrderedSet.head
        leftOrderdSet.add(rightHead)
        rightOrderedSet.remove(rightHead)
      }

      lastVal
    }else {
      -1
    }
  }

  def popBack(): Int = {
    if (rightOrderedSet.size > 0) {
      val rightTime = rightOrderedSet.last
      rightOrderedSet.remove(rightTime)

      val rightVal = timeVal.get(rightTime).get
      timeVal.remove(rightVal)

      valTime.get(rightVal).get.remove(rightTime)

      rightVal
    }else {
      -1
    }
  }

}

