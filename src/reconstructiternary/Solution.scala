package reconstructiternary

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Edge(_id : Int,_dest: String) {
  val id = _id
  val dest = _dest


  def canEqual(other: Any): Boolean = other.isInstanceOf[Edge]

  override def equals(other: Any): Boolean = other match {
    case that: Edge =>
      (that canEqual this) &&
        id == that.id
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(id)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
class Graph {
  var id = 0
  def getId() : Int = {
    id = id+1
    id
  }
  val nodes = new mutable.HashMap[String,mutable.TreeSet[Edge]]()
  def addEdge(source : String,dest : String) : Unit = {
    val edges = nodes.getOrElseUpdate(source,new mutable.TreeSet[Edge]()((x: Edge, y: Edge) => {
      x.dest.compare(y.dest) //ordering based on dest id
    }))

    edges.add(new Edge(getId(),dest))
  }

  def getNeigbours(source : String) : mutable.TreeSet[Edge]  = {
    if (nodes.contains(source)) {
      nodes.get(source).get
    }else {
      new mutable.TreeSet[Edge]()((x: Edge, y: Edge) => {
        x.dest.compare(y.dest) //ordering based on dest id
      })
    }
  }
}
object Solution {
  def findItinerary(tickets: List[List[String]]): List[String] = {
    val graph = new Graph
    for (ticket <- tickets) {
      graph.addEdge(ticket.head,ticket.tail.head)
    }

    graph.addEdge("__","JFK")

    val visited = new mutable.HashSet[Edge]()
    val path = new ListBuffer[String]
    def dfs(edge: Edge) : Unit = {
      visited.add(edge)
      path.append(edge.dest)

      val dest = edge.dest
      for (neighbour <- graph.getNeigbours(dest)) {
        if (visited.contains(neighbour) == false) {
          dfs(neighbour)
        }
      }
    }

    val neigbours = graph.getNeigbours("__")
    dfs(neigbours.head)
    path.tail.toList

  }
}
