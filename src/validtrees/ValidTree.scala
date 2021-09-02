package validtrees

import scala.collection.mutable

class Graph(val n : Int) {
  val nodes = new Array[mutable.HashSet[Int]](n)
  for (j <- 0 to n-1) {
    nodes(j) = new mutable.HashSet[Int]()
  }

  def add(u : Int,v : Int) : Unit = {
    nodes(u).add(v)
  }

  def getNeighbours(u : Int) : Set[Int] = {
    nodes(u).toSet
  }

}
object Solution {
  def validTree(n: Int, edges: Array[Array[Int]]): Boolean = {
    val graph = new Graph(n)
    for (edge <- edges) {
      graph.add(edge(0),edge(1))
      graph.add(edge(1),edge(0))
    }

    var cycleFound = false
    val visited = new mutable.HashSet[Int]()
    var time = 0
    def getTime() : Int = {
      time = time+1
      time
    }

    val post = new mutable.HashMap[Int,Int]
    val pre = new mutable.HashMap[Int,Int]
    val parent = new mutable.HashMap[Int,Int]
    def dfs(u : Int) : Unit  = {

      pre += ((u,getTime()))
      visited.add(u)

      for (v <- graph.getNeighbours(u) if cycleFound == false) {
        if (visited.contains(v) == false) {
          parent += ((v,u))
          dfs(v)
        }else {
          if (parent.contains(u) == true && parent.get(u).get != v && pre.contains(v)) {
           cycleFound = true
          }
        }
      }

      post += ((u,getTime()))
    }

    dfs(0)
    cycleFound == false && (parent.size == n-1)
  }
}
