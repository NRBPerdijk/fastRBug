import org.graalvm.polyglot.{Context, Source}

object Main extends App {
  val graalContext: Context = Context.newBuilder().allowAllAccess(true).build
  val sourceFirstR: Source = Source.newBuilder("R", Main.getClass.getResource("r/rFunction.R")).build()
  val funcFirstRSimpleTuple: ((String, String)) => String = graalContext.eval(sourceFirstR).as(classOf[((String, String)) => String])

  funcFirstRSimpleTuple(("First", "Call"))

  val funcFirstRComplexTuple: ((String, Int, String)) => String = graalContext.eval(sourceFirstR).as(classOf[((String, Int, String)) => String])

  funcFirstRComplexTuple(("First", 2, "Call"))
}
