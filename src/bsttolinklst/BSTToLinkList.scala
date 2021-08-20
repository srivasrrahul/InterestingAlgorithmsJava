package bsttolinklst



class Node(var _value: Int) {
  var value: Int = _value
  var left: Node = null
  var right: Node = null
}


object Solution {
  type Left = Node;
  type Right = Node
  def treeToDoublyList(root: Node): Node = {
    def itr(node : Node) : Option[(Left,Right)] = {
      node match {
        case null => {
          None
        }
        case _ => {
          val lVal = itr(node.left)
          val rVal = itr(node.right)

          (lVal,rVal) match {
            case (None,None) => {
              node.left = node
              node.right = node
              Some((node,node))
            }
            case (Some((lMin,lMax)),None) => {
              lMax.right = node
              node.left = lMax
              node.right = lMin
              lMin.left = node
              Some((lMin,node))
            }
            case (None,Some((rMin,rMax))) => {
              node.right = rMin
              rMin.left = node
              node.left = rMax
              rMax.right = node
              Some((node,rMax))
            }
            case (Some((lMin,lMax)),Some((rMin,rMax))) => {
              lMax.right = node
              node.left = lMax
              node.right = rMin
              rMin.left = node
              rMax.right = lMin
              lMin.left = rMax
              Some((lMin,rMax))
            }
          }
        }
      }
    }

    itr(root) match {
      case None => null
      case Some((lMin,_)) => lMin
    }
  }
}
