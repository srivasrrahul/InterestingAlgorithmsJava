package recoverbst

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
  def recoverTree(root: TreeNode): Unit = {
    val arr = new ArrayBuffer[TreeNode]()
    val map = new mutable.HashMap[Int,TreeNode]()
    def itr(node : TreeNode) : Unit = {
      if (node == null) {

      }else {

        map += ((node.value,node))
        itr(node.left)
        arr.append(node)
        itr(node.right)
      }
    }

    itr(root)


    var found = false
    var first = -1
    var second = -1
    for (j <- 0 to arr.length-2 if found == false) {
      if (arr(j).value > arr(j+1).value) {
        second = arr(j+1).value
        if (first == -1) {
          first = arr(j+1).value
        }else {
          found = true
        }
      }
    }

    val fNode = map.get(first).get
    val sNode = map.get(second).get
    val temp = fNode.value
    fNode.value = sNode.value
    sNode.value = temp


  }


}
