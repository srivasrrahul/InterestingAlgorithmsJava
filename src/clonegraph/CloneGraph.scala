package clonegraph

//import scala.collection.mutable
//
//
//class Node(var _value: Int) {
//  var value: Int = _value
//  var neighbors: List[Node] = List()
//}
//
//
//object Solution {
//  def cloneGraph(graph: Node): Node = {
//    if (graph == null) {
//      null
//    }else {
//      val newMap = new mutable.HashMap[Int, Node]()
//
//      val visited = new mutable.HashSet[Int]()
//
//      def dfs(oldNode: Node): Unit = {
//        visited.add(oldNode.value)
//        val newNode = new Node(oldNode.value)
//        newMap += ((newNode.value, newNode))
//        for (neighbour <- oldNode.neighbors) {
//          if (visited.contains(neighbour.value) == false) {
//            dfs(neighbour)
//          }
//
//          val neighbourNewNode = newMap.get(neighbour.value).get //can it ever not exist??
//          newNode.neighbors = neighbourNewNode :: newNode.neighbors
//        }
//      }
//
//      dfs(graph)
//      newMap.get(graph.value).get
//    }
//  }
//}