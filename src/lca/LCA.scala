package lca



class Node(var _value: Int) {
  var value: Int = _value
  var left: Node = null
  var right: Node = null
  var parent: Node = null
}


object Solution {
  def lowestCommonAncestor(p: Node, q: Node): Node = {
    def path(node : Node) : List[Node] = {
      if (node.parent == null) {
        List(node)
      }else {
        val upList = path(node.parent)
        node::upList
      }
    }

    val left = path(p).reverse
    val right = path(q).reverse

    var lca = left.head
    var r = right
    var found = false
    for (l <- left if found == false) {
      if (r.isEmpty == false) {
        if (l.value == r.head._value) {
          r = r.tail
          lca = l
        } else {
          found = true
        }
      }else {
        found = true
      }
    }

    lca
  }
}
