package LogStorageSystem



import java.text.SimpleDateFormat
import java.util.Calendar
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class LogSystem() {

  val orderedLogs = new mutable.TreeMap[Long, ListBuffer[Int]]()
  def convertTimeStamp(str : String) : Long = {
    val simpleDate = new SimpleDateFormat("YYYY:MM:DD:HH:MM:SS")
    simpleDate.parse(str).getTime
  }
  def put(id: Int, timestamp: String)  : Unit = {
    val time = convertTimeStamp(timestamp)
    val lst = orderedLogs.getOrElseUpdate(time, new ListBuffer[Int])
    lst.append(id)
  }


  def retrieve(start: String, end: String, granularity: String): List[Int] = {

    def getFormat() : SimpleDateFormat = {
      granularity match {
        case "Day" => {
          new SimpleDateFormat("YYYY:MM:DD")
          //val startDate = p.parse(start)
        }
        case "Hour" => {
          new SimpleDateFormat("YYYY:MM:DD:HH")
        }
        case "Minute" => {
          new SimpleDateFormat("YYYY:MM:DD:HH:MM")
        }
        case "Second" => {
          new SimpleDateFormat("YYYY:MM:DD:HH:MM:SS")
        }
      }
    }

    val p = getFormat()
    val d = p.parse(start)

    val low = p.parse(start).getTime
    val high = p.parse(end).getTime
    val map = orderedLogs.range(low,high+1)
    val retValue = new ListBuffer[Int]
    for ((_,v) <- map) {
      retValue.addAll(v)
    }

    retValue.toList
  }

}
