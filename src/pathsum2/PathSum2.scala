package pathsum2

import scala.collection.mutable.ListBuffer


class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
  def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
    val retValue = new ListBuffer[List[Int]]
    def sum(node : TreeNode,accumulated : Int,path : List[Int]) : Unit = {
      if (node == null) {

      }else {
        if (node.left == null && node.right == null) {
          if (accumulated + node.value == targetSum) {
            retValue.append((node.value::path).reverse)
          }
        }else {
          sum(node.left,accumulated+node.value,node.value :: path)
          sum(node.right,accumulated+node.value,node.value :: path)
        }
      }
    }

    sum(root,0,List())
    retValue.toList
  }
}