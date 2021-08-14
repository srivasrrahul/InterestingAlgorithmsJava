package MapSumPairs

import scala.collection.mutable

class Node {
  val nodes = new mutable.HashMap[Char,Node]()
  var valueAssociated : Option[Int] = None

  def insert(str : String,j : Int,v : Int) : Unit = {
    if (j == str.length-1) {
      valueAssociated = Some(v)
    }else {
      val nextNode = nodes.getOrElseUpdate(str(j+1),new Node)
      nextNode.insert(str,j+1,v)
    }
  }

  def collect() : Int = {
    val pending = nodes.foldRight(0)((kv,s) => {
      s+kv._2.collect()
    })

    valueAssociated match {
      case None => pending
      case Some(value) => value+pending
    }

  }
  def sum(prefix : String,j : Int) : Int = {
    if (j == prefix.length - 1) {
      val sumPending = nodes.foldRight(0)((kv, s) => {
        s + kv._2.collect()
      })
      valueAssociated match {
        case None => sumPending
        case Some(value) => sumPending+value
      }
    } else {
      nodes.get(prefix(j + 1)) match {
        case None => 0
        case Some(nextNode) => nextNode.sum(prefix, j + 1)
      }
    }
  }
}

class RootNode {
  val nodes = new mutable.HashMap[Char,Node]()
  def insert(str : String,v : Int) : Unit = {
    val nextNode = nodes.getOrElseUpdate(str(0),new Node)
    nextNode.insert(str,0,v)
  }

  def sum(prefix : String) : Int = {
    nodes.get(prefix(0)) match {
      case None => 0
      case Some(nextNode) => nextNode.sum(prefix,0)
    }

  }
}
class MapSum() {

  /** Initialize your data structure here. */


    val rootNode = new RootNode
  def insert(key: String, v: Int) : Unit = {
    rootNode.insert(key,v)
  }

  def sum(prefix: String): Int = {
    rootNode.sum(prefix)
  }

}