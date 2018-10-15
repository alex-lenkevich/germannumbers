package text2speech

import com.google.cloud.texttospeech.v1._
import javax.sound.sampled.AudioSystem

import scala.util.Try

class TextToSpeechClient {

  val textToSpeechClient = TextToSpeechClient.create

  def say(text: String): Try[Any] = {
    try {
      Try {
        val input = SynthesisInput.newBuilder.setText(text).build
        val voice = VoiceSelectionParams.newBuilder.setLanguageCode("de-DE").setSsmlGender(SsmlVoiceGender.MALE).build
        // Select the type of audio file you want returned
        val audioConfig = AudioConfig.newBuilder.setAudioEncoding(AudioEncoding.MP3).build
        // Perform the text-to-speech request on the text input with the selected voice parameters and
        // audio file type
        val response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig)
        // Get the audio contents from the response
        val audioContents = response.getAudioContent.newInput()
        // Write the response to the output file.

        val audioInputStream = AudioSystem.getAudioInputStream(response.getAudioContent.newInput())
        val clip = AudioSystem.getClip
        clip.open(audioInputStream)
//        val floatGainControl = clip.getControl(FloatControl.Type.MASTER_GAIN).asInstanceOf[FloatControl]
//        floatGainControl.setValue(gainControl)  //reduce volume by x decibels (like -10f or -20f)
        clip.start()
      }
    }
  }

}
