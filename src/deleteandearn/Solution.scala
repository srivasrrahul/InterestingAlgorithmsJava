package deleteandearn

import scala.collection.mutable

object Solution {
  def deleteAndEarn(nums: Array[Int]): Int = {
    val numMap = new mutable.HashMap[Int,Int]()
    for (num <- nums) {
      val count = numMap.getOrElseUpdate(num,0)
      numMap += ((num,count+1))
    }


    val cache = new mutable.HashMap[Map[Int,Int],Int]()
    def points(nMap : scala.collection.immutable.Map[Int,Int]) : Int = {
      if (nMap.size == 0) {
        0
      }else {
        //println(nMap)
        if (cache.contains(nMap)) {
          cache.get(nMap).get
        }else {
          var maxPoints = 0
          for (entry <- nMap) {
            val key = entry._1
            //delete key
            val count = entry._2

            var newMap = nMap
            if (count == 1) {
              newMap = nMap.-(key)
            } else {
              newMap = nMap.+((key, count - 1))

            }

            newMap = newMap.--(List(key-1,key+1))

            //println(key + " " + count + " " + newMap)
            val updatedPoint = key + points(newMap)

            if (updatedPoint > maxPoints) {
              maxPoints = updatedPoint
            }
          }

          cache += ((nMap,maxPoints))
          maxPoints
        }
      }
    }

    points(numMap.toMap)
  }
}
