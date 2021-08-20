package FindAPathExistInGraph

import scala.collection.mutable

class Graph(val _n : Int) {
  val arr = new Array[mutable.HashSet[Int]](_n)

  for (j <- 0 to arr.length-1) {
    arr(j) = new mutable.HashSet[Int]()
  }

  def addEdge(u : Int,v : Int) : Unit = {
    arr(u).add(v)
  }

  def getNeigburs(u : Int) : Set[Int] = {
    arr(u).toSet
  }
}
object Solution {
  def validPath(n: Int, edges: Array[Array[Int]], start: Int, end: Int): Boolean = {
    val graph = new Graph(n)
    for (edge <- edges) {
      graph.addEdge(edge(0),edge(1))
      graph.addEdge(edge(1),edge(0))
    }

    val visited = new mutable.HashSet[Int]()

    var retValue = false
    def dfs(current : Int) : Unit = {
      if (current == end || retValue == true) {
        retValue = true
      }else {
        visited.add(current)

        for (v <- graph.getNeigburs(current)) {
          if (visited.contains(v) == false) {
            dfs(v)
          }
        }
      }
    }
    dfs(start)
    retValue
  }
}