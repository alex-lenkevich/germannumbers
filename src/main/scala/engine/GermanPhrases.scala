package engine

import model._
import util.Table

object GermanPhrases {
//
//  val Pronomen = Table[Noun, Kasus](
//    List(Nominativ, Dativ  , Akkusativ),
//    (I   , List("ich"    , "mir"  , "mich"   )),
//    (YouS, List("du"     , "dir"  , "mich"   )),
//    (He  , List("er"     , "ihm"  , "ihn"    )),
//    (She , List("sie"    , "ihr"  , "sie"    )),
//    (It  , List("er"     , "ihm"  , "es"     )),
//    (We  , List("wir"    , "uns"  , "uns"    )),
//    (YouP, List("ihr"    , "euch" , "euch"   )),
//    (They, List("sie"    , "ihnen", "sie"    )),
//    (YouF, List("Sie"    , "ihnen", "sie"    )),
//  )
//
//  val Lieben = Table(
//           List(Nominativ, Dativ  , Akkusativ),
//    (I   , List("ich"    , "mir"  , "mich"   )),
//    (YouS, List("du"     , "dir"  , "mich"   )),
//    (He  , List("er"     , "ihm"  , "ihn"    )),
//    (She , List("sie"    , "ihr"  , "sie"    )),
//    (It  , List("er"     , "ihm"  , "es"     )),
//    (We  , List("wir"    , "uns"  , "uns"    )),
//    (YouP, List("ihr"    , "euch" , "euch"   )),
//    (They, List("sie"    , "ihnen", "sie"    )),
//    (YouF, List("Sie"    , "ihnen", "sie"    )),
//  )
//
//  val schwachesVerb = Map[Verb, String](Love -> "lieben")
//
//  def process(phrase: AffirmativeSentence) : String = {
//    s"${translateNounPhrase((phrase.actor, Nominativ))} ${translateVerbPhrase(null)}"
//  }
//
//  sealed trait Kasus
//  object Nominativ extends Kasus
//  object Genitv extends Kasus
//  object Dativ extends Kasus
//  object Akkusativ extends Kasus
//
//  sealed trait Geschlecht
//  object Maskulinum extends Geschlecht
//  object Femininum extends Geschlecht
//  object Neutrum extends Geschlecht
//
//  sealed trait Person
//  object FirstPerson extends Person
//  object SecondPerson extends Person
//  object ThirdPerson extends Person
//
//  sealed trait Numerus
//  object Singular extends Numerus
//  object Plural extends Numerus
//
//  case class NounDescription(geschlecht: Geschlecht, numerus: Numerus, kasus: Kasus)
//
//  val translateNoun: PartialFunction[(Noun, Kasus), String] = {
//    case (p: Noun, kasus) if Pronomen.get(p, kasus) => "ich"
//  }
//
//  def withEnding(verb: String, ending: String) = verb.replace("en$", ending)
//
//  val translateVerb: PartialFunction[(Verb, Pronoun), String] = {
//    case (verb, pronoun) if schwachesVerb.contains(verb) => pronoun match {
//      case I => withEnding(schwachesVerb(verb), "e")
//      case YouS => withEnding(schwachesVerb(verb), "st")
//      case He | She | It | YouP => withEnding(schwachesVerb(verb), "t")
//      case We | They | YouF => withEnding(schwachesVerb(verb), "en")
//    }
//  }
//
//  val translateNounPhrase: PartialFunction[(Phrase[Noun], Kasus), String] = {
//    case (OneWordPhrase(n), kasus) => translateNoun(n, kasus)
//  }
//
//  val translateVerbPhrase: PartialFunction[(Phrase[Verb], Pronoun), String] = {
//    case (OneWordPhrase(n), pronoun) => translateVerb(n, pronoun)
//  }
//
//  def subjectCase(word: Verb): Kasus = word match {
//    case Talk => Dativ
//  }


}


