package scala



class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right

  override def toString = s"TreeNode($value, $left, $right)"
}

object Solution {
  def str2tree(s: String): TreeNode = {
    if (s.isEmpty) {
      null
    }else {
      val s1 = "(" + s + ")"

      def itr(j: Int): Option[(Int, TreeNode)] = {
        //starts with '(' and ends with corresponding ')'
        //println(j)
        if (j >= s1.length) {
          None
        } else {
          var k = j + 1
          val nodeValStr = new StringBuilder
          while (k < s1.length && s1(k).isDigit) {
            nodeValStr.append(s1(k))
            k = k + 1
          }

          //println(nodeValStr)
          val nodeVal = nodeValStr.toString().toInt
          s1(k) match {
            case '(' => {
              val left = itr(k)
              k = left.get._1 + 1
              if (k < s1.length && s1(k) == '(') {
                val right = itr(k)
                Some((right.get._1 + 1, new TreeNode(nodeVal, left.get._2, right.get._2)))
              } else {
                Some((left.get._1 + 1, new TreeNode(nodeVal, left.get._2)))
              }
            }
            case ')' => {
              //finished
              Some((k, new TreeNode(nodeVal)))
            }
          }
        }
      }

      itr(0).get._2
    }
  }

  def main(args: Array[String]): Unit = {
    println(str2tree("4(2(3)(1))(6(5))"))
  }
}
