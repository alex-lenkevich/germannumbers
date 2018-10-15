import Exercise.{Method, Text2TextMethod}

trait Exercise[T] {

  type Question = T

  def nextQuestion(i: Int): T

  def answer(t: T): String
  def questionText(t: T): String
  def method(t: T): Method = Text2TextMethod

  def correct(t: T): Unit = {}
  def wrong(t: T): Unit = {}

}

object Exercise {

  sealed trait Method
  case object Text2TextMethod extends Method
  case object Voice2TextMethod extends Method

}
