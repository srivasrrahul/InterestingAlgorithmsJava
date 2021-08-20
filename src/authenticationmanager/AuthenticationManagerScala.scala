package authenticationmanager

import scala.collection.mutable

class AuthenticationManager(_timeToLive: Int) {

  val expiryTokens = new mutable.TreeMap[Integer,mutable.HashSet[String]]()
  val idMap = new mutable.HashMap[String,Integer]()

  def generate(tokenId: String, currentTime: Int) {
    idMap += ((tokenId,currentTime+_timeToLive))
    val set = expiryTokens.getOrElseUpdate(currentTime+_timeToLive,new mutable.HashSet[String]())
    set.add(tokenId)
  }

  def renew(tokenId: String, currentTime: Int) {
    //clear
    idMap.get(tokenId) match {
      case Some(oldTTl) => {
        if (currentTime <= oldTTl) {
          val oldSet = expiryTokens.get(oldTTl).get
          oldSet.remove(tokenId)
          if (oldSet.isEmpty) {
            expiryTokens.remove(oldTTl)
          }

          generate(tokenId,currentTime)
        }else {
          idMap.remove(tokenId)
          val oldSet = expiryTokens.get(oldTTl).get
          oldSet.remove(tokenId)
          if (oldSet.isEmpty) {
            expiryTokens.remove(oldTTl)
          }

        }
      }
      case _ => {

      }
    }
  }

  def countUnexpiredTokens(currentTime: Int): Int = {
    println(currentTime)
    println(expiryTokens)
    println(idMap)
    val pendingMap = expiryTokens.rangeTo(currentTime+_timeToLive)
    var count = 0
    pendingMap.foreach((entry) => {
      if (entry._1 <= currentTime) {
        count += entry._2.size
      }
    })

    count
  }

}


/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * var obj = new AuthenticationManager(timeToLive)
 * obj.generate(tokenId,currentTime)
 * obj.renew(tokenId,currentTime)
 * var param_3 = obj.countUnexpiredTokens(currentTime)
 */