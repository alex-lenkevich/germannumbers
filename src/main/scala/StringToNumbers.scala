import engine.NumbersStringifier

import scala.util.Random

object StringToNumbers extends Exercise[Long] {


  def answer(longVal: Long): String = longVal.toString

  override def nextQuestion(n: Int): Long = {
    (Math.max(Random.nextInt(20) - 10, 0) * 100) +
      (if(Random.nextBoolean) Math.max(Random.nextInt(30) - 10, 0) else Random.nextInt(100))
  }
  
  override def questionText(t: Long): String = NumbersStringifier.stringlify(t)
}
