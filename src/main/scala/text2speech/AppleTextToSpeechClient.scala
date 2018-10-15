package text2speech

object AppleTextToSpeechClient {

  def speak(sentence: String) {
    if (sentence.matches(".*[A-Za-zaäöüsß]")) {
      val script: String = s"say -v Anna $sentence"
      val runtime = Runtime.getRuntime
      runtime.exec(script)
      Thread.sleep(2000)
    }
  }
}
