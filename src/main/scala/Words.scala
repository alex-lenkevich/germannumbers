import java.io._

import Exercise.{Method, Text2TextMethod, Voice2TextMethod}

import scala.util.Random

object Words extends Exercise[(String, Method)] {

  val StatsFileName = "./words.stats.dat"

  protected val words = Map(
//    "я" -> "ich",
//    "ты" -> "du",
//    "он" -> "er",
//    "она" -> "sie",
//    "оно" -> "es",
//    "мы" -> "wie",
//    "вы" -> "ihr",
//    "они" -> "sie",
//    "Вы" -> "Sie",
//
//    "кто" -> "wer",
//    "что" -> "was",
//    "кого" -> "wen",
//    "кому" -> "wem",
//    "чей" -> "wessen",
//    "где" -> "wo",
//    "куда" -> "wohin",
//    "откуда" -> "woher",
//    "когда" -> "wann",
//    "как долго" -> "wie lange",
//    "как часто" -> "wie oft",
//    "почему" -> "warum",
//    "как" -> "wie",
//    "сколько" -> "wie viel",
//    "какой" -> "welcher",
//    "какая" -> "welche",
//    "какое" -> "welches"

    "направо" -> "rechts",
    "налево" -> "links",
    "поворачивать" -> "abbiegen",
    "затем" -> "dann",
    "сперва" -> "zuerst",
    "затем" -> "anschließend",
    "потом" -> "danach",
    "снова" -> "wieder",
    "единожды" -> "einmal",
    "дважды" -> "zweimal",
    "светофор" -> "Ampel",
    "возможность" -> "möglichkeit",
    "перестраиваться" -> "einordnen",
    "полоса" -> "Fahrstreifen",
    "трек" -> "Spur",
    "сменять" -> "wechseln",
    "оставаться" -> "bleiben",
    "следовать" -> "folgen",
    "позже" -> "später",
    "настроить" -> "einstellen",
    "трогаться" -> "losfahren",
    "останавливаться" -> "anhalten",
    "задом" -> "rückwärts",
    "поворотник" -> "Blinker",
    "сцепление" -> "Kupplung",
    "нажимать ногой" -> "treten",
    "отпускать" -> "lösen",
    "медленно" -> "langsam",
    "тормоз" -> "Bremse",
    "удерживать" -> "festhalten",
    "зеркало" -> "Spiegel",
    "свет" -> "Licht",
    "руль" -> "Lenkrad",
    "оба" -> "beide",
    "передача" -> "Gang",
    "1я" -> "erster",
    "2я" -> "zweiter" ,
    "3я" -> "dritter",
    "4я" -> "vierter",
    "5я" -> "fünfter",
    "нейтральная" -> "leerlauf",
    "в направлении" -> "richtung",
    "съезд" -> "Verlassen",
    "покидать" -> "abfahren",
    "обратно" -> "umkehren",
    "развернуться" -> "wenden",
    "держаться близко" -> "dicht dran",
    "растояние" -> "Abstand",
    "готовый" -> "fertig",
    "отрегулированный" -> "eingestellt",
    "главная дорога" -> "Vorfahrtsstrasse",
    "преймущество проезда" -> "Vorfahrt",
    "курс" -> "Verlauf",
    "автомобиль" -> "Fahrzeug",
    "освещение" -> "Beleuchtung",
    "соответственно" -> "entsprechend",
    "загрузка" -> "Beladung",
    "стояночный свет" -> "das Standlicht",
    "ближний свет" -> "das Abblendlicht",
    "дальний свет" -> "das Fernlicht",
    "обогрев" -> "heizbare",
    "заднее стекло" -> "die Heckscheibe",
    "дворники" -> "der Scheibenwischer",
    "включать" -> "einschalten",
    "выключать" -> "ausschalten",
    "стояночный тормоз" -> "die Handbremse",
    "боковое стекло" -> "die Seitenscheibe",
    "наполовину" -> "halb",
    "вниз" -> "herunter",
    "аварийка" -> "das Warnblinklicht",
    "использовать" -> "benutzt"
  ).flatMap {case (k, v) => Seq(v -> k, k -> v)}

  private var weights = Map[String, Double]()

  if (new File(StatsFileName).exists()){
    val ois = new ObjectInputStream(new FileInputStream(StatsFileName))
    weights = ois.readObject.asInstanceOf[Map[String, Double]]
    ois.close()
  }


  override def nextQuestion(i: Int): Question = {
//    println(weights.toList.sortBy(-_._2))
    val wordsWeights: Map[String, Double] = words.keys.map(x => x -> weights.getOrElse(x, 1d)).toMap
    println(wordsWeights.toList.groupBy(_._2).mapValues(_.map(_._1)).toSeq.sortBy(_._1).map(x => s"${x._1}\n${x._2.mkString(", ")}").mkString("\n"))
    println("Progress: " + (wordsWeights.toList.map(_._2).sum / wordsWeights.size))
    println()
    val rand: Double = Random.nextDouble() * wordsWeights.values.sum
    val question = wordsWeights.foldLeft("" -> 0d){case ((rkey, sum), (key, v)) => (if(sum <= rand) key else rkey) -> (sum + v)}._1
    val method = if(Random.nextBoolean()) Text2TextMethod else Voice2TextMethod
    (question, method)
  }

  override def answer(t: Question): String = s"${words(t._1)}"

  override def correct(t: Question): Unit = {
    weights = weights + (t._1 -> weights.getOrElse(t._1, 1d) / 2)
    saveStats
  }

  override def wrong(t: Question): Unit = {
    weights = weights + (t._1 -> weights.getOrElse(t._1, 1d) * 2)
    saveStats
  }

  private def saveStats = {
    val oos = new ObjectOutputStream(new FileOutputStream(StatsFileName))
    oos.writeObject(weights)
    oos.close()
  }

  override def questionText(t: Question): String = t._1
}
