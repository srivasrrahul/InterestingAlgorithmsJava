package coinschange

import scala.collection.mutable

case class Pair(val pending : Int,val max : Int)
object Solution {
  def change(amount: Int, coins: Array[Int]): Int = {
    val visited = new mutable.HashSet[Int]()
    val cache = new mutable.HashMap[Pair,Option[Int]]()
    def itr(pending : Int,max : Int) : Option[Int] = {

      pending match {
        case lesszero if pending < 0 => None
        case _ => {
          val pair = new Pair(pending, max)
          if (cache.contains(pair)) {
            cache.get(pair).get
          } else {
            var total = 0
            for (coin <- coins if coin <= max) {
              coin match {
                case pendingZero if coin - pending == 0 => {
                  total += 1
                }
                case _ => {

                  val p = itr(pending - coin, math.min(max, coin))
                  if (p.isDefined) {
                    total = total + p.get
                  }
                }
              }
            }

            if (total > 0) {
              cache.put(pair,Some(total))
              Some(total)
            } else {
              cache.put(pair,None)
              None
            }
          }
        }
      }
    }


    if (amount == 0) {
      1
    }else {
      itr(amount, Int.MaxValue) match {
        case Some(x) => x
        case _ => 0
      }
    }
  }
}
