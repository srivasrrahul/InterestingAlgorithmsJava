package minheighttrees

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Graph(val _n : Int) {
  val edges = new mutable.HashMap[Int,mutable.HashSet[Int]]
  for (j <- 0 to _n-1) {
    edges += ((j,new mutable.HashSet[Int]()))
  }

  def addEdge(u : Int,v : Int) : Unit = {
    edges(u).add(v)
  }

  def removeNode(u : Int) : Unit = {
    for (v <- edges.get(u).get) {
      edges.get(v).get.remove(u)
    }

    edges.remove(u)
  }




}
object Solution {
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    //Sort by degress
    val graph = new Graph(n)
    for (e <- edges) {
      graph.addEdge(e(0),e(1))
      graph.addEdge(e(1),e(0))
    }


    var degreeLst = new mutable.TreeMap[Int,mutable.HashSet[Int]]()

    for ((v,e) <- graph.edges) {
      val degrees = e.size
      val nodeLst = degreeLst.getOrElseUpdate(degrees,new mutable.HashSet[Int]())
      nodeLst.add(v)
    }

    while (degreeLst.size > 1) {
      val currentLast = degreeLst.head._2
       for (current <- currentLast) {
         graph.removeNode(current)
       }

      val degreeLst1 = new mutable.TreeMap[Int,mutable.HashSet[Int]]()

      for ((v,e) <- graph.edges) {
        val degrees = e.size
        val nodeLst = degreeLst1.getOrElseUpdate(degrees,new mutable.HashSet[Int]())
        nodeLst.add(v)
      }

      degreeLst = degreeLst1

    }


    degreeLst.head._2.toList




  }
}
