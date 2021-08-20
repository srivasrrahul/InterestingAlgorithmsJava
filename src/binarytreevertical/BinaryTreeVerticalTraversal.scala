package binarytreevertical

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right

}

object Solution {
  def verticalOrder(root: TreeNode): List[List[Int]] = {
    val map = new mutable.TreeMap[Int,mutable.TreeMap[Int,ListBuffer[Int]]]()


    def itr(node : TreeNode,verticalLevel : Int,horizontalLevl : Int) : Unit = {
      if (node == null) {

      } else {
        val horizontalMap = map.getOrElseUpdate(verticalLevel,new mutable.TreeMap[Int,ListBuffer[Int]])
        val lstBuf = horizontalMap.getOrElseUpdate(horizontalLevl,new ListBuffer[Int])
        lstBuf.append(node.value)
        itr(node.left,verticalLevel-1,horizontalLevl+1)
        itr(node.right,verticalLevel+1,horizontalLevl+1)
      }
    }

    itr(root,0,0)
    val retValue = new ListBuffer[List[Int]]
    for (entry <- map) {
      val lst = new ListBuffer[Int]
      for (e <- entry._2) {
        lst.addAll(e._2)
      }

      retValue.append(lst.toList)
    }

    retValue.toList
  }

}
