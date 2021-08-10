package filesystem

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait INode {
  def getAllINodes() : List[INode]
  def addINode(key : String,value : INode) : Boolean
  def getId() : Int
  def getINode(key : String) : Option[INode]
}

class Dir(val _v : Int) extends INode {
  val inodes = new mutable.HashMap[String,INode]()
  override def getAllINodes(): List[INode] = {
    inodes.foldRight(new ListBuffer[INode])((kv,lstBuffer) => {
      lstBuffer.append(kv._2)
    }).toList
  }

  override def addINode(key: String, iNode: INode): Boolean = {
    inodes.get(key) match {
      case None => {
        inodes += ((key,iNode))
        true
      }
      case _ => {
        false
      }
    }
  }

  override def getId(): Int = _v

  override def getINode(key: String): Option[INode] = {
    inodes.get(key)
  }
}
class FileSystem() {

  val rootNode : INode = new Dir(1)

  def getFinalPath(path : String) : Option[(INode,String)] = {
    val pathLst = path.split("/")
    if (pathLst.size == 1) {
      None
    }else {
      var itr = rootNode
      var validPath = true
      for (j <- 1 to pathLst.length-2 if validPath == true) {
        itr.getINode(pathLst(j)) match {
          case None => {
            validPath = false
          }
          case Some(subNode) => {
            itr = subNode
          }
        }
      }

      if (validPath) {
        Some(itr,pathLst.last)
      }else {
        None
      }
    }

  }

  def createPath(path: String, value: Int): Boolean = {
     getFinalPath(path) match {
       case None => false
       case Some((lastNode,lastPath)) => {
         lastNode.addINode(lastPath,new Dir(value))
       }
    }
  }

  def get(path: String): Int = {
    getFinalPath(path) match {
      case None => -1
      case Some((secondLastNode,lastPath)) => {
        secondLastNode.getINode(lastPath) match {
          case None => -1
          case Some(lastNode) => lastNode.getId()
        }
      }
    }
  }

}

object Solution {
  def main(args: Array[String]): Unit = {
    val fs = new FileSystem
    fs.createPath("/a",1)
    fs.createPath("/a/b",2)
    println(fs.get("/a"))
    println(fs.get("/a/b"))
    println(fs.get("/a/b/c"))
    fs.createPath("/a/c",3)
    println(fs.get("/a/c"))
  }
}