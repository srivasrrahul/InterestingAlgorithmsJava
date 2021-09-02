package ComplexNumberMultiplication

case class ComplexNum(real : Int, imaginary : Int)

object Solution {
  object Solution {
    def multiply(c1 : ComplexNum,c2 : ComplexNum) : ComplexNum = {
      val newReal = c1.real*c2.real-(c1.imaginary*c2.imaginary)
      val newImag = c1.real*c2.imaginary+c1.imaginary*c2.real
      ComplexNum(newReal,newImag)
    }

    def parseComplexNum(num : String) : ComplexNum = {
      val parsedStr = num.split("\\+")
      //println(parsedStr.mkString(","))
      val realStr = parsedStr(0)
      val complex = parsedStr(1).substring(0,parsedStr(1).length-1)
      //println(complex)
      ComplexNum(realStr.toInt,complex.toInt)

    }
    def complexNumberMultiply(num1: String, num2: String): String = {
      val c1 = parseComplexNum(num1)
      val c2 = parseComplexNum(num2)
      val c3 = multiply(c1,c2)
      c3.real+"+"+c3.imaginary+"i"
    }

    def main(args: Array[String]): Unit = {
      println(complexNumberMultiply("1+1i","1+1i"))
    }
  }
}