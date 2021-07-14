package coroutines.data

data class Port(
  var portId: String = "UKSIN",
  var voyage: String = "9999",
  var direction: String = "W",
  var callingIndication: Int = 1,
)