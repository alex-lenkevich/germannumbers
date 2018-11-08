package util

import text2speech.AppleTextToSpeechClient

object SayMeWords extends App {
  val words =
    """ ich     | du       | er      | wie       | ihr     | Sie
      | fallen  | fällst   | fällt   | fallen    | fallt   | fallen
      | schlafe | schläfst | schläft | schlaffen | schlaft | schlafen
      | treffe  | triffst  | trifft  | treffen   | trefft  | treffen
      | helfen  | hilfst   | hilft   | helfen    | helft   | helfen
      | fahre   | fährst   | fährt   | fahren    | fahrt   | fahren
      | spreche | sprichst | spricht | sprechen  | sprecht | sprechen
      | wasche  | wäschst  | wäscht  | waschen   | wascht  | waschen
      | gebe    | gibst    | gibt    | geben     | gebt    | geben
      | nehme   | nimst    | nihmt   | nehmen    | nehmt   | nehmen
      | trage   | trägst   | trägt   | tragen    | tragt   | tragen
      | sehe    | siehts   | sieht   | sehen     | seht    | sehen
      | laufe   | läufst   | läuft   | laufen    | lauft   | laufen
      | lese    | liest    | liest   | lesen     | lest    | lesen
      | esse    | isst     | isst    | essen     | esst    | essen
      | lasse   | lässt    | lässt   | lassen    | lasst   | lassen """.stripMargin

  val header :: lines = words.split("\n").toList.map(_.split("\\s*\\|\\s*").map(_.trim).toList).filter(_.nonEmpty)

  lines.foreach { line =>
    header.zip(line).foreach { case (pronoun, verb) =>
      AppleTextToSpeechClient.speak(s"$pronoun $verb")
    }
  }

}
