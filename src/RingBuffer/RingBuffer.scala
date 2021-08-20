package RingBuffer

class MyCircularQueue(_k: Int) {

  val q = new Array[Int](_k)
  var front = -1
  var rear = -1
  def enQueue(value: Int): Boolean = {
    if (front == -1 && rear == -1) {
      front = 0
      rear = 0
      q(front) = value
      true
    }else {
      val tempRear = (rear+1) % _k

      if (front == tempRear) {
        //full
        false
      }else {
        rear = tempRear
        q(rear) = value
        true
      }
    }
  }

  def deQueue(): Boolean = {
    if (front == -1 && rear == -1) {
      false
    }else {
      val retValue = q(front)

      if (front == rear) {
        //last element
        front = -1
        rear = -1
      }else {
        front = (front+1) % _k
      }

      //println("Deleting " + retValue)
      true
    }
  }

  def Front(): Int = {
    if (front == -1 && rear == -1) {
      -1
    }else {
      q(front)
    }
  }

  def Rear(): Int = {
    if (front == -1 && rear == -1) {
      -1
    }else {
      q(rear)
    }
  }

  def isEmpty(): Boolean = {
    front == -1 && rear == -1
  }

  def isFull(): Boolean = {
    isEmpty() == false && (front == ((rear+1)%_k))
  }

}

object Main {
  def main(args: Array[String]): Unit = {
    val s = new MyCircularQueue(10)
    for (j <- 1 to 10) {
      println(s.enQueue(j) == true)
    }

    println(s.enQueue(12) == false)

    for (j <- 1 to 10) {
      println(s.deQueue() == true)
    }
    println(s.deQueue() == false)

  }
}
