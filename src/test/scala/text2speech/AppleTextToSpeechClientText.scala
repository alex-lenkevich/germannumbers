package text2speech

import org.testng.Assert._
import org.testng.annotations.Test

import scala.util.Failure
import scala.util.control.NonFatal

class AppleTextToSpeechClientText {


  @Test def testSay: Unit = {
    AppleTextToSpeechClient.speak("Ich bin Sandra")
  }


}
