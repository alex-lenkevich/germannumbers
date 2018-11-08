import java.util.Scanner

object Main extends App {
  //  new Session(Words).next()

//
//  val der = Seq(
//    "Fernseher",
//    "Wecker",
//    "Haartrockner",
//    "Kredit",
//    "Hosenbügler",
//    "Balkon",
//    "Internetanschluss",
//    "Zimmerschlüssel",
//    "Hauptbahnhof",
//    "Zimmersafe",
//    "Preis",
//    "Parkplatz",
//  )
//  val die = Seq(
//    "Tiefgarage",
//    "Adresse",
//    "Dusche",
//    "karte",
//    "Minibar",
//  )
//
//  val das = Seq(
//    "Zimmer",
//    "Hotel",
//    "Fitnesscenter",
//    "Hauptbahnhof",
//    "Radio",
//    "Bad",
//    "Stadtzentrum",
//    "Bett",
//    "Restaurant",
//    "Frühstück",
//  )
//
//
  //  def check(list: Seq[String], article: String) = list.
  //    map(x => client.requestGender(x).getOrElse("???") -> x).
  //    filterNot(_._1 == article).
  //    map(x => s"$article $x").
  //    foreach(println)
  //
  //
  //  check(der, "der")
  //  check(das, "das")
  //  check(die, "die")

  val client = new blabla.Client()
  val scanner = new Scanner(System.in)
  println("Input word")
  while (true) {
    val word = scanner.nextLine()
    word.split(" ", 2).toList match {
      case article :: word :: Nil if article.matches("ein|eine") =>
        val correctArticle = client.requestGender(word) match {
          case Some("F") => "eine"
          case Some("N") => "ein"
          case Some("M") => "ein"
          case None => "???"
        }
        if (correctArticle == article)
          println("RIGHT!")
        else
          println(s"$correctArticle $word")

      case article :: word :: Nil if article.matches("das|die|der") =>
        val correctArticle = client.requestGender(word) match {
          case Some("F") => "die"
          case Some("N") => "das"
          case Some("M") => "der"
          case None => "???"
        }
        if (correctArticle == article)
          println("RIGHT!")
        else
          println(s"$correctArticle $word")

      case word :: Nil =>
        val correctArticle = client.requestGender(word) match {
          case Some("F") => "die"
          case Some("N") => "das"
          case Some("M") => "der"
          case None => "???"
        }
        println(s"$correctArticle $word")
    }
  }
}
