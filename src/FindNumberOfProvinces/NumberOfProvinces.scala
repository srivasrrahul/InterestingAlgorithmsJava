package FindNumberOfProvinces

import scala.collection.mutable


class Union(val _n : Int) {
  val root = new Array[Int](_n)
  val rank = new Array[Int](_n)

  for (j <- 0 to _n-1) {
    root(j) = j
    rank(j) = 1
  }

  def union(j : Int,k : Int) : Unit = {
    //println("For " + j + " " + k )
//    println("Befor root " + root.mkString(","))
//    println("Before rank " + rank.mkString(","))
    val rootJ = find(j)
    val rootK = find(k)
    if (rootJ != rootK) {
      if (rank(rootJ) > rank(rootK)) {
        root(rootK) = rootJ
      }else {
        if (rank(rootK) > rank(rootJ)) {
          root(rootJ) = rootK
        }else {
          root(rootK) = rootJ
          rank(rootJ) = rank(rootJ)+1
        }
      }
    }

//    println("After root " + root.mkString(","))
//    println("After rank " + rank.mkString(","))
  }
  def find(x : Int) : Int = {
    var y = x
    while (y != root(y)) {
      y = root(y)
    }

    y
  }

  def isConnectd(x : Int,y : Int) : Boolean = {
    find(x) == find(y)
  }

  def count() : Int = {
    val diffRoots = new mutable.HashSet[Int]()
    for (j <- root) {
      diffRoots.add(find(j))
    }

    diffRoots.size
  }


  override def toString = s"Union(${root.mkString(",")}, ${rank.mkString(",")})"
}
object Solution {


  def findCircleNum(isConnected: Array[Array[Int]]): Int = {
    val union = new Union(isConnected.length)

    for (j <- 0 to isConnected.length-1) {
      for (k <- j+1 to isConnected.length-1) {
        if (isConnected(j)(k) == 1 && union.isConnectd(j,k) == false) {
          union.union(j,k)
        }
      }
    }

    //println(union)
    union.count()
  }

  def main(args: Array[String]): Unit = {
    println(findCircleNum(Array(Array(1,0,0,1),Array(0,1,1,0),Array(0,1,1,1),Array(1,0,1,1))))
  }
}
