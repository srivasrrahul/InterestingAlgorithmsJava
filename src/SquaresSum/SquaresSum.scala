package SquaresSum

import scala.collection.mutable

object Solution {
  def judgeSquareSum(c: Int): Boolean = {
    var j = 0

    val squares = new mutable.HashSet[Int]()
    squares.add(j*j)
    var pending = c-(j*j)
    var found = false
    while (pending >= 0 && found == false) {

      if (squares.contains(pending)) {
        found = true
      }

      squares.add(j*j)
      j = j + 1
      squares.add(j*j)
      pending=c-(j*j)
    }

    found
  }

  def main(args: Array[String]): Unit = {
    println(judgeSquareSum(2))
  }
}