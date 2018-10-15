import java.util

import Exercise.{Text2TextMethod, Voice2TextMethod}
import com.sksamuel.diffpatch.DiffMatchPatch
import com.sksamuel.diffpatch.DiffMatchPatch.Operation
import text2speech.AppleTextToSpeechClient

import scala.collection.JavaConversions._

class Session[T](exercise: Exercise[T]) {

  private val ANSI_RESET = "\u001B[0m"
  //  val ANSI_BLACK = "\u001B[30m"
  private val ANSI_RED = "\u001B[31m"
  private val ANSI_GREEN = "\u001B[32m"
  //  val ANSI_YELLOW = "\u001B[33m"
  //  val ANSI_BLUE = "\u001B[34m"
  //  val ANSI_PURPLE = "\u001B[35m"
  //  val ANSI_CYAN = "\u001B[36m"
  //  val ANSI_WHITE = "\u001B[37m"

  var stats = 0 -> 0

  def next() = ask(exercise.nextQuestion(stats._2), repeat = 0)

  private def ask(questionData: T, repeat: Int): Unit = {

    val correct = exercise.answer(questionData)
    val questionText = exercise.questionText(questionData)

    val articlePart = if (correct.matches("^(der|die|das) .*$")) "*" else ""
    exercise.method(questionData) match {
      case Text2TextMethod =>
        print(s"$questionText$articlePart: ")
      case Voice2TextMethod =>
        print("[VOICE]:")
    }
    AppleTextToSpeechClient.speak(questionText)

    val answer = scala.io.StdIn.readLine()

    val diffs = diff(correct.toLowerCase(), answer.toLowerCase())
    if (diffs.forall(_.operation == Operation.EQUAL)) {
      stats = (stats._1 + 1) -> (stats._2 + 1)
      if (repeat == 0) exercise.correct(questionData)
      println(s"Correct! Total: ${stats._2}, rate: ${stats._1.toDouble / stats._2 * 100}%")
      AppleTextToSpeechClient.speak(correct)
      next()
    } else {
      stats = stats._1 -> (stats._2 + 1)
      if (repeat == 0) {
        println(s"Wrong!: ${correct.toList.zipWithIndex.map(x => if (x._2 % 2 == 1) '*' else x._1).mkString("")}")
      } else if (repeat == 1) {
        AppleTextToSpeechClient.speak(correct)
      } else {
        exercise.wrong(questionData)
        println(s"Wrong!: ${diffPrintable(diffs)}")
      }
      ask(questionData, repeat = repeat + 1)
    }
  }

  private def diffPrintable(diffs: util.LinkedList[DiffMatchPatch.Diff]) = {
    diffs.foldLeft("") { (buf, diff) =>
      diff.operation match {
        case Operation.EQUAL => buf + diff.text
        case Operation.INSERT => buf + ANSI_GREEN + diff.text + ANSI_RESET
        case Operation.DELETE => buf + ANSI_RED + diff.text + ANSI_RESET
      }
    }
  }

  private def diff(etalon: String, str: String) = {
    new DiffMatchPatch().diff_main(str, etalon)
  }

}
