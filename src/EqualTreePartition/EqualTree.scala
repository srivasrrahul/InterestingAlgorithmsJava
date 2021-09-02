package EqualTreePartition



class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
  def checkEqualTree(root: TreeNode): Boolean = {
    var treeSum  = 0
    var negative = 0
    var positive = 0
    def totalSum(node : TreeNode) : Unit = {

      if (node != null) {
        if (node.value < 0) {
          negative = negative + node.value
        }else {
          positive = positive + node.value
        }
        //treeSum = treeSum+node.value
        totalSum(node.left)
        totalSum(node.right)
      }
    }


    totalSum(root)
    treeSum = negative + positive
    //println(treeSum + " " + negative+ " " + positive)

    def itr(node : TreeNode) : Option[(Boolean,Int)] = {
      if (node == null) {
        None
      }else {
        val lResult = itr(node.left)
        val rResult  = itr(node.right)

        (lResult,rResult) match {
          case (None,None) => Some(false,node.value)
          case (Some((lFound,lSum)),None) => {
            if (lFound == true) {
              lResult
            }else {
              //println(node.value + " " + lSum)
              if (lSum == treeSum-lSum) {

                //println("test")
                Some(true,lSum + node.value)
              }else {
                Some(lFound, lSum + node.value)
              }
            }
          }
          case (None,Some((rRes,rSum))) => {
            if (rRes == true) {
              rResult
            }else {
              if (rSum == treeSum-rSum) {
                Some(true,rSum+node.value)
              }else {
                Some((rRes,rSum+node.value))
              }

            }
          }

          case (Some((lFouund,lSum)),Some((rFound,rSum))) => {
            val net = node.value + lSum + rSum
            if (lFouund == true || rFound == true) {
              Some(true,net)
            }else {

              if (lSum == treeSum-lSum) {
                Some(true,net)
              }else {
                if (treeSum-rSum == rSum) {
                  Some(true,net)
                }else {
                  Some(false,net)
                }
              }
            }
          }
        }

      }
    }

    itr(root).get._1
  }
}
