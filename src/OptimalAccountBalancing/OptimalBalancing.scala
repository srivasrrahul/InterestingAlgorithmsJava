package OptimalAccountBalancing

//package OptimalAccountBalancing

import scala.collection.mutable

object Solution {
  def minTransfers(transactions: Array[Array[Int]]): Int = {
    val balances = new mutable.HashMap[Int,Int]() //how much balance everybody has


    for (transaction <- transactions) {

      val from = transaction(0)
      val to = transaction(1)
      val amount = transaction(2)

      val fromBalance = balances.getOrElseUpdate(from,0)
      balances += ((from,fromBalance-amount))

      val toBalance = balances.getOrElseUpdate(to,0)
      balances += ((to,toBalance+amount))
    }

    val updatedBalances = new mutable.HashMap[Int,Int]()
    for ((person,amount) <- balances) {
      if (balances.contains(-amount)) {

      }else {

      }
    }
    //println(balances.mkString(","))

    var balanceArr = balances.values.toArray
    balanceArr.sortInPlace()(new Ordering[Int] {
      override def compare(x: Int, y: Int): Int = y.compare(x)
    })

    println(balanceArr.mkString(","))
    val endAmountEachShouldHave = 0

    var j = 0
    var k = balanceArr.length-1

    var timesTransfer = 0
    while (balanceArr.size >= 1 && j < k && balanceArr(j) > endAmountEachShouldHave) {

      val jDiffMean = balanceArr(j)-endAmountEachShouldHave
      val kDiffMean  = endAmountEachShouldHave-balanceArr(k)

      //println(j + " " + k + " " + jDiffMean + " " + kDiffMean)
      if (jDiffMean == kDiffMean) {
        //println("here")

        balanceArr(j) = 0
        balanceArr(k) = 0
        j = j+1
        k = k-1
        timesTransfer = timesTransfer + 1

      }else {
        if (jDiffMean > kDiffMean) {
          balanceArr(j) = balanceArr(j) - kDiffMean
          balanceArr(k) = 0
          k = k - 1
          timesTransfer = timesTransfer + 1
        } else {
          balanceArr(j) = 0
          balanceArr(k) = (balanceArr(k) + jDiffMean)
          j = j + 1
          timesTransfer = timesTransfer + 1
        }
      }

      println("After fixing " + balanceArr.mkString(",") + " " + j + " " + " " + k)



      balanceArr = balanceArr.slice(j,k+1)
      balanceArr.sortInPlace()(new Ordering[Int] {
        override def compare(x: Int, y: Int): Int = y.compare(x)
      })

      println("After splicing " + balanceArr.mkString(",") + " " + timesTransfer)

      println("=============")

      j = 0
      k = balanceArr.length-1
    }

    timesTransfer

  }

  //   def main(args: Array[String]): Unit = {
  //     //[[0,1,10],[2,0,5]]
  //     //[[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
  //     println(minTransfers(Array(Array(0,1,10),Array(2,0,5))))

  //     //println(minTransfers(Array(Array(0,1,10),Array(1,0,1),Array(1,2,5),Array(2,0,5))))
  //   }
}