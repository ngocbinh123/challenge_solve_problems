package coroutines.repo

import coroutines.data.Port
import coroutines.data.PortActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.Date

class PortRepo {
  suspend fun getPortList(): Pair<List<Port>, List<PortActivity>> {
    return coroutineScope {
      println("get port list on ${Thread.currentThread().name}")
      val ports = async {
        (1..5).map{
          Thread.sleep(200)
          println("port: $it")
          Port(voyage = "${it}${it}${it}${it}")
        }
      }

      val activities = async {
        (1..5).map {
          println("activities: $it")
          PortActivity(Date().toString(), "event $it")
        }
      }

      return@coroutineScope Pair(ports.await(), activities.await())
    }
  }
  suspend fun getPortListSequence(): Pair<List<Port>, List<PortActivity>> {
    return coroutineScope {
      println("get port list on ${Thread.currentThread().name}")
      val ports = withContext(Dispatchers.Default) {
        (1..5).map {
          Thread.sleep(200)
          println("port: $it")
          Port(voyage = "${it}${it}${it}${it}")
        }
      }

      val activities = withContext(Dispatchers.Default) {
        (1..5).map {
          println("activities: $it")
          PortActivity(Date().toString(), "event $it")
        }
      }
      return@coroutineScope Pair(ports, activities)
    }
  }
}