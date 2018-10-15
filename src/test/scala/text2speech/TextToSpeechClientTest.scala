package text2speech

import org.testng.Assert._
import org.testng.annotations.Test

import scala.util.Failure
import scala.util.control.NonFatal

class TextToSpeechClientTest {


  @Test def testSay: Unit = {
    val client = new TextToSpeechClient()
    assertTrue(client.say("Hello").recoverWith {
      case NonFatal(e) => e.printStackTrace()
        Failure(e)
    }.isSuccess)
  }


}
