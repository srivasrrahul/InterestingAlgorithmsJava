package CBTInserter



class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

class CBTInserter(_root: TreeNode) {
  def maxDepth(node : TreeNode) : Int = {
    if (node == null) {
      0
    }else {
      val leftMax = maxDepth(node.left)
      val rightMax = maxDepth(node.right)

      math.max(leftMax,rightMax)+1
    }
  }

  def getFirstPathLessDepth(node : TreeNode,depthTillNow : Int) : Option[TreeNode] = {
    if (node == null) {
      if (depthTillNow == 0) {
        None
      }else {
        Some(null)
      }
    }else {
      val lPath = getFirstPathLessDepth(node.left,depthTillNow-1)
      lPath match {
        case None => {
          val rPath = getFirstPathLessDepth(node.right,depthTillNow-1)
          rPath match {
            case None => {
              None
            }
            case Some(y) => {
              if( y == null) {
                Some(node)
              }else {
                Some(y)
              }
            }
          }
        }
        case Some(x) => {
          if (x == null) {
            Some(node)
          }else {
            Some(x)
          }
        }
      }
    }
  }
  def addNode(node : TreeNode,value : Int) : Unit = {
    if (node.left == null) {
      node.left = new TreeNode(value)
    }else {
      node.right = new TreeNode(value)
    }
  }

  def getleftMost(node : TreeNode) : TreeNode = {
    if (node.left != null) {
      getleftMost(node.left)
    }else {
      node
    }
  }
  def insert(value : Int): Int = {
    val mDepth = maxDepth(_root)
    val node = getFirstPathLessDepth(_root,mDepth)
    node match {
      case None => {
        val leftMost = getleftMost(_root)
        addNode(leftMost,value)
        leftMost.value
      }
      case Some(lastNode) => {
        addNode(lastNode,value)
        lastNode.value
      }
    }


  }

  def get_root(): TreeNode = {
    _root
  }

}
