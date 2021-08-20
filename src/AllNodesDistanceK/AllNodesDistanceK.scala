package AllNodesDistanceK

import scala.collection.mutable.ListBuffer


class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null
}

object Solution {
  def distanceK(root: TreeNode, target: TreeNode, k: Int): List[Int] = {
    def path(node : TreeNode,lst : List[TreeNode]) : Option[List[TreeNode]] = {
      if (node == null) {
        None
      }else {
        if (node.value == target.value) {
          Some(node::lst)
        }else {
          val leftPath = path(node.left,node :: lst)
          val rightPath = path(node.right,node :: lst)

          (leftPath,rightPath) match {
            case (None,None) => None
            case (Some(lPath),None) => Some(lPath)
            case (_,Some(rPath)) => Some(rPath)
          }
        }
      }
    }

    val retValue = new ListBuffer[Int]

    def gather(node : TreeNode,netDistance : Int) : Unit = {
      if (node == null) {

      }else {
        //println(" " + node.value + " " + netDistance)
        if (netDistance == 0) {
          retValue.append(node.value)
        } else {
          gather(node.left, netDistance - 1)
          gather(node.right, netDistance - 1)
        }
      }
    }
    def iteratePath(pendingPath : List[TreeNode],netDistance : Int) : Unit = {
      if (netDistance <= k) {
        //println(pendingPath.head.value + " " + netDistance + " size " + pendingPath.length)
        if (netDistance == k) {
          retValue.append(pendingPath.head.value)
        }else {
          if (pendingPath.size == 1) {
            //retValue.append(pendingPath.head.value)
            gather(pendingPath.head.left,k-1)
            gather(pendingPath.head.right,k-1)
          }else {
            val next = pendingPath.tail.head
            if (pendingPath.head.left != null && pendingPath.head.left.value != next.value) {
              gather(pendingPath.head.left,k-netDistance-1)
            }else {
              if (pendingPath.head.right != null && pendingPath.head.right.value != next.value) {
                gather(pendingPath.head.right,k-netDistance-1)
              }

            }
          }
        }
      }
    }
    val p = path(root,List()).get //target is one of the value as constraint
    println(p)
    var prev = p.reverse
    while (prev.isEmpty == false) {
      iteratePath(prev,prev.length-1)
      prev = prev.tail
    }

    retValue.toList


  }
}
