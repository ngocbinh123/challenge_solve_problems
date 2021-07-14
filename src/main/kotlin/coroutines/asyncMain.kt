package coroutines

import coroutines.repo.PortRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
  val repo = PortRepo()
  withContext(Dispatchers.IO) {
    val time = measureTimeMillis {
      val model = repo.getPortListSequence()
      println("ports size: ${model.first.size}")
      println("histories size: ${model.second.size}")
    }
    println("running in $time")
  }
}

