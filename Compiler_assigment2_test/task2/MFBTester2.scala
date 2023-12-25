import MFBHelpers._
import MFBTests.{ getTestsNamed }

object MFBTester {

  import MFBTesters.{ runTest, processResults, storeTask }

  def main ( args : Array [ String ] ) : Unit = {
    try {
      val candidate = "anonymous"
      val taskName = "task2"
      val codegen = Task2.create ()
      val tests = getTestsNamed ( taskName, MFBTests.task2 ) 
      val results = processResults ( runTest ( candidate, taskName, codegen, tests ) )
      storeTask ( candidate, taskName, results )
    }
    catch {
      case e : Throwable => {
        println ( "Encontered problem in MFBTester2:run: " )
        println ( e.printStackTrace () )
        sys.exit( -1 ) } } }

}


