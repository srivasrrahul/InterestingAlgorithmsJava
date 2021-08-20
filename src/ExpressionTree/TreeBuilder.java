package ExpressionTree;

import java.util.Stack;

abstract class Node {
    public abstract int evaluate();

};

class NodeDataVal extends Node {
    private int data;

    public NodeDataVal(int data) {
        this.data = data;
    }


    @Override
    public int evaluate() {
        return data;
    }
}

class NodePlusVal extends Node {
    private Node left;
    private Node right;

    public NodePlusVal(Node left, Node right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public int evaluate() {
        return left.evaluate()+ right.evaluate();
    }
}

class NodeMinusVal extends Node {
    private Node left;
    private Node right;

    public NodeMinusVal(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate()- right.evaluate();
    }
}

class NodeMulVal extends Node {
    private Node left;
    private Node right;

    public NodeMulVal(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate()* right.evaluate();
    }
}

class NodeDivEval extends Node {
    private Node left;
    private Node right;

    public NodeDivEval(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate()/ right.evaluate();
    }
}
/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input
 * and returns the expression tree represnting it as a Node.
 */

class Evaluator {
    int j = 0;
    //Stack<String> stringStack = new Stack<>();
    Stack<Node> nodeStack = new Stack<>();

    Node [] extractFirstNode() {
        Node secondNode = nodeStack.pop();
        Node firstNode = nodeStack.pop();

        return new Node[]{firstNode,secondNode};
    }
    Node buildTree(String[] postfix) {

        while (j < postfix.length) {
            String curr = postfix[j];

            if (curr.equals("+")) {

                Node [] arr = extractFirstNode();
                Node pluNode = new NodePlusVal(arr[0],arr[1]);
                nodeStack.push(pluNode);
            }else {
                if (curr.equals("-")) {
                    Node [] arr = extractFirstNode();
                    Node minusNode = new NodeMinusVal(arr[0],arr[1]);
                    nodeStack.push(minusNode);
                }else {
                   if (curr.equals("*")) {
                       Node [] arr = extractFirstNode();
                       Node mulNode = new NodeMulVal(arr[0],arr[1]);
                       nodeStack.push(mulNode);
                   }else {
                       if (curr.equals("/")) {
                           Node [] arr = extractFirstNode();
                           Node divNode = new NodeDivEval(arr[0],arr[1]);
                           nodeStack.push(divNode);
                       }else {
                           nodeStack.push(new NodeDataVal(Integer.parseInt(curr)));
                       }
                   }
                }
            }

            ++j;
        }

        return nodeStack.pop();
    }
};

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Evaluator evaluator = new Evaluator();
        return evaluator.buildTree(postfix);
    }
}
