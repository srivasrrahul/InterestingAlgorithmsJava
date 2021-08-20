package traprainwater

object Solution {
  def trap(height: Array[Int]): Int = {
    val buildingArea = new Array[Int](height.length)
    buildingArea(0) = height(0)
    for (j <- 1 to buildingArea.length-1) {
      buildingArea(j) = buildingArea(j-1) + height(j)
    }

    def findBuildingArea(j : Int,k: Int) : Int = {
      if (j == 0) {
        height(0)
      }else {
        buildingArea(k)- buildingArea(j-1)
      }
    }

    def findWater(j : Int, k : Int) : Int = {
      val minHeight = math.min(height(j),height(k))
      minHeight*(k-j)-findBuildingArea(j,k-1)
    }

    var waterCount = 0
    var j = height.length-1
    while (j >= 0) {
      var smallestFound = -1
      for (k <- j-1 to 0 by -1 if smallestFound == -1) {
        if (height(k) <= height(k+1)) {

        }else {
          smallestFound = k+1
        }
      }

      println("for j " + j + " smallest " + smallestFound)
      if (smallestFound != -1 && smallestFound != j) {
        var largestFound = -1
        for (k <- smallestFound-1 to 0 by -1 if largestFound == -1) {
          if (height(k) <= height(j) && height(k) >= height(k+1)) {

          }else {
            largestFound = k+1
          }
        }


        if (largestFound != -1 && largestFound != smallestFound) {
          val newAdded = findWater(largestFound,j)
          println("for j " + j + " smallest " + smallestFound + " " + " largest " + largestFound + " new Added " + newAdded)
          waterCount = waterCount + newAdded
          j = largestFound
        }
      }else {
        j = j -1
      }
    }

    waterCount
  }

  def main(args: Array[String]): Unit = {
    println(trap(Array(0,1,0,2,1,0,1,3,2,1,2,1)))
  }
}
