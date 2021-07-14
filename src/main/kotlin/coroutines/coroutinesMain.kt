package coroutines
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
  withContext(Dispatchers.IO) {
    val time = measureTimeMillis {
      demoNumbersWithConflation()
    }
    println("running in $time")
  }
}

suspend fun demoFlowWithReduce() {
  val sum = (1..5).asFlow()
    .map { it * it }
    .reduce { a, b ->
      println(" $a + $b = ${a + b}")
      a + b
    }
  println("sum: $sum")
}

suspend fun demoFlowWithTransform() {
  (1..5).asFlow()
    .transform { request ->
      emit("request $request")
      emit(12)
    }
    .collect {
      println(it)
    }
}

suspend fun demoNumbersWithCollectLatest() {
  numbers()
    .filter { it % 2 == 0 }
    .map {
      "request $it"
    }
    .collectLatest { println(it) }
}

suspend fun demoNumbersWithConflation() {
  val time = measureTimeMillis {
    simple()
      .conflate() // conflate emissions, don't process each one
      .collect { value ->
        delay(300) // pretend we are processing it for 300 ms
        println(value)
      }
  }
  println("Collected in $time ms")

//  (1..10).asFlow()
//    .conflate() // it make process time become longer
//    .filter { it % 2 == 0 }
//    .map {
//      "request $it"
//    }
//    .collect {
//      println(it)
//    }
}

suspend fun demoNumbers() {
  numbers()
    .filter { it % 2 == 0 }
    .map {
      "request $it"
    }
    .collect { println(it) }
}

suspend fun simple(): Flow<Int> = flow {
  (0..20).forEach { emit(it) }
}

fun numbers(): Flow<Int> = flow {
  try {
    emit(1)
    emit(2)
    emit(3)
    emit(4)
    emit(5)
    emit(6)
  }finally {
  }
}

