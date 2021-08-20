package EncodeDecodeString

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class Codec {




  def encodeCharToInt(stringBuilder: StringBuilder,value : Int) : Unit = {
    //addInt(stringBuilder,x)
    var xStr = value.toString
    val diffLen = 4-xStr.size
    xStr = "0" * diffLen + xStr
    stringBuilder.append(xStr)

  }

  def decodeLen(string: String, j : Int) : Int = {
    val l0 = string(j)
    val l1 = string(j+1)
    val l2 = string(j+2)
    val l3 = string(j+3)


    //val len = ((l0.asDigit << 24) & 0xff) | ((l1.asDigit << 16) & 0xff) | ((l2.asDigit << 8) & 0xff) | ((l3.asDigit << 0) & 0xff)
    val len = l0.toString + l1.toString + l2.toString + l3.toString
    Integer.parseInt(len)
  }

  def decodeChar(string: String, j : Int) : Char = {
    val l0 = string(j)
    val l1 = string(j+1)
    val l2 = string(j+2)
    val l3 = string(j+3)

//    val len = ((l0.asDigit << 24) & 0xff) | ((l1.asDigit << 16) & 0xff) | ((l2.asDigit << 8) & 0xff) | ((l3.asDigit << 0) & 0xff)
//    println("decode char ascii " + len + " " + l0.asDigit + " " + l1.asDigit + " " + l2.asDigit + " " + l3.asDigit)
//    len.toChar
    val len = l0.toString + l1.toString + l2.toString + l3.toString
    Integer.parseInt(len).toChar
  }
  def decodeString(string: String, j : Int) : (String,Int) = {
    val len = decodeLen(string, j)
    //println("decoded len " + len)
    //j+4
    val stringBuilder = new StringBuilder
    var ref = j+4
    for (k <- 0 to len-1) {
      val parsedChar = decodeChar(string,ref)
      stringBuilder.append(parsedChar)
      ref = ref+4
    }

    //println(stringBuilder.toString())
    (stringBuilder.toString(),ref)
  }


  def encodeString(stringBuilder: StringBuilder,s : String) : Unit = {
    val len = s.length
    encodeCharToInt(stringBuilder,len)

    for (ch <- s) {
      encodeCharToInt(stringBuilder,ch.toInt)
    }
  }

  // Decodes a single string to a list of strings.
  def decode(s: String): List[String] = {
      val lst = new ListBuffer[String]
      var ref = 0
    while (ref < s.length) {
      val (str,nextRef) = decodeString(s,ref)
      ref = nextRef
      lst.append(str)
    }

    lst.toList
  }

  // Encodes a list of strings to a single string.
  def encode(strs: List[String]): String = {
    val stringBuilder = new StringBuilder
    for (str <- strs) {
      encodeString(stringBuilder,str)
    }

    stringBuilder.toString()
  }


}

object Main {
  def main(args: Array[String]): Unit = {
    val codec = new Codec
   // val stringBuilder = new StringBuilder
    val x = "hi"
    val encodedStr = codec.encode(List("he","hi"))
    println(encodedStr.mkString(","))

    println(codec.decode(encodedStr))
//    codec.addInt(stringBuilder,x.length)
//
//    val s = stringBuilder.toString()
//    println(s)
//    println(codec.decodeInt(s))


  }
}
