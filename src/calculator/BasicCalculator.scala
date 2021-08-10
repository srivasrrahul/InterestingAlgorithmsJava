  package calculator

  import scala.collection.mutable
  import scala.collection.mutable.ListBuffer

  trait Token
  case class Number(x : Int) extends Token
  object Plus extends Token
  object Minus extends Token
  object Multiply extends Token
  object Div extends Token

  object Solution {
    def precedence(ch : Char) : Int = {
      if (ch == '*' || ch == '/') {
        2;
      }else {
        if (ch == '-' || ch == '+') {
          1
        }else {
          -1
        }
      }

    }

    def operandToken(ch : Char) : Token = {
      ch match {
        case '+' => Plus
        case '-' => Minus
        case '*' => Multiply
        case _ => Div
      }
    }


    def calculate(s1: String): Int = {
      val s2 = "(" + s1.replace(" ","") + ")"

      val stringBuilder = new StringBuilder()
      for (j <- 0 to s2.length-1) {
        if (s2(j) != '-') {
          stringBuilder.append(s2(j))
        }else {
          //unary operator highest priority
          if (s2(j-1).isDigit) {
            //binary operator
            stringBuilder.append('-')
          }else {
            stringBuilder.append("-1")
            stringBuilder.append("*")
          }
        }
      }


      val s = stringBuilder.toString()

      println(s)
      val postfix = new ListBuffer[Token]

      val token = new StringBuilder
      val stack = new mutable.Stack[Char]()

      var factor = 1

      for (j <- 0 to s.length-1) {
        s(j) match {
          case dig if s(j).isDigit => {
            token.append(s(j))
          }
          case '(' => {
            if (token.isEmpty == false) {
              postfix.append(Number(token.toString().toInt*factor))
              token.clear()
              factor = 1
            }

            stack.push(s(j))
          }
          case ')' => {
            if (token.isEmpty == false) {
              postfix.append(Number(token.toString().toInt*factor))
              token.clear()
              factor = 1
            }
            while (stack.top != '(') {
              //println(stack.top)
              postfix.append(operandToken(stack.pop()))
            }

            stack.pop()
          }
          case ' ' => {
            if (token.isEmpty == false) {
              postfix.append(Number(token.toString().toInt * factor))
              token.clear()
              factor = 1
            }
          }
          case '-' => {

            if (token.isEmpty && (j>=1 && s(j-1) == '(' )) {
              //println("here")
              factor = -1
            }else {
              if (token.isEmpty == false) {
                postfix.append(Number(token.toString().toInt * factor))
                factor = 1
                token.clear()
              }

              while (stack.isEmpty == false && (precedence(s(j)) <= precedence(stack.top))) {
                postfix.append(operandToken(stack.pop()))
              }

              stack.push(s(j))
            }
          }
          case _ => {
            //operand
            if (token.isEmpty == false) {
              postfix.append(Number(token.toString().toInt * factor))
              token.clear()
              factor = 1
            }


            while (stack.isEmpty == false && (precedence(s(j)) <= precedence(stack.top))) {
              postfix.append(operandToken(stack.pop()))
            }

            stack.push(s(j))

          }
        }
      }

      if (token.isEmpty == false) {
        postfix.append(Number(token.toString().toInt))
      }

      while (stack.isEmpty == false) {
        postfix.append(operandToken(stack.pop()))
      }

      println(postfix.mkString(","))

      val evalStack = new mutable.Stack[Int]()
      for (token <- postfix) {
        token match {
          case Number(x) => {
            evalStack.push(x)
          }
          case Plus => {
            val second = evalStack.pop()
            val first = evalStack.pop()
            evalStack.push(first+second)
          }
          case Minus => {
            val second = evalStack.pop()
            val first = evalStack.pop()
            evalStack.push(first-second)
          }
          case Multiply => {
            val second = evalStack.pop()
            val first = evalStack.pop()
            evalStack.push(first*second)
          }
          case Div => {
            val second = evalStack.pop()
            val first = evalStack.pop()
            evalStack.push(first/second)
          }
        }
      }
      evalStack.pop()

    }

    def main(args: Array[String]): Unit = {
      println(calculate("-(3+(4+5))"))
    }


  }