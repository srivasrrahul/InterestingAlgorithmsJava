package productofnumbers

import scala.collection.mutable.ArrayBuffer

case class Index(val start : Int,val end : Int)

class ProductOfNumbers() {
  val prod = new ArrayBuffer[Int]()
  def add(num: Int) {
    prod.append(num)
  }

  def getProduct(k: Int): Int = {
    var j = prod.length-1
    var count = 0
    var retValue = 1
    while (count < k && retValue != 0) {
      retValue = retValue*prod(j)
      j = j - 1
      count = count+1
    }

    retValue
  }

}
