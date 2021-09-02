package TwoSumBST4



class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
  def search(node : TreeNode,target : Int) : Boolean = {
    if (node == null) {
      false
    }else {
      if (node.value > target) {
        search(node.left,target)
      }else {
        node.value == target || search(node.right,target)
      }
    }
  }
  def findTarget(root: TreeNode, k: Int): Boolean = {
    var found = false
    def itr(node : TreeNode) : Unit = {
      if (node == null || found == true) {
        //terminate
      }else {

        val diff = k-node.value
        //println("Searching " + diff)
        if (diff == node.value) {

        }else {
          val retValue = search(root,diff)
          if (retValue) {
            found = true
          }
        }

        itr(node.left)
        itr(node.right)

        //println("Searching " + diff)

      }
    }

    itr(root)
    found
  }
}

