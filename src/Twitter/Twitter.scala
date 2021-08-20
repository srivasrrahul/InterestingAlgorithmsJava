package  Twitter

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


class TweetLog(val userId : Int) {
  val lst = ArrayBuffer[(Int,Int)]()

  def append(time : Int,tweetId : Int) : Unit = {
    lst.append((time,tweetId))
  }

  def getTweet(n : Int = 10,pageToken : Int = -1) : (List[(Int,Int)],Int) = {
    if (pageToken == -1) {
      (lst.takeRight(n).toList,lst.size-n)
    }else {
      val retValue = new ListBuffer[(Int,Int)]
      for (j <- pageToken to 0 by -1 if retValue.size < n && j < lst.length) {
        retValue.append(lst(j))
      }

      (retValue.toList,pageToken-n)
    }
  }
}

class FeedCreator(val accessibleTweets : List[TweetLog]) {
  def fetch(n : Int = 10) : List[Int] = {
    val retValue = new ListBuffer[Int]

    val pageSize = new mutable.HashMap[Int,Int]() //user => pageSize
    val pageTokens = new mutable.HashMap[Int,Int]() //user => pageToken
    val sortedLst = new mutable.TreeMap[Int,(Int,Int)]() //tweetime => (tweetID,userID)
    val providers = new mutable.HashMap[Int,TweetLog]()
//    for (log <- accessibleTweets) {
//      val (lst,newPageToken) = log.getTweet(n,-1)
//      lst.foreach(x => sortedLst += ((x._1,x._2)))
//      pageTokens += ((log.userId,newPageToken))
//      pageSize += ((log.userId,sortedLst.size))
//      providers += ((log.userId,log))
//    }

     while (retValue.size < n && pageSize.isEmpty == false && sortedLst.size > 0) {

       val top = sortedLst.last
       sortedLst.remove(top._1)
       retValue.append(top._2._1)

       val userId = top._2._2
       val newPageSize = pageSize.get(userId).get-1
       if (newPageSize > 0) {
         pageSize += ((userId,newPageSize))
       }else {
         pageSize.remove(userId)
         val oldPageToken = pageTokens.get(userId).get
         val provider = providers.get(userId).get
         val (lst,newPageToken) = provider.getTweet(n,oldPageToken)
         if (lst.size > 0) {
           pageTokens += ((userId,newPageToken))
           pageSize += ((userId,lst.size))
         }else {
           pageTokens.remove(userId)
           pageSize.remove(userId)
         }
       }
     }

    retValue.toList
  }
}

class Following(val _userID : Int) {
  protected val following = new mutable.HashSet[Int]()
  def addFollowing(other : Int) : Unit = {
    following.add(other)
  }

  def removeFollowing(other : Int) : Unit = {
    following.remove(other)
  }

  def getAll() : Set[Int] = {
    following.toSet
  }
}
class Twitter() {

  /** Initialize your data structure here. */
  var time = 0
  def newTime() : Int = {
    time = time+1
    time
  }

  val userLogs = new mutable.HashMap[Int,TweetLog]()
  val following = new mutable.HashMap[Int,Following]()
  /** Compose a new tweet. */
  def postTweet(userId: Int, tweetId: Int) {
    userLogs.getOrElseUpdate(userId,new TweetLog(userId)).append(newTime(),tweetId)
  }

  /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
  def getNewsFeed(userId: Int): List[Int] = {
    val feedCreator = new FeedCreator(following.getOrElseUpdate(userId,new Following(userId)).getAll().foldRight(new ListBuffer[TweetLog])((kv,lst) => {
      lst.append(userLogs.getOrElse(kv,new TweetLog(kv)))
    }).append(userLogs.getOrElseUpdate(userId,new TweetLog(userId))).toList)

    feedCreator.fetch(10)


  }

  /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
  def follow(followerId: Int, followeeId: Int) {
    following.getOrElseUpdate(followerId,new Following(followerId)).addFollowing(followeeId)
  }

  /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
  def unfollow(followerId: Int, followeeId: Int) {
    following.getOrElseUpdate(followerId,new Following(followerId)).removeFollowing(followeeId)
  }

}