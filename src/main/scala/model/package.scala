package object model {

  sealed trait Word[W <: Word[W]]
  sealed trait Phrase[W <: Word[W]]

//  case class JoinedPhrase[A <: Word[A]](word1: A, conjunction: Conjunction, word2: A)
//
//  case class OneWordPhrase[W](w: W) extends Phrase[W]
//
//  implicit def wordToPhrase[W <: Word[W]](w: W): Phrase[W] = OneWordPhrase(w)

//  case class PronounPhrase(actor: Pronoun, adjective: Option[Adjective] = None) extends Phrase[Pronoun]
//  case class NounPhrase(noun: Noun, adjective: Option[Adjective] = None) extends Phrase[Noun]
//  case class VerbPhrase(verb: Verb, adjective: Option[Adverb] = None) extends Phrase[Verb]

  trait Pronoun extends Noun
  trait Adjective extends Word[Adjective]
  trait Adverb extends Word[Adverb]
  trait Verb extends Word[Verb]
  trait Conjunction extends Word[Conjunction]
  trait Noun extends Word[Noun]

  case class AffirmativeSentence(
                      actor: Phrase[Noun],
                      action: Phrase[Verb],
                      subject: Option[Noun] = None,
                      adverb: Option[Adverb] = None
                     )
//
//  object I extends Pronoun with Adjective
//  object YouS extends Pronoun with Adjective
//  object He extends Pronoun with Adjective
//  object She extends Pronoun with Adjective
//  object It extends Pronoun with Adjective
//  object We extends Pronoun with Adjective
//  object YouP extends Pronoun with Adjective
//  object They extends Pronoun with Adjective
//  object YouF extends Pronoun with Adjective

//  object My extends Adjective
//  object YoursS extends Adjective
//  object His extends Adjective
//  object Her extends Adjective
//  object Its extends Adjective
//  object Our extends Adjective
//  object YoursP extends Adjective
//  object Their extends Adjective
//  object YoursF extends Adjective
//

}
