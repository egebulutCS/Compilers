import MFBHelpers._
import MFBTests.{ getTestsNamed }

object MFBTester {

  import MFBTesters.{ runTest, processResults, storeTask }

  def main ( args : Array [ String ] ) : Unit = {
    try {
      val candidate = "anonymous"
      val taskName = "task3"
      val codegen = Task3.create ()
      val tests = getTestsNamed ( taskName, MFBTests.task3 ) 
      val results = processResults ( runTest ( candidate, taskName, codegen, tests ) )
      storeTask ( candidate, taskName, results )
    }
    catch {
      case e : Throwable => {
        println ( "Encontered problem in MFBTester3:run: " )
        println ( e.printStackTrace () )
        sys.exit( -1 ) } } }

}


